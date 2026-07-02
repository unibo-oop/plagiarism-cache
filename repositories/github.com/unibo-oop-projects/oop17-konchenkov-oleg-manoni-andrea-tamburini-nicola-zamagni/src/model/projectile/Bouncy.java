package model.projectile;

import model.physics.collider.Polycollider2D;

/**
 *
 * Projectile concrete decorator to add a "bounce on impact" behavior.
 *
 * @author Nicola Zamagni
 *
 */
public class Bouncy extends ProjectileDecorator {

    private final double bouncingTime;
    private double elapsedTime;

    /**
     *
     * Constructor.
     *
     * @param projectile
     *            projectile
     * @param bouncingTime
     *            time interval before the projectile stops bouncing
     */
    protected Bouncy(final AbstractProjectile projectile, final double bouncingTime) {
        super(projectile);
        this.bouncingTime = bouncingTime;
    }

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
        elapsedTime += timeStep;
        super.getProjectile().update(timeStep);
    }

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
    @Override
    public void collide(final Polycollider2D polycollider2D, final double timeStep,
            final double coefficientOfRestitution) throws IllegalArgumentException {
        if (elapsedTime < bouncingTime) {
            super.getProjectile().collide(polycollider2D, timeStep, coefficientOfRestitution);
        } else {
            super.getProjectile().collide(polycollider2D, timeStep, 0.0);
        }
    }

}
