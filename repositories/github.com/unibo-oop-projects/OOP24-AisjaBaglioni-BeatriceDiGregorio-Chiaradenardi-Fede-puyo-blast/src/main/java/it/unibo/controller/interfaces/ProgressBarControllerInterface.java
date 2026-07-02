package it.unibo.controller.interfaces;

/**
 * Interface representing the controller for managing the progress bar in the game.
 * Provides methods to reset the progress bar and the controller state.
 */
public interface ProgressBarControllerInterface {

    /**
     * Resets the progress bar to its initial state.
     * Typically sets the progress bar's charge level to 0 and prepares it for a new cycle.
     * 
     * @return {@code true} if the progress bar was successfully reset, {@code false} otherwise.
     */
    boolean resetBar();

    /**
     * Resets the entire controller state.
     * This could include resetting any internal states or properties related to the progress bar's functionality.
     */
    void reset();
}
