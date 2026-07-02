package view.gameover;

import java.util.List;

import controller.Controller;
import enums.GameScene;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.entities.GameEntity;
import view.GameSceneInterface;
import view.JavaFXView;

/**
 * Scene that display the text "Game Over". Implemented in JavaFX. The class
 * extends the JavaFX's class Scene.
 */
public final class GameOverScene extends Scene implements GameSceneInterface {

    // Scene Magic Numbers.
    private static final double SCENE_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double SCENE_HEIGHT = JavaFXView.STAGE_DIMESNION;

    // The controller of the game
    private final Controller controller;

    /**
     * Constructor method.
     * 
     * @param controller the controller of the game.
     */
    public GameOverScene(final Controller controller) {
        super(new GameOverPanel(), SCENE_WIDTH, SCENE_HEIGHT);
        this.controller = controller;
        init();
    }

    /*
     * Method that initialize the game over scene.
     */
    private void init() {
        setKeyPressed();
    }

    /**
     * Set the behavior of the menu when a key is pressed.
     */
    private void setKeyPressed() {
        setOnKeyPressed(event -> {
            // Enter Key.
            if (event.getCode() == KeyCode.ENTER) {
                controller.setGameScene(GameScene.MAIN_MENU_NEW);
            }
        });
    }

    @Override
    public void render(final List<GameEntity> gameEntities) {
        // Nothing to render.
    }
}
