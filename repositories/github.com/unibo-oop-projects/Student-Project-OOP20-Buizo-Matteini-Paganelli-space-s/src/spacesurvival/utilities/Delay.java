package spacesurvival.utilities;

/**
 *  General delay times in milliseconds.
 */
public final class Delay {

    /**
     * The time between changing of direction for the random movement.
     */
    public static final long CHANGING_DIRECTION = 5000;

    /**
     * The time between every shot for the fire enemy.
     */
    public static final int FIRE_ENEMY_FIRING = 5000;

    /**
     * The time between every shot for the boss.
     */
    public static final int BOSS_FIRING = 3000;

    /**
     * The time between every changing of ammo for the boss.
     */
    public static final int BOSS_CHANGING_AMMO = 20_000;

    /**
     * The time for the fire effect.
     */
    public static final int FIRE_EFFECT = 1000;

    /**
     * The time for bonus spawning.
     */
    public static final int SPAWN_PERK = 40_000;

    /**
     * The time for waiting.
     */
    public static final int WAIT_IN_WHILE = 20;

    /**
     * The time to update loading progress bar. 
     */
    public static final long LOADING_UPDATE = 5;

    /**
     * The time to update animation's frames. 
     */
    public static final long ANIMATIONS = 200;

    /**
     * The time to update weapon. 
     */
    public static final long BULLET_SHOT = 100;

    private Delay() {
    }
}
