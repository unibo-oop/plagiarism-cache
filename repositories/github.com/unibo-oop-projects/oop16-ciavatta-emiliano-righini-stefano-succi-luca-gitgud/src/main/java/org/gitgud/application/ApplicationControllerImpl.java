package org.gitgud.application;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.gitgud.application.about.AboutBoxController;
import org.gitgud.application.about.AboutBoxControllerImpl;
import org.gitgud.application.modal.ModalBoxController;
import org.gitgud.application.modal.ModalBoxControllerImpl;
import org.gitgud.application.node.Box;
import org.gitgud.application.repository.RepositoryBoxController;
import org.gitgud.application.repository.RepositoryBoxControllerImpl;
import org.gitgud.events.EscPressedListener;
import org.gitgud.events.ExitActionListener;
import org.gitgud.events.utils.GlobalExceptionThrowedListener;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.model.Model;
import org.gitgud.model.ModelImpl;
import org.gitgud.utils.Log;
import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The controller implementation of the application controller.
 */
public class ApplicationControllerImpl extends Application implements ApplicationController {

    private final Model model;

    private Stage stage;
    private Pane root; // NOPMD
    private Pane mainContainer;

    private ApplicationView view; // NOPMD

    private final WorkingAreaController workingArea;
    private final AboutBoxController aboutBox;
    private final ModalBoxController modalBox;
    private final RepositoryBoxController repoBox;
    private final Pane waitingOverlay;
    private boolean waitingOverlayShown;

    private List<EscPressedListener> escPressedListeners;
    private List<ExitActionListener> exitActionListeners;

    private Optional<Box> lastOpenedBox = Optional.empty();

    /**
     *
     */
    public ApplicationControllerImpl() {
        model = new ModelImpl();

        workingArea = new WorkingAreaControllerImpl(this, model);
        aboutBox = new AboutBoxControllerImpl(this);
        modalBox = new ModalBoxControllerImpl(this);
        repoBox = new RepositoryBoxControllerImpl(this, model);

        waitingOverlay = new StackPane();
        waitingOverlay.getStyleClass().add("waiting-overlay");

        final GlobalExceptionThrowedListener listener = message -> workingArea.showErrorNotification("Unknown error",
                message);

        Utils.addGlobalExceptionThrowedListener(listener);
    }

    /**
     *
     */
    public static void initController() {
        Log.getLogger().info("Initializing controller..");
        Thread.setDefaultUncaughtExceptionHandler(Utils.getDefaultUncaughtExceptionHandler());
        Application.launch(new String[0]);
    }

    @Override
    public void addEscPressedListener(final EscPressedListener epl) {
        if (escPressedListeners == null) {
            escPressedListeners = new LinkedList<>();
        }
        escPressedListeners.add(epl);
    }

    @Override
    public void addExitActionListener(final ExitActionListener eal) {
        if (exitActionListeners == null) {
            exitActionListeners = new LinkedList<>();
        }
        exitActionListeners.add(eal);
    }

    @Override
    public void closeAboutBox() {
        mainContainer.getChildren().remove(aboutBox.getPane());
        lastOpenedBox = Optional.empty();
    }

    @Override
    public void closeModalBox() {
        Utils.updateView(() -> mainContainer.getChildren().remove(modalBox.getPane()));
        lastOpenedBox = Optional.empty();
    }

    @Override
    public void closeRepositoryBox() {
        mainContainer.getChildren().remove(repoBox.getPane());
        lastOpenedBox = Optional.empty();
    }

    @Override
    public ModalBoxController getModalBox() {
        return modalBox;
    }

    @Override
    public void openAboutBox() {
        if (!canDoAction()) {
            return;
        }

        if (lastOpenedBox.isPresent()) {
            lastOpenedBox.get().closeBox();
        }

        mainContainer.getChildren().add(aboutBox.getPane());
        lastOpenedBox = Optional.of(aboutBox);
    }

    @Override
    public void openModalBox() {
        if (lastOpenedBox.isPresent()) {
            lastOpenedBox.get().closeBox();
        }

        Utils.updateView(() -> mainContainer.getChildren().add(modalBox.getPane()));
        lastOpenedBox = Optional.of(modalBox);
    }

    @Override
    public void openRepositoryBox(final String menu) {
        if (!canDoAction()) {
            return;
        }

        if (lastOpenedBox.isPresent()) {
            lastOpenedBox.get().closeBox();
        }

        Utils.updateView(() -> mainContainer.getChildren().add(repoBox.getPane()));
        repoBox.commandOpenBox(menu);
        lastOpenedBox = Optional.of(repoBox);
    }

    @Override
    public void quit() {
        stage.close();
    }

    @Override
    public void resetTaskProgress() {
        Utils.updateView(() -> view.resetTaskProgress());
    }

    @Override
    public void setTaskProgress(final double progress) {
        Utils.updateView(() -> view.setTaskProgress(progress));
    }

    @Override
    public void setWaitingState(final boolean isWaiting) {
        if (waitingOverlayShown && !isWaiting) {
            mainContainer.getChildren().remove(waitingOverlay);
            waitingOverlayShown = false;
        } else if (!waitingOverlayShown && isWaiting) {
            mainContainer.getChildren().add(waitingOverlay);
            waitingOverlayShown = true;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void start(final Stage stage) {
        this.stage = stage;

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());

        try {
            root = loader.load(getClass().getResourceAsStream("Application.fxml"));
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: Application.fxml");
        }

        view = loader.getController();
        view.attachController(this);

        mainContainer = (Pane) root.lookup("#mainContainer");
        mainContainer.getChildren().add(workingArea.getPane());

        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(Utils.resolveString(ResourceType.SETTINGS, "application.title"));
        stage.getIcons()
                .add(new Image(getClass()
                        .getResourceAsStream(Utils.resolveString(ResourceType.SETTINGS, "application.icon"))));
        stage.setMaximized(true);
        stage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE && escPressedListeners != null) {
                escPressedListeners.forEach(epl -> epl.onEscPressed());
            }
        });
    }

    @Override
    public void stop() {
        model.exitCommand();
        if (exitActionListeners != null) {
            exitActionListeners.forEach(epl -> epl.onExit());
        }
    }

    private boolean canDoAction() {
        return !waitingOverlayShown;
    }

}
