package it.unibo.model;

import it.unibo.model.interfaces.TryAgainModelInterface;

/**
 * This class represents the model for the "Try Again" button in the game.
 * It manages whether the "Try Again" option is enabled or not.
 * The feature can be toggled based on the game's state.
 */
public class TryAgainModel implements TryAgainModelInterface {

    /**
     * A flag that indicates whether the "Try Again" option is enabled.
     */
    private boolean isEnabled;

    /**
     * Constructor that initializes the "Try Again" feature as enabled by default.
     */
    public TryAgainModel() {
        this.isEnabled = true;
    }

    /**
     * Returns whether the "Try Again" feature is enabled.
     *
     * @return true if the "Try Again" option is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Sets the state of the "Try Again" feature.
     *
     * @param enabled true to enable the "Try Again" feature, false to disable it.
     */
    @Override
    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
}
