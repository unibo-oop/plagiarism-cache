package view.keyboardhandler;

import controller.stagehandler.playerhandler.PlayerHandler;
import javafx.scene.input.KeyCode;

/**
 * Responsible for activating the functions corresponding to the input received.
 */
public interface KeyEvent {

    /**
     * Activate a function when a key is pressed.
     * @param key KeyCode of the key pressed
     */
    void keyPressed(KeyCode key);

    /**
     * Deactivate a function when a key is released.
     * @param key KeyCode of the key released
     */
    void keyReleased(KeyCode key);

    /**
     * Sets the PlayerHandler for player-related key events.
     * @param handler The PlayerHandler to be set
     */
    void setPlayerHandler(PlayerHandler handler);

}
