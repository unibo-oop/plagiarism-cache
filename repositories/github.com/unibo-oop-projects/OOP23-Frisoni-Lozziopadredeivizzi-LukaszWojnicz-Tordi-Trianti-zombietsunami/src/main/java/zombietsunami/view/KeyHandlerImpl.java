package zombietsunami.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import zombietsunami.view.api.KeyHandler;

/**
 * Implementation of the KeyHandler interface for handling keyboard input.
 */
public class KeyHandlerImpl implements KeyListener, KeyHandler {

    private boolean jump;
    private boolean onPause;

    /**
     * This method is not used in this implementation and does not perform any
     * actions.
     * 
     * @param e The KeyEvent object representing the key event.
     */
    @Override
    public void keyTyped(final KeyEvent e) {
    }

    /**
     * Responds to a key being pressed.
     * 
     * @param e The KeyEvent object representing the key event.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            jump = true;
        }
        if (code == KeyEvent.VK_ESCAPE && !this.onPause) {
            this.onPause = true;
        } else if (code == KeyEvent.VK_ESCAPE && this.onPause) {
            this.onPause = false;
        }
    }

    /**
     * Responds to a key being released.
     * 
     * @param e The KeyEvent object representing the key event.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        final int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            jump = false;
        }

    }

    /**
     * Checks if the jump key is currently pressed and resets its state.
     * 
     * @return true if the jump key is currently pressed, false otherwise.
     */
    @Override
    public boolean isPressed() {
        if (jump) {
            jump = false;
            return true;
        }
        return false;
    }

    /**
     * Checks if the game is currently paused.
     * 
     * @return true if the game is paused, false otherwise.
     */
    @Override
    public boolean isOnPause() {
        return this.onPause;
    }

}
