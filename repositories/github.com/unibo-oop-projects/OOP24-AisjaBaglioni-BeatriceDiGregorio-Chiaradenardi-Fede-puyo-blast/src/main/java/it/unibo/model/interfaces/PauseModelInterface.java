package it.unibo.model.interfaces;

/**
 * The {@code PauseModelInterface} defines the methods for managing the pause state of the game.
 * Implementing this interface allows controlling whether the game is paused, toggling the pause state,
 * and retrieving the current pause status.
 */
public interface PauseModelInterface {

    /**
     * Sets the pause state of the game.
     * 
     * @param pause {@code true} to pause the game, {@code false} to unpause it
     */
    void setPause(boolean pause);

    /**
     * Toggles the current pause state of the game.
     * If the game is paused, it will be unpaused, and if it's unpaused, it will be paused.
     */
    void togglePause();

    /**
     * Retrieves the current pause state of the game.
     * 
     * @return {@code true} if the game is paused, {@code false} otherwise
     */
    boolean getPause();
}
