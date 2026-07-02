
package model.utilities;

/**
 * Contains information for powerup settings and default values.
 */
public final class PowerUpUtilities {

    /**
     * default damage dealt by the ball.
     */
    public static final int DEFAULT_BALL_DAMAGE = 1;

    /**
     * ball damage (modified  by powerups).
     */
    public static final int BALL_DAMAGE = 1;

    /**
     * damage modifier used by powerup to increase or decrease the damage dealt by the ball.
     */
    public static final int DAMAGE_MODIFIER = 1;

    /**
     * default active time for each powerup activation (seconds).
     */
    public static final long POWERUP_ACTIVE_TIME = 5;

    /**
     * default life modifier used by powerup to increase or decrease player's lives.
     */
    public static final int DEFAULT_LIFE_MODIFIER = 1;

    /**
     * speed modifier used by powerup to speed the ball up or down.
     */
    public static final double SPEED_MODIFIER = 0.1;

    /**
     * speed for the powerup drop.
     */
    public static final double POWERUP_DROP_SPEED = 0.2;

    /**
     * default string for the inactive powerup .
     */
    public static final String DEFAULT_PWUP_STRING = "--";


    /**
     * default drop direction for powerup on the Y axis.
     */
    public static final DirVector POWERUP_DROP_DIR = new DirVector(0, 1);

    private PowerUpUtilities() {

    }

}
