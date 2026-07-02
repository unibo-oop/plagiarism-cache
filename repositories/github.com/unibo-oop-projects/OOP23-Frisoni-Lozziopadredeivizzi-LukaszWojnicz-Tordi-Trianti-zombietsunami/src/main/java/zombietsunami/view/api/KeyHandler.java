package zombietsunami.view.api;

import java.awt.event.KeyEvent;

/**
 * This interface provides methods for handling keyboard input.
 */
public interface KeyHandler {
    /**
     * Responds to a key being pressed.
     * 
     * @param e The KeyEvent object representing the key event.
     */
    void keyPressed(KeyEvent e);

    /**
     * Responds to a key being released.
     * 
     * @param e The KeyEvent object representing the key event.
     */
    void keyReleased(KeyEvent e);

    /**
     * This method is not used in this implementation and does not perform any
     * actions.
     * 
     * @param e The KeyEvent object representing the key event.
     */
    void keyTyped(KeyEvent e);

    /**
     * Checks if a jump variable is true.
     * 
     * @return true if the key is currently pressed, false otherwise.
     */
    boolean isPressed();

    /**
     * @return if the game is on pause or not
     */
    boolean isOnPause();
}
