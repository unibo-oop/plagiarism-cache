package powpaw.player.view.api;

import javafx.scene.input.KeyCode;

/**
 * Interface that defines methods for handling key events in a game.
 * 
 * @author Alessia Carfì
 */
public interface KeyObserver {

    /**
     * Responds to a key press event by updating the state of the associated
     * `Player` object.
     *
     * @param event the `KeyCode` associated with the key press event
     */
    void keyPressed(KeyCode event);

    /**
     * Responds to a key release event by updating the state of the associated
     * `Player` object.
     *
     * @param event the `KeyCode` associated with the key release event
     */
    void keyReleased(KeyCode event);
}
