package bubbleshooter.view;

import java.io.IOException;

import bubbleshooter.controller.Controller;
import bubbleshooter.utility.Settings;
import bubbleshooter.view.images.ImageLoader;
import bubbleshooter.view.scene.FXMLPath;
import bubbleshooter.view.scene.SceneLoader;
import bubbleshooter.view.scene.controller.AbstractController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Implementation of {@link View}.
 *
 */
public class ViewImpl implements View {

    private static final String TITLE = "BUBBLE SHOOTER";
    private static final double WIDTH = Settings.getGuiWidth();
    private static final double HEIGHT = Settings.getGuiHeight();
    private Controller controller;
    private AbstractController currentSceneController;
    private final Stage stage;
    private boolean viewStarted;
    private final SceneLoader sceneLoader;

    /**
     * @param startingStage The starting stage of the GUI.
     */
    public ViewImpl(final Stage startingStage) {
        this.stage = startingStage;
        this.viewStarted = false;
        this.sceneLoader = new SceneLoader();
    }

    @Override
    public final void launch(final Controller controller) {
        this.controller = controller;
        this.initialize();
    }

    /**
     * Loads all images, sets the stage and loads first scene.
     */
    private void initialize() {
        ImageLoader.loadAll();
        this.stage.setTitle(TITLE);
        this.stage.setMinHeight(HEIGHT);
        this.stage.setMinWidth(WIDTH);
        this.stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
        this.loadScene(FXMLPath.MAIN);
    }

    @Override
    public final void loadScene(final FXMLPath scene) {
        try {
            this.sceneLoader.loadScene(scene);
            this.currentSceneController = this.sceneLoader.getController();
            this.currentSceneController.init(controller, this);
            this.sceneLoader.getScene().getRoot().requestFocus();
            Platform.runLater(() -> this.initStage(this.sceneLoader.getScene()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets the stage for the newly loaded scene.
     * 
     * @param the loaded scene.
     */
    private void initStage(final Scene scene) {
        this.stage.setScene(scene);
        this.stage.setWidth(this.stage.getWidth());
        this.stage.setHeight(this.stage.getHeight());
        if (!this.viewStarted) {
            this.stage.setResizable(false);
            this.stage.show();
            this.viewStarted = true;
        }
    }

    @Override
    public final void update() {
        Platform.runLater(() -> this.currentSceneController.render());
    }

    @Override
    public final void showGameOver() {
        this.loadScene(FXMLPath.GAMEOVER);
    }

}
