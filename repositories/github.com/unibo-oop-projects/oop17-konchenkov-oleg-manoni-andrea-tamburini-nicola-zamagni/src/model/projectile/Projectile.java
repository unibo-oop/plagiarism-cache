package model.projectile;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.collider.Polycollider2D;
import model.physics.particle.environment.Environment;
import model.physics.particle.shape.ParticleShape;

/**
 *
 * Represents a projectile.
 *
 * @author Nicola Zamagni
 *
 */
public interface Projectile {

    /**
     *
     * Returns the previous position of the projectile.
     *
     * @return previous position, in metre
     */
    List<Vector2D> getPreviousPosition();

    /**
     *
     * Returns the previous velocity of the projectile.
     *
     * @return previous velocity, in metres per second
     */
    List<Vector2D> getPreviousVelocity();

    /**
     *
     * Returns the previous acceleration of the projectile.
     *
     * @return previous acceleration, in metres per second squared
     */
    List<Vector2D> getPreviousAcceleration();

    /**
     *
     * Returns the current position of the projectile.
     *
     * @return current position, in metre
     */
    List<Vector2D> getPosition();

    /**
     *
     * Returns the current velocity of the projectile.
     *
     * @return current velocity, in metres per second
     */
    List<Vector2D> getVelocity();

    /**
     *
     * Returns the current acceleration of the projectile.
     *
     * @return current acceleration, in metres per second squared
     */
    List<Vector2D> getAcceleration();

    /**
     *
     * Returns if the projectile is stationary.
     *
     * @return true if the projectile is stationary, false otherwise
     */
    boolean isStationary();

    /**
     *
     * Returns the mass of the projectile.
     *
     * @return mass, in kilogram
     */
    List<Double> getMass();

    /**
     *
     * Returns the environment where the physical phenomenon takes place.
     *
     * @return environment
     */
    List<Environment> getEnvironment();

    /**
     *
     * Returns the shape of the projectile.
     *
     * @return shape
     */
    List<ParticleShape> getShape();

    /**
     *
     * Updates position, velocity and acceleration of the projectile, after a
     * specified time step.
     *
     * @param timeStep
     *            time step, in second (nonnegative)
     * @throws IllegalArgumentException
     *             time step must be nonnegative
     */
    void update(double timeStep) throws IllegalArgumentException;

    /**
     *
     * Updates position and velocity of the projectile right after a collision with
     * a polycollider.
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
     * Returns the kinetic energy of the projectile.
     *
     * @return kinetic energy, in joule
     */
    List<Double> getKineticEnergy();

    /**
     *
     * Returns the number of fragments of the projectile.
     *
     * @return number of fragments of the projectile
     */
    int getNumberOfFragments();

    /**
     *
     * Returns if the polycollider is hitted by at least one fragment of the
     * projectile.
     *
     * @param polycollider2D
     *            polycollider2D
     * @return if the polycollider is hitted by at least one fragment of the
     *         projectile
     */
    boolean checkAnyFragmentCollision(Polycollider2D polycollider2D);

    /**
     *
     * Returns if the polycollider is hitted by all the fragments of the projectile.
     *
     * @param polycollider2D
     *            polycollider2D
     * @return if the polycollider is hitted by all the fragments of the projectile
     */
    boolean checkAllFragmentCollision(Polycollider2D polycollider2D);

    /**
     * Returns the points where the fragments of the projectile hitted the
     * polycollider.
     *
     * @param polycollider2D
     *            polycollider2D
     * @param timeStep
     *            the time step when the collision occur, in second (nonnegative)
     * @return the points where the fragments of the projectile hitted the
     *         polycollider, in metre
     * @throws IllegalArgumentException
     *             time step must be nonnegative
     */
    List<Vector2D> getCollidingFragmentPosition(Polycollider2D polycollider2D, double timeStep)
            throws IllegalArgumentException;

    /**
     *
     * Returns the kinetic energy of the fragments of the projectile that hitted the
     * polycollider.
     *
     * @param polycollider2D
     *            polycollider2D
     * @param timeStep
     *            the time step when the collision occur, in second (nonnegative)
     * @return kinetic energy of the fragments of the projectile, in joule
     * @throws IllegalArgumentException
     *             time step must be nonnegative
     */
    List<Double> getCollidingFragmentKineticEnergy(Polycollider2D polycollider2D, double timeStep)
            throws IllegalArgumentException;

    /**
     *
     * Returns the landing points of the fragments of the projectile.
     *
     * @return the landing points of the fragments of the projectile, in metre
     */
    List<Vector2D> getFragmentLandingPoint();

    /**
     *
     * Returns the blast radius of the projectile.
     *
     * @return blast radius, in metre
     */
    double getBlastRadius();

}
