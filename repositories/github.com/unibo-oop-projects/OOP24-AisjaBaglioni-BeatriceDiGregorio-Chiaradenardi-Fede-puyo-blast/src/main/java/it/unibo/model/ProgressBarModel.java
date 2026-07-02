package it.unibo.model;

import it.unibo.model.interfaces.ProgressBarModelInterface;

/**
 * The ProgressBarModel class represents the state and behavior of a progress
 * bar.
 * It manages the charge level, which ranges from 0 to 1, and allows for the
 * incrementing of the charge level as well as resetting it.
 */

public class ProgressBarModel implements ProgressBarModelInterface {
    /**
     * The current charge level of the progress bar, ranging from 0 to 1.
     * The value represents how filled the progress bar is.
     */
    private double chargeLevel;
    /**
     * The step size for incrementing the charge level.
     * Each time the progress is incremented, the charge level is increased by this
     * value.
     */
    private static final double STEP = 0.002;

    /**
     * Constructor initializing the progress bar's charge level to 0.
     */
    public ProgressBarModel() {
        this.chargeLevel = 0;
    }

    /**
     * Gets the current charge level of the progress bar.
     * 
     * @return the charge level, ranging from 0 to 1.
     */
    @Override
    public double getChargeLevel() {
        return this.chargeLevel;
    }

    /**
     * Resets the charge level of the progress bar to 0.
     */
    @Override
    public void resetCharge() {
        this.chargeLevel = 0;
    }

    /**
     * Increments the charge level of the progress bar by a fixed step size.
     * The charge level is capped at 1 to ensure it never exceeds the maximum value.
     */
    @Override
    public void incrementProgress() {
        this.chargeLevel = Math.min(1, this.chargeLevel + STEP);
    }
}
