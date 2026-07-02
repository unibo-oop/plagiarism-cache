package it.tbt.controller.viewcontrollermanager.api;

/**
 * The {@code InputListener} interface represents an input listener that handles key press events.
 * Implementations of this interface can be registered to receive key press events.
 */
public interface InputListener {
    /**
     * Called when a key is pressed.
     *
     * @param key the key code of the pressed key
     */
    void onKeyPressed(int key);
}
