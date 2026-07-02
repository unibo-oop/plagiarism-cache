package view.transitions;

import java.util.List;

import controller.Controller;
import enums.GameScene;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.entities.GameEntity;
import view.GameSceneInterface;
import view.JavaFXView;

/**
 * Scene class for the transition between Main Menu and Construction Mode. The
 * class extends JavaFX's class Scene.
 */
public final class TransitionConstructionScene extends Scene implements GameSceneInterface {

    // Scene Magic Numbers.
    private static final double SCENE_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double SCENE_HEIGHT = JavaFXView.STAGE_DIMESNION;

    // The controller of the game.
    private final Controller controller;

    /**
     * Constructor method.
     * 
     * @param controller the controller of the game.
     */
    public TransitionConstructionScene(final Controller controller) {
        super(new TransitionPanel(false, false, "CONSTRUCTION MODE", "NOT IMPLEMENTED"), SCENE_WIDTH, SCENE_HEIGHT);
        this.controller = controller;
        init();
    }

    /*
     * Method that initialize the transition scene.
     */
    private void init() {
        setKeyPressed();
    }

    /*
     * Set the behavior of the menu when a key is pressed.
     */
    private void setKeyPressed() {
        setOnKeyPressed(event -> {
            // Event for the Enter Key pressed.
            if (event.getCode() == KeyCode.ENTER) {
                controller.setGameScene(GameScene.MAIN_MENU_OLD);
            }
        });
    }

    @Override
    public void render(final List<GameEntity> gameEntities) {
        // Nothing to render.
    }

}
