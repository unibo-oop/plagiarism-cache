package controller.input;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Interface to manage the controller input.
 */
public interface ProcessInput {

    /**
     * Setter for the keyboard input.
     * @param event
     *          the {@link KeyEvent}.
     * @param b
     *          a boolean. It's true if the key is pressed, false otherwise.
     */
    void setKeyInput(KeyEvent event, boolean b);

    /**
     * Setter for the mouse movement input.
     * @param event
     *          the {@link MouseEvent}.
     */
    void setMouseMovement(MouseEvent event);

    /**
     * Setter for the mpuse clicked input.
     * @param event
     *          the {@link MouseEvent},
     */
    void setMouseClicked(MouseEvent event);

}
