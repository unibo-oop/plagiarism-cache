package controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utils.Direction;

/**
 * 
 * Manages all keyboard inputs.
 *
 */
public class KeyboardInput {

    private KeyCode pressedKey = KeyCode.UNDEFINED;
    private MainController controller;
    private EntityFactory factory;
    private MovementController movement;

    /**
     * @param mainScene  The view main scene.
     * @param controller The main controller of the game.
     */
    public KeyboardInput(final Scene mainScene, final MainController controller) {
        this.controller = controller;
        mainScene.addEventHandler(KeyEvent.KEY_PRESSED, this::onKeyEvent);
    }

    private void onKeyEvent(final KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            pressedKey = event.getCode();
        }
        execution();
    }

    /**
     * getter of pressedKey.
     * 
     * @return pressedKey.
     */
    private KeyCode getPressedKey() {
        return pressedKey;
    }

    /**
     * 
     */
    public void execution() {
        switch (GameState.getGameState().getState()) {
        case INGAME:
            if (getPressedKey().equals(KeyCode.LEFT)) {
                movement.moveStuntman(Direction.LEFT);
            }
            if (getPressedKey().equals(KeyCode.RIGHT)) {
                movement.moveStuntman(Direction.RIGHT);
            }
            if (getPressedKey().equals(KeyCode.UP)) {
                factory.getHawks().forEach(hawk -> {
                    hawk.shiftDown();
                });
                factory.getVases().forEach(vase -> {
                    vase.shiftDown();
                });
                factory.getBonus().forEach(bonus -> {
                    bonus.shiftDown();
                });
                factory.getPalace().addFloor();
                movement.moveStuntman(Direction.UP);
            }
            if (getPressedKey().equals(KeyCode.ESCAPE)) {
                controller.pause();
            }
            break;
        case PAUSE:
            if (getPressedKey().equals(KeyCode.ESCAPE)) {
                controller.continueGame();
            }
            break;
        case MENU:
            if (getPressedKey().equals(KeyCode.ESCAPE)) {
                controller.exit();
            }
            break;
        default:
            break;
        }
        pressedKey = KeyCode.UNDEFINED;
    }

    /**
     * 
     * @param factory  The entity factory.
     * @param movement The movement controller.
     */
    public void setParameters(final EntityFactory factory, final MovementController movement) {
        this.factory = factory;
        this.movement = movement;
    }
}
