package it.unibo.model.interfaces;

/**
 * Interface representing a model for controlling the progress bar in the game.
 * Provides methods for getting the current charge level, resetting the charge, and incrementing progress.
 */
public interface ProgressBarModelInterface {

    /**
     * Gets the current charge level of the progress bar.
     * 
     * @return The current charge level as a double value, typically between 0.0 and 1.0.
     */
    double getChargeLevel();

    /**
     * Resets the charge level of the progress bar to the initial state.
     * This will typically set the charge level back to 0.
     */
    void resetCharge();

    /**
     * Increments the progress of the progress bar.
     * This will increase the charge level, typically by a fixed amount.
     */
    void incrementProgress();
}
