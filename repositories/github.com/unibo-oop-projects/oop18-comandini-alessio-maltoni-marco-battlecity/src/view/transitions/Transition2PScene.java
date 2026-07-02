package view.transitions;

import java.util.List;

import controller.Controller;
import enums.GameMode;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.entities.GameEntity;
import view.GameSceneInterface;
import view.JavaFXView;

/**
 * Scene class for the transition between Main Menu and Two Player Mode. The
 * class extends JavaFX's class Scene.
 */
public final class Transition2PScene extends Scene implements GameSceneInterface {

    // Scene Magic Numbers.
    private static final double SCENE_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double SCENE_HEIGHT = JavaFXView.STAGE_DIMESNION;
    private static final String SELECT = "SELECT STAGE";
    private static final String STAGE = "STAGE ";
    private static final String MODE = "TWO PLAYER MODE";

    // The controller of the game.
    private final Controller controller;
    // The root of the scene.
    private final TransitionPanel root;

    /**
     * Constructor method.
     * 
     * @param controller the controller of the game.
     */
    public Transition2PScene(final Controller controller) {
        super(new TransitionPanel(false, true, SELECT, STAGE + Integer.toString(controller.getCurrentStageNumber()),
                MODE), SCENE_WIDTH, SCENE_HEIGHT);
        this.controller = controller;
        this.root = (TransitionPanel) getRoot();
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
                controller.setGameMode(GameMode.TWO_PLAYER);
                controller.startGame();
            }
            // Event for the Left Key pressed.
            if (event.getCode() == KeyCode.LEFT) {
                controller.setPreviousStage();
                if (controller.getCurrentStage().equals(controller.getMinStage())) {
                    root.setText(false, true, SELECT, STAGE + Integer.toString(controller.getCurrentStageNumber()),
                            MODE);
                } else {
                    root.setText(true, true, SELECT, STAGE + Integer.toString(controller.getCurrentStageNumber()),
                            MODE);
                }
            }
            // Event for the Right Key pressed.
            if (event.getCode() == KeyCode.RIGHT) {
                controller.setNextStage();
                if (controller.getCurrentStage().equals(controller.getMaxStage())) {
                    root.setText(true, false, SELECT, STAGE + Integer.toString(controller.getCurrentStageNumber()),
                            MODE);
                } else {
                    root.setText(true, true, SELECT, STAGE + Integer.toString(controller.getCurrentStageNumber()),
                            MODE);
                }
            }
        });
    }

    @Override
    public void render(final List<GameEntity> gameEntities) {
        // Nothing to render.
    }

}
