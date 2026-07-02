package it.unibo.model.interfaces;

/**
 * Interface representing a model for handling keyboard input in the game.
 * Provides methods to detect key presses and releases, and to check the state of a key.
 */
public interface KeyboardModelInterface {

    /**
     * Registers when a key is pressed down.
     * This method is called when a specific key is pressed by the user.
     * 
     * @param key The key code of the key that was pressed.
     */
    void keyDown(Integer key);

    /**
     * Registers when a key is released.
     * This method is called when a specific key is released by the user.
     * 
     * @param key The key code of the key that was released.
     */
    void keyUp(Integer key);

    /**
     * Checks if a specific key is currently being pressed.
     * 
     * @param key The key code of the key to check.
     * @return {@code true} if the key is currently pressed, {@code false} otherwise.
     */
    boolean isKeyPressed(Integer key);
}
