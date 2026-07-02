package model.physics.particle;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.collider.Polycollider2D;
import model.physics.particle.environment.Environment;
import model.physics.particle.shape.ParticleShape;

/**
 *
 * Represent a particle.
 *
 * @author Nicola Zamagni
 *
 */
public interface Particle {

    /**
     * Returns the previous position of the particle.
     *
     * @return previous position, in metre
     */
    Vector2D getPreviousPosition();

    /**
     * Returns the previous velocity of the particle.
     *
     * @return previous velocity, in metres per second
     */
    Vector2D getPreviousVelocity();

    /**
     * Returns the previous acceleration of the particle.
     *
     * @return previous acceleration, in metres per second squared
     */
    Vector2D getPreviousAcceleration();

    /**
     * Returns the current position of the particle.
     *
     * @return current position, in metre
     */
    Vector2D getPosition();

    /**
     * Returns the current velocity of the particle.
     *
     * @return current velocity, in metres per second
     */
    Vector2D getVelocity();

    /**
     * Returns the current acceleration of the particle.
     *
     * @return current acceleration, in metres per second squared
     */
    Vector2D getAcceleration();

    /**
     *
     * Returns if the particle is stationary.
     *
     * @return true if the particle is stationary, false otherwise
     */
    boolean isStationary();

    /**
     *
     * Returns the mass of the particle.
     *
     * @return mass, in kilogram
     */
    double getMass();

    /**
     *
     * Returns the environment where the physical phenomenon takes place.
     *
     * @return environment
     */
    Environment getEnvironment();

    /**
     *
     * Returns the shape of the particle.
     *
     * @return shape
     */
    ParticleShape getShape();

    /**
     *
     * Updates position, velocity and acceleration of the particle, after a
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
     *            coefficient of restitution in the range [0.0, 1.0]
     * @throws IllegalArgumentException
     *             time step must be nonnegative and the coefficient of restitution
     *             in the range [0.0, 1.0]
     */
    void collide(Polycollider2D polycollider2D, double timeStep, double coefficientOfRestitution)
            throws IllegalArgumentException;

    /**
     *
     * Returns the kinetic energy of the particle.
     *
     * @return kinetic energy, in joule
     */
    double getKineticEnergy();

}
