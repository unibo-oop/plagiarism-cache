package bzzbomber.model;

/**
 * Represents type of reset for the game needs.
 */
public enum ResetType {
    /**
     * Means that the game has to be in a state as if it was launched for the first
     * time.
     */
    TOTAL,
    /**
     * Means that the game has to switch to the next level and reset the states of
     * dependent classes.
     */
    LEVEL_CHANGED
}
