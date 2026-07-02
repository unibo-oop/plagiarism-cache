package view;

import java.util.List;

import controller.Controller;
import controller.file.FileController;
import controller.file.FileControllerImpl;
import enums.GameScene;
import enums.SceneImage;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.entities.GameEntity;
import view.gamegui.GameGuiScene;
import view.gameover.GameOverScene;
import view.mainmenu.MainMenuScene;
import view.pointsrecap.PointsRecapScene;
import view.transitions.Transition1PScene;
import view.transitions.Transition2PScene;
import view.transitions.TransitionConstructionScene;

/**
 * Class that implements the view with JavaFX.
 */
public final class JavaFXView implements View {

    /**
     * Window dimensions. WIDTH = HEIGHT. All the game dimensions depends on this
     * value.
     */
    public static final double STAGE_DIMESNION = 900.0;

    // Stage Magic Numbers
    private static final double STAGE_WIDTH = STAGE_DIMESNION * 1.07;
    private static final double STAGE_HEIGHT = STAGE_DIMESNION;

    // Title of the window.
    private static final String TITLE = "Battle City";
    // The main stage.
    private final Stage myStage;
    // The current scene.
    private GameSceneInterface currentScene;
    // The controller of the game.
    private final Controller controller;
    // The file controller.
    private final FileController fc;

    /**
     * The constructor method of the view.
     * 
     * @param myStage    main window.
     * @param controller the controller of the game.
     */
    public JavaFXView(final Stage myStage, final Controller controller) {
        this.myStage = myStage;
        this.controller = controller;
        fc = new FileControllerImpl();
    }

    @Override
    public void initWindow() {
        myStage.setTitle(TITLE);
        myStage.getIcons().add(fc.getSceneImage(SceneImage.PLAYER_ICON)); // Icon.
        myStage.setWidth(STAGE_WIDTH);
        myStage.setHeight(STAGE_HEIGHT);
        myStage.setResizable(false); // No resize.
        myStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {
                controller.stopGame();
                Platform.exit();
                System.exit(0);
            }
        });
    }

    @Override
    public void loadScene(final GameScene scene) {

        switch (scene) {
        case GAME_GUI:
            currentScene = new GameGuiScene(controller);
            break;
        case GAME_OVER:
            currentScene = new GameOverScene(controller);
            break;
        case MAIN_MENU_NEW:
            currentScene = new MainMenuScene(controller, true, 0);
            break;
        case MAIN_MENU_OLD:
            currentScene = new MainMenuScene(controller, false, 2);
            break;
        case POINTS_RECAP:
            currentScene = new PointsRecapScene(controller);
            break;
        case TRANSITION_STAGE_1P:
            currentScene = new Transition1PScene(controller);
            break;
        case TRANSITION_STAGE_2P:
            currentScene = new Transition2PScene(controller);
            break;
        case TRANSITION_CONSTRUCTION:
            currentScene = new TransitionConstructionScene(controller);
            break;
        default:
            break;
        }

        myStage.setScene((Scene) currentScene);
    }

    @Override
    public void showWindow() {
        myStage.show();
    }

    @Override
    public void renderCurrentScene(final List<GameEntity> gameEntities) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.currentScene.render(gameEntities));
        }
    }

}
