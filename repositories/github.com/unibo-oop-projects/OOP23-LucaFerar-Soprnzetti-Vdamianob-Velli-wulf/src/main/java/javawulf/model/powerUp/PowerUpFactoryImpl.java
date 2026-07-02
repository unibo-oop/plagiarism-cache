package javawulf.model.powerup;

import javawulf.model.Coordinate;

/**
 * PowerUpFactoryImpl is the implementation of PowerUpFactory.
 */
public final class PowerUpFactoryImpl implements PowerUpFactory {

    /**
     *  Param for Attack powerUp (duration).
     */
    public static final int DURATION_ATTACK = 20;
    /**
     *  Param for Attack powerUp (points).
     */
    public static final int POINTS_ATTACK = 50;
    /**
     *  Param for Attack powerUp (type).
     */
    public static final String TYPE_ATTACK = "Attack";
    /**
     *  Param for Double points powerUp (duration).
     */
    public static final int DURATION_DOUBLEPOINTS = 30;
    /**
     *  Param for Double points powerUp (points).
     */
    public static final int POINTS_DOUBLEPOINTS = 100;
    /**
     *  Param for Double points powerUp (type).
     */
    public static final String TYPE_DOUBLEPOINTS = "DoublePoints";
    /**
     *  Param for Invincibility powerUp (duration).
     */
    public static final int DURATION_INVINCIBILITY = 10;
    /**
     *  Param for Invincibility powerUp (points).
     */
    public static final int POINTS_INVINCIBILITY = 100;
    /**
     *  Param for Invincibility powerUp (type).
     */
    public static final String TYPE_INVINCIBILITY = "Invincibility";
    /**
     *  Param for Speed powerUp (duration).
     */
    public static final int DURATION_SPEED = 30;
    /**
     *  Param for Speed powerUp (points).
     */
    public static final int POINTS_SPEED = 50;
    /**
     *  Param for Speed powerUp (type).
     */
    public static final String TYPE_SPEED = "Speed";

    @Override
    public PowerUpAttack createPowerUpAttack(final Coordinate coordinates) {
        return new PowerUpAttack(coordinates, DURATION_ATTACK, POINTS_ATTACK, TYPE_ATTACK);
    }

    @Override
    public PowerUpDoublePoints createPowerUpDoublePoints(final Coordinate coordinates) {
        return new PowerUpDoublePoints(coordinates, DURATION_DOUBLEPOINTS, POINTS_DOUBLEPOINTS, TYPE_DOUBLEPOINTS);
    }

    @Override
    public PowerUpInvincibility createPowerUpInvincibility(final Coordinate coordinates) {
        return new PowerUpInvincibility(coordinates, DURATION_INVINCIBILITY, POINTS_INVINCIBILITY, TYPE_INVINCIBILITY);
    }

    @Override
    public PowerUpSpeed createPowerUpSpeed(final Coordinate coordinates) {
        return new PowerUpSpeed(coordinates, DURATION_SPEED, POINTS_SPEED, TYPE_SPEED);
    }

}
