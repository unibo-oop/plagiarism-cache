package view;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.DifficultyLevel;
import model.score.Progress;
import view.controllers.FxController;
import view.controllers.FxHandleDataScene;
import view.controllers.FxNormalScene;
import view.controllers.minigames.FxMiniGameController;

/**
 * The implementation of the {@link View}.
 *
 */
public class ViewImpl extends Application implements View {

    private static final double WIDTH = Screen.getPrimary().getBounds().getWidth() / 2;
    private static final double HEIGHT = Screen.getPrimary().getBounds().getHeight() / 1.2;
    private static final double PERCENTAGE = 1.3;

    private final Controller controller;
    private final SceneLoader sceneLoader;
    private FxController currentSceneController;
    private Stage stage;
    private boolean inGame;

    /**
     * Simple constructor of {@link View}.
     * 
     */
    public ViewImpl() {
        this.controller = new ControllerImpl();
        this.sceneLoader = new SceneLoaderImpl();
    }

    private void setStageSize() {
        this.stage.setHeight(HEIGHT);
        this.stage.setWidth(WIDTH);
        this.stage.setMinHeight(HEIGHT / PERCENTAGE);
        this.stage.setMinWidth(WIDTH / PERCENTAGE);
        this.stage.setMaxHeight(HEIGHT * PERCENTAGE);
        this.stage.setMaxWidth(WIDTH * PERCENTAGE);
    }

    private void initializeSceneController(final FxController currentController) {
        currentController.initialize(this, this.controller);
        if (this.stage.getScene() == null) {
            this.stage.setScene(new Scene(currentController.getRoot()));
        } else {
            this.stage.getScene().setRoot(currentController.getRoot());
        }
        currentController.getRoot().requestFocus();
    }

    private void simpleSwitch(final String path) {
        this.currentSceneController = this.sceneLoader.loadScene(path).get();
        this.initializeSceneController(this.currentSceneController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchScene(final String path) {
        this.simpleSwitch(path);
        ((FxNormalScene) this.currentSceneController).init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadProgress(final String path) {
        this.inGame = false;
        this.simpleSwitch(path);
        this.controller.initStatistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMiniGameScene(final String path) {
        this.inGame = true;
        this.simpleSwitch(path);
        this.controller.selectedDifficultyLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initStatistics(final Progress progress) {
        ((FxHandleDataScene) this.currentSceneController).init(progress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final DifficultyLevel difficultyLevel) {
        ((FxMiniGameController) this.currentSceneController).initGame(difficultyLevel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        this.stage = stage;
        this.setStageSize();
        this.stage.setTitle("Brain Trainer");
        this.stage.getIcons().add(new Image("images/brain_training.png"));
        this.stage.setResizable(true);
        this.stage.show();
        this.inGame = false;
        this.controller.initializeView(this);

        this.stage.setOnCloseRequest((e) -> {
            controller.saveData();
            if (this.inGame) {
                ((FxMiniGameController) currentSceneController).stopTimer();
            }
        });
    }
}
