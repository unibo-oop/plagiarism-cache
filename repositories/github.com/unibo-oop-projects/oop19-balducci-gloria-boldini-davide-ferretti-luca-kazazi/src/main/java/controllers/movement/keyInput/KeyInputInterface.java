package controllers.movement.keyInput;

import java.awt.event.KeyEvent;

public interface KeyInputInterface {

    /**
     * @param e
     * 
     *          method to recognize when a key is pressed
     */
    void keyPressed(KeyEvent e);

    /**
     * @param e
     * 
     *          method to recognize when a key has been released
     */
    void keyReleased(KeyEvent e);
}
