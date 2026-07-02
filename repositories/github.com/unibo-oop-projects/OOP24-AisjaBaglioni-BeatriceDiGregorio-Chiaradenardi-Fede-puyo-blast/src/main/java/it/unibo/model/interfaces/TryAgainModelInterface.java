package it.unibo.model.interfaces;

/**
 * Interface for the "Try Again" model in the game.
 * It provides methods to check and modify the state of the "Try Again" feature.
 */
public interface TryAgainModelInterface {

    /**
     * Returns whether the "Try Again" feature is enabled.
     * 
     * @return true if the "Try Again" feature is enabled, false otherwise.
     */
    boolean isEnabled();

    /**
     * Sets the state of the "Try Again" feature.
     * 
     * @param enabled true to enable the "Try Again" feature, false to disable it.
     */
    void setEnabled(boolean enabled);
}
