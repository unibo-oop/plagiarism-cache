package model.utilities;

import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;

/**
 * 
 * Utility for velocity.
 *
 */
public final class StaticVelocity {

    private StaticVelocity() {

    }

    /**
     * 
     * @param bulletVelocity
     *            the bullet's velocity.
     * @param degreeDistance
     *            degree distance.
     * @return the new velocity.
     */
    public static Velocity velocityByDegree(final Velocity bulletVelocity, final double degreeDistance) {
        final double base = Math
                .toDegrees(Math.atan(bulletVelocity.getY() / (bulletVelocity.getX() != 0 ? bulletVelocity.getX() : 0)));
        final double speed = Math.sqrt(Math.pow(bulletVelocity.getX(), 2) + Math.pow(bulletVelocity.getY(), 2));
        return new VelocityImpl(Math.cos(Math.toRadians(base + degreeDistance)) * speed,
                Math.sin(Math.toRadians(base + degreeDistance)) * speed);
    }

    /**
     * 
     * @param velocity
     *            the velocity's entity.
     * @return the velocity for diagonal.
     */
    public static Double diagonalVelocity(final double velocity) {
        return Math.sqrt(Math.pow(velocity, 2) / 2);
    }

    /**
     * Method that calculate a new velocity based on the module.
     * 
     * @param velocity
     *            initial
     * @param speed
     *            absolute speed
     * @return the new velocity
     */
    public static Velocity setAbsoluteSpeed(final Velocity velocity, final int speed) {
        final double scale = Math
                .sqrt(Math.pow(speed, 2) / (Math.pow(velocity.getX(), 2) + Math.pow(velocity.getY(), 2)));

        return new VelocityImpl(velocity.getX() * scale, velocity.getY() * scale);
    }

}
