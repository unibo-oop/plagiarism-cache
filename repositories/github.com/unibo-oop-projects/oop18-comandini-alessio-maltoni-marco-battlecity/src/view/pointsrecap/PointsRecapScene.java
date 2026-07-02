package view.pointsrecap;

import java.util.List;

import controller.Controller;
import enums.GameScene;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.entities.GameEntity;
import view.GameSceneInterface;
import view.JavaFXView;

/**
 * Scene that recapitulate the player's point. The class extends JavaFX's class
 * Scene.
 */
public final class PointsRecapScene extends Scene implements GameSceneInterface {

    // Scene Magic Numbers.
    private static final double SCENE_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double SCENE_HEIGHT = JavaFXView.STAGE_DIMESNION;

    // The controller of the game.
    private final Controller controller;
    // The root of the scene.
    private final PointsRecapPanel myRoot;

    /**
     * Constructor of the scene.
     * 
     * @param controller controller of the game.
     */
    public PointsRecapScene(final Controller controller) {
        super(new PointsRecapPanel(controller), SCENE_WIDTH, SCENE_HEIGHT);
        this.controller = controller;
        this.myRoot = (PointsRecapPanel) getRoot();
        init();
    }

    /*
     * Method that initialize the points recapitulation scene.
     */
    private void init() {
        setKeyPressed();
    }

    /*
     * Set the behavior of the menu when a key is pressed. Active only when
     * animation is over.
     */
    private void setKeyPressed() {
        setOnKeyPressed(event -> {
            // Event for the Enter Key pressed.
            if (event.getCode() == KeyCode.ENTER) {
                if (myRoot.isAnimationcompleted()) {
                    controller.setGameScene(GameScene.MAIN_MENU_NEW);
                } else {
                    myRoot.completeAnimation();
                }
            }
        });
    }

    @Override
    public void render(final List<GameEntity> gameEntities) {
        // Nothing to render.
    }

}
