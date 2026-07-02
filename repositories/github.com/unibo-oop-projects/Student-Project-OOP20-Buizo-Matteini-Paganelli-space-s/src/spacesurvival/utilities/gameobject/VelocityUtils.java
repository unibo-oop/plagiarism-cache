package spacesurvival.utilities.gameobject;

import spacesurvival.model.common.V2d;

/**
 * Utils for velocity.
 */
public final class VelocityUtils {

    /**
     * Space ship rotation velocity.
     */
    public static final double SPACESHIP_ROTATION = 15;

    /**
     * Space ship acceleration.
     */
    public static final double SPACESHIP_ACCELERATION = 1.15;

    /**
     * Space ship deceleration.
     */
    public static final double SPACESHIP_DECELERATION = 0.8;

    /**
     * No acceleration.
     */
    public static final double NO_ACCELERATION = 0;

    /**
     * Space ship starting velocity.
     */
    public static final int SPACESHIP_STARTING_VEL = 5;

    /**
     * Space ship starting velocity.
     */
    public static final int SPACESHIP_TOLLERANCE_RESTART = 1;

    /**
     * Max velocity which space ship can reach.
     */
    public static final int SPACESHIP_MAX_VELOCITY = 20;

    /**
     * Space ship initial velocity.
     */
    public static final V2d SPACESHIP_VEL = new V2d(0, 0);

    /**
     * Asteroid fixed velocity.
     */
    public static final V2d ASTEROID_VEL = new V2d(5, 0);

    /**
     * Chase enemy fixed velocity.
     */
    public static final V2d CHASE_ENEMY_VEL = new V2d(0, -3);

    /**
     * Fire enemy fixed velocity.
     */
    public static final V2d FIRE_ENEMY_VEL = new V2d(0, -1);

    /**
     * Boss fixed velocity.
     */
    public static final V2d BOSS_VEL = new V2d(0, -1);

    /**
     * Bullet fixed velocity.
     */
    public static final V2d BULLET_VEL = new V2d(0, -40);

    /**
     * No velocity.
     */
    public static final V2d NO_VELOCITY = new V2d(0, 0);

    private VelocityUtils() {
    }

}
