package it.unibo.utilities;

/**
 * Represents the movements of the player.
 */
public enum Movements {

    /**
     * Represents the upward movement of the player.
     */
    UP,
    /**
     * Represents the downward movement of the player.
     */
    DOWN,
    /**
     * Represents the moving to the left of the player.
     */
    LEFT,
    /**
     * Represents the moving to the right of the player.
     */
    RIGHT,
    /**
     * Represents the movements to fit the window of the player.
     */
    FIX,
    /**
     * Represents the motionlessness of the player.
     */
    STOP;

    /**
     * The current movements.
     */
    private static Movements current = STOP;

    /**
     * Get the current movements.
     * 
     * @return the current movements
     */
    public static Movements getMovements() {
        return current;
    }

    /**
     * Set the movements.
     * 
     * @param mov the movements
     */
    public static void setMovements(final Movements mov) {
        current = mov;
    }
}
