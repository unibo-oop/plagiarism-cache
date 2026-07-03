package oop.lit.view.controller;

import oop.lit.view.Camera;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class configures keys.
 */
public class Keyboard {

    private static final double CAMERA_TICK = 10;
    private boolean keepOldSelection;

    /**
     * Set the keys that players can use during a game.
     * 
     * @param s
     *            the scene of the game.
     * @param camera
     *            of the board.
     */
    public void editKeyboard(final Scene s, final Camera camera) {
        s.setOnKeyPressed(getOnKeyPressed(camera));
        s.setOnKeyReleased(getOnKeyReleased());
    }

    /**
     * @return the keepOldSelection
     */
    public boolean isKeepOldSelection() {
        return keepOldSelection;
    }

    /**
     * @param keepOldSelection
     *            the keepOldSelection to set
     */
    public final void setKeepOldSelection(final boolean keepOldSelection) {
        this.keepOldSelection = keepOldSelection;
    }

    private EventHandler<KeyEvent> getOnKeyPressed(final Camera camera) {
        return new EventHandler<KeyEvent>() {

            @Override
            public void handle(final KeyEvent e) {
                if (e.getCode().equals(KeyCode.CONTROL)) {
                    keepOldSelection = true;
                }
                if (e.getCode().equals(KeyCode.W)) {
                    camera.moveVertical(-CAMERA_TICK);
                }
                if (e.getCode().equals(KeyCode.A)) {
                    camera.moveHorizontal(-CAMERA_TICK);
                }
                if (e.getCode().equals(KeyCode.S)) {
                    camera.moveVertical(CAMERA_TICK);
                }
                if (e.getCode().equals(KeyCode.D)) {
                    camera.moveHorizontal(CAMERA_TICK);
                }
                if (e.getCode().equals(KeyCode.C)) {
                    camera.resetCamera();
                }
                e.consume();
            }
        };
    }

    private EventHandler<KeyEvent> getOnKeyReleased() {
        return new EventHandler<KeyEvent>() {

            @Override
            public void handle(final KeyEvent e) {
                if (e.getCode().equals(KeyCode.CONTROL)) {
                    keepOldSelection = false;
                }
                e.consume();
            }
        };
    }
}
