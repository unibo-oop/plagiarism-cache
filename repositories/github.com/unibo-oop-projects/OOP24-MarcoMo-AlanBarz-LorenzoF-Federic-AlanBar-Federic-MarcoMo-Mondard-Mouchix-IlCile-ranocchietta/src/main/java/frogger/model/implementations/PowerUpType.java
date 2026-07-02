package frogger.model.implementations;

/**
 * Enum representing the different types of power-ups available in the game.
 * <ul>
 *   <li>{@link #EXTRA_LIFE} - Grants the player an additional life.</li>
 *   <li>{@link #FREEZE} - Temporarily freezes obstacles or enemies.</li>
 *   <li>{@link #X2_SCORE} - Doubles the score earned for a limited time.</li>
 * </ul>
 */
public enum PowerUpType {
    /**
     * Represents a power-up that grants the player an additional life.
     */
    EXTRA_LIFE,
    /**
     * Represents a power-up that temporarily freezes obstacles or enemies.
     */
    FREEZE,
    /**
     * Represents a power-up that doubles the score earned for a limited time.
     */
    X2_SCORE
}

