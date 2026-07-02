package model.projectile;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.collider.Polycollider2D;
import model.physics.particle.environment.Environment;
import model.physics.particle.shape.ParticleShape;

/**
 *
 * Projectile abstract decorator.
 *
 * @author Nicola Zamagni
 *
 */
public abstract class ProjectileDecorator extends AbstractProjectile {

    private final AbstractProjectile projectile;

    /**
     *
     * Constructor.
     *
     * @param projectile
     *            projectile
     */
    protected ProjectileDecorator(final AbstractProjectile projectile) {
        super();
        this.projectile = projectile;
    }

    /**
     *
     * Returns the projectile.
     *
     * @return projectile
     */
    protected AbstractProjectile getProjectile() {
        return projectile;
    }

    /**
     *
     * Returns the previous position of the projectile.
     *
     * @return previous position, in metre
     */
    @Override
    public List<Vector2D> getPreviousPosition() {
        return projectile.getPreviousPosition();
    }

    /**
     *
     * Returns the previous velocity of the projectile.
     *
     * @return previous velocity, in metres per second
     */
    @Override
    public List<Vector2D> getPreviousVelocity() {
        return projectile.getPreviousVelocity();
    }

    /**
     *
     * Returns the previous acceleration of the projectile.
     *
     * @return previous acceleration, in metres per second squared
     */
    @Override
    public List<Vector2D> getPreviousAcceleration() {
        return projectile.getPreviousAcceleration();
    }

    /**
     *
     * Returns the current position of the projectile.
     *
     * @return current position, in metre
     */
    @Override
    public List<Vector2D> getPosition() {
        return projectile.getPosition();
    }

    /**
     *
     * Returns the current velocity of the projectile.
     *
     * @return current velocity, in metres per second
     */
    @Override
    public List<Vector2D> getVelocity() {
        return projectile.getVelocity();
    }

    /**
     *
     * Returns the current acceleration of the projectile.
     *
     * @return current acceleration, in metres per second squared
     */
    @Override
    public List<Vector2D> getAcceleration() {
        return projectile.getAcceleration();
    }

    /**
     *
     * Returns if the projectile is stationary.
     *
     * @return true if the projectile is stationary, false otherwise
     */
    @Override
    public boolean isStationary() {
        return projectile.isStationary();
    }

    /**
     *
     * Returns the mass of the projectile.
     *
     * @return mass, in kilogram
     */
    @Override
    public List<Double> getMass() {
        return projectile.getMass();
    };

    /**
     *
     * Returns the environment where the physical phenomenon takes place.
     *
     * @return environment
     */
    @Override
    public List<Environment> getEnvironment() {
        return projectile.getEnvironment();
    };

    /**
     *
     * Returns the shape of the projectile.
     *
     * @return shape
     */
    @Override
    public List<ParticleShape> getShape() {
        return projectile.getShape();
    };

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
    @Override
    public void update(final double timeStep) throws IllegalArgumentException {
        projectile.update(timeStep);
    }

    /**
     *
     * Calculates the time of collision and recalculates new velocities at the time
     * of collision of the projectile, after a collision with a polycollider.
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
    @Override
    public void collide(final Polycollider2D polycollider2D, final double timeStep,
            final double coefficientOfRestitution) throws IllegalArgumentException {
        projectile.collide(polycollider2D, timeStep, coefficientOfRestitution);

    }

    /**
     *
     * Returns the kinetic energy of the projectile.
     *
     * @return kinetic energy, in joule
     */
    @Override
    public final List<Double> getKineticEnergy() {
        return projectile.getKineticEnergy();
    }

    /**
     *
     * Fragments the projectile in a certain number of fragments.
     *
     * @param numberOfFragment
     *            number of fragments
     */
    @Override
    protected void fragment(final int numberOfFragment) {
        projectile.fragment(numberOfFragment);
    }

    /**
     *
     * Returns the number of fragments of the projectile.
     *
     * @return number of fragments of the projectile
     */
    @Override
    public int getNumberOfFragments() {
        return projectile.getNumberOfFragments();
    }

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
    @Override
    public boolean checkAnyFragmentCollision(final Polycollider2D polycollider2D) {
        return projectile.checkAnyFragmentCollision(polycollider2D);
    }

    /**
     *
     * Returns if the polycollider is hitted by all the fragments of the projectile.
     *
     * @param polycollider2D
     *            polycollider2D
     * @return if the polycollider is hitted by all the fragments of the projectile
     */
    @Override
    public boolean checkAllFragmentCollision(final Polycollider2D polycollider2D) {
        return projectile.checkAllFragmentCollision(polycollider2D);
    }

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
     *             time step must be nonnegative and the coefficient of restitution
     *             in the range [0.0, 1.0]
     */
    @Override
    public List<Vector2D> getCollidingFragmentPosition(final Polycollider2D polycollider2D, final double timeStep)
            throws IllegalArgumentException {
        return projectile.getCollidingFragmentPosition(polycollider2D, timeStep);
    }

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
     *             time step must be nonnegative and the coefficient of restitution
     *             in the range [0.0, 1.0]
     */
    @Override
    public List<Double> getCollidingFragmentKineticEnergy(final Polycollider2D polycollider2D, final double timeStep)
            throws IllegalArgumentException {
        return projectile.getCollidingFragmentKineticEnergy(polycollider2D, timeStep);
    }

    /**
     *
     * Returns the landing points of the fragments of the projectile.
     *
     * @return the landing points of the fragments of the projectile, in metre
     */
    @Override
    public List<Vector2D> getFragmentLandingPoint() {
        return projectile.getFragmentLandingPoint();
    }

    /**
     *
     * Returns the blast radius of the projectile.
     *
     * @return blast radius, in metre
     */
    @Override
    public double getBlastRadius() {
        return projectile.getBlastRadius();
    }

}
