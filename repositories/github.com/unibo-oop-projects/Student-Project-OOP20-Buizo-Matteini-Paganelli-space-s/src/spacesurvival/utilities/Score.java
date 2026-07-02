package spacesurvival.utilities;

/**
 * Utility class for object's scores.
 */
public final class Score {

    /**
     * Score assigned to user when ship destroyed.
     */
    public static final int SHIP = -100;
    /**
     * Score assigned to user when asteroid destroyed.
     */
    public static final int ASTEROID = 10;
    /**
     * Score assigned to user when chase enemy destroyed.
     */
    public static final int CHASE_ENEMY = 15;
    /**
     * Score assigned to user when fire enemy destroyed.
     */
    public static final int FIRE_ENEMY = 20;
    /**
     * Score assigned to user when boss destroyed.
     */
    public static final int BOSS = 50;

    /**
     * Empty private constructor for this utility class.
     */
    private Score() {
    }

}
