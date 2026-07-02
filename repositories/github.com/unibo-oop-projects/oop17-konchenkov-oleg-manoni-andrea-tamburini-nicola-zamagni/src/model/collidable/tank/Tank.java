package model.collidable.tank;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.collidable.Collidable;
import model.collidable.tank.color.TankColor;
import model.collidable.tank.shape.TankShape;
import model.physics.collider.Polycollider2D;

/**
 *
 * Represents a tank.
 *
 * @author Nicola Tamburini
 *
 */
public interface Tank extends Collidable {

    /**
     *
     * Returns the color of the tank.
     *
     * @return color of the tank
     */
    TankColor getColor();

    /**
     *
     * Returns the tank shape.
     *
     * @return the tank shape
     */
    TankShape getTankShape();

    /**
     *
     * Returns the scale factor.
     *
     * @return the scale factor
     */
    double getScaleFactor();

    /**
     *
     * Returns the outline points of the tank.
     *
     * @return outline points of the tank
     */
    List<Vector2D> getOutlinePoints();

    /**
     *
     * Returns the points of the turret.
     *
     * @return points of the turret
     */
    List<Vector2D> getTurretPoints();

    /**
     *
     * Returns the projectile emission point of the turret.
     *
     * @return points of the turret
     */
    Vector2D getTurretMuzzle();

    /**
     *
     * Returns the elevation angle of the turret.
     *
     * @return elevation angle, in radians
     */
    double getElevationAngle();

    /**
     *
     ** Sets the elevation angle of the turret.
     *
     * @param elevationAngle
     *            elevation angle, in radians
     */
    void setElevationAngle(double elevationAngle);

    /**
     *
     * Returns the initial speed of the projectile.
     *
     * @return initial speed, in metres per second
     */
    double getProjectileInitialSpeed();

    /**
     *
     * Sets the initial speed of the projectile.
     *
     * @param initialSpeed
     *            initial speed, in metres per second
     */
    void setProjectileInitialSpeed(double initialSpeed);

    /**
     *
     * Returns if the tank is destroyed.
     *
     * @return true if the tank is destroyed, false otherwise
     */
    boolean isDestroyed();

    /**
     *
     * Set the status of the tank.
     *
     * @param destroyed
     *            true if the tank is destroyed, false otherwise
     */
    void setDestroyed(boolean destroyed);

    /**
     *
     * Set the status of the tank to falling.
     *
     */
    void setFalling();

    /**
     *
     * Returns the explosion radius of the tank.
     *
     * @return explosion radius of the tank, in meters
     */
    double getExplosionRadius();

    /**
     *
     * Returns the previous position of the tank.
     *
     * @return previous position, in metre
     */
    Vector2D getPreviousPosition();

    /**
     *
     * Returns the previous velocity of the tank.
     *
     * @return previous velocity, in metres per second
     */
    Vector2D getPreviousVelocity();

    /**
     *
     * Returns the previous acceleration of the tank.
     *
     * @return previous acceleration, in metres per second squared
     */
    Vector2D getPreviousAcceleration();

    /**
     *
     * Returns the current position of the tank.
     *
     * @return current position, in metre
     */
    Vector2D getPosition();

    /**
     *
     * Returns the current velocity of the tank.
     *
     * @return current velocity, in metres per second
     */
    Vector2D getVelocity();

    /**
     *
     * Returns the current acceleration of the tank.
     *
     * @return current acceleration, in metres per second squared
     */
    Vector2D getAcceleration();

    /**
     *
     * Returns if the tank is stationary.
     *
     * @return true if the tank is stationary, false otherwise
     */
    boolean isStationary();

    /**
     *
     * Returns the mass of the tank.
     *
     * @return mass, in kilogram
     */
    double getMass();

    /**
     *
     * Returns the gravitational acceleration where the physical phenomenon takes
     * place.
     *
     * @return gravitational acceleration
     */
    Vector2D getGravitationalAcceleration();

    /**
     *
     * Updates position, velocity and acceleration of the tank, after a specified
     * time step.
     *
     * @param timeStep
     *            time step, in second (nonnegative)
     * @throws IllegalArgumentException
     *             time step must be nonnegative
     */
    void update(double timeStep) throws IllegalArgumentException;

    /**
     *
     * Updates the position (and in some cases also the velocity and acceleration)
     * of the tank, after a collision with an immovable objects.
     *
     * @param polycollider2D
     *            polycollider2D
     * @param timeStep
     *            the time step when the collision occur, in second (nonnegative)
     * @param coefficientOfRestitution
     *            coefficient of restitution [0.0, 1.0]
     * @throws IllegalArgumentException
     *             time step must be nonnegative and the coefficient of restitution
     *             in the range [0.0, 1.0]
     */
    void collide(Polycollider2D polycollider2D, double timeStep, double coefficientOfRestitution)
            throws IllegalArgumentException;

    /**
     *
     * Returns the kinetic energy of the tank.
     *
     * @return kinetic energy, in joule
     */
    double getKineticEnergy();

}
