package powpaw.player.view.api;

import javafx.scene.input.KeyCode;

/**
 * Interface that provides a mechanism for registering
 * KeyObserver objects and notifying them when keys are pressed or released.
 * 
 * @author Alessia Carfì
 */
public interface KeyObservable {

    /**
     * Registers a {@code KeyObserver} object to receive notifications when keys are
     * pressed or released.
     * 
     * @param observer the KeyObserver object
     */
    void addObserver(KeyObserver observer);

    /**
     * Unregisters a {@code KeyObserver} object from receiving notifications.
     * 
     * @param observer the KeyObserver object
     */
    void removeObserver(KeyObserver observer);

    /**
     * Notifies all registered KeyObserver that a key has been pressed.
     * 
     * @param event the key that was pressed
     */
    void notifyObserversPressed(KeyCode event);

    /**
     * Notifies all registered KeyObserver objects that a key has been released.
     * 
     * @param event the key that was released
     */
    void notifyObserversReleased(KeyCode event);
}
