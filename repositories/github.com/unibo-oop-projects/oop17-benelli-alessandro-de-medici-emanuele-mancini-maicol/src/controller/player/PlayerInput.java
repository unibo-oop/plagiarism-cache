package controller.player;

import javafx.scene.input.KeyEvent;

/**
 * PlayerInputImpl interface.
 *
 */
public interface PlayerInput {

    /**
     * Method called when player presses a button while playing. Stops and runs
     * player's animation and step sound.
     * 
     * @param event
     *            KeyEvent of the pressed button
     */
    void keyPressed(KeyEvent event);

    /**
     * Method called when player releases a button while playing.
     * 
     * @param event
     *            KeyEvent of the released button
     */
    void keyReleased(KeyEvent event);

    /**
     * Setter of repeat.
     * 
     * @param repeat
     *            value to be set
     */
    void setRepeat(boolean repeat);
}
