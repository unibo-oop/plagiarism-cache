package spacesurvival.utilities;

public final class RoundUtils {

    /**
     * Increment of asteroids that spawn each round.
     */
    public static final int ASTEROID_INCREMENT_PER_ROUND = 1;

    /**
     * Increment of chase enemies that spawn each round.
     */
    public static final int CHASE_ENEMY_INCREMENT_PER_ROUND = 1;

    /**
     * Increment of fire enemies that spawn each round.
     */
    public static final int FIRE_ENEMY_INCREMENT_PER_ROUND = 1;

    /**
     * Round needed to spawn a boss.
     */
    public static final int ROUND_PER_BOSS = 5;

    /**
     * Minimum round needed for start spawning fire enemies.
     */
    public static final int FIRE_ENEMY_MINIMUM_ROUND = 6;

    /**
     * Max number of asteroids per round.
     */
    public static final int MAX_ASTEROID_PER_ROUND = 15;

    /**
     * Max number of chase enemies per round.
     */
    public static final int MAX_CHASE_ENEMY_PER_ROUND = 10;

    /**
     * Max number of fire enemies per round.
     */
    public static final int MAX_FIRE_ENEMY_PER_ROUND = 5;

    private RoundUtils() {
    }

}
