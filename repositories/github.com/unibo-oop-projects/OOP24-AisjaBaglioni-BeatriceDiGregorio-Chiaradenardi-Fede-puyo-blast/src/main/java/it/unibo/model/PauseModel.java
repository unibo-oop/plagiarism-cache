package it.unibo.model;

import it.unibo.model.interfaces.PauseModelInterface;

/**
 * The {@code PauseModel} class is responsible for managing the pause state of the game.
 * It allows checking if the game is paused, setting the pause state, and toggling the pause state.
 * 
 * This class implements the {@link PauseModelInterface} and provides methods to update
 * and retrieve the pause status of the game.
 */
public class PauseModel implements PauseModelInterface {

    /** Indicates whether the game is currently paused. */
    private boolean isPaused;

    /**
     * Constructs a {@code PauseModel} instance, initializing the game to be unpaused by default.
     */
    public PauseModel() {
        this.isPaused = false;
    }

    /**
     * Sets the pause state of the game.
     * 
     * @param pause {@code true} to pause the game, {@code false} to unpause it
     */
    @Override
    public void setPause(boolean pause) {
        this.isPaused = pause;
    }

    /**
     * Toggles the current pause state of the game.
     * If the game is paused, it will be unpaused, and vice versa.
     */
    @Override
    public void togglePause() {
        this.isPaused = !this.isPaused;
    }

    /**
     * Retrieves the current pause state of the game.
     * 
     * @return {@code true} if the game is paused, {@code false} otherwise
     */
    @Override
    public boolean getPause() {
        return this.isPaused;
    }
}
