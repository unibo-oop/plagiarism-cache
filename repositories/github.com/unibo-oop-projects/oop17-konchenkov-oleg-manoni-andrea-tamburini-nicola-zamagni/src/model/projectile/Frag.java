package model.projectile;

import model.physics.collider.Polycollider2D;

/**
 *
 * Projectile concrete decorator to add a "fragment on impact" behavior.
 *
 * @author Nicola Zamagni
 *
 */
public class Frag extends ProjectileDecorator {

    private final int numberOfFragments;
    private Status status = Status.INITIAL;

    /**
     *
     * Constructor.
     *
     * @param projectile
     *            projectile
     * @param numberOfFragments
     *            number of fragment
     */
    protected Frag(final AbstractProjectile projectile, final int numberOfFragments) {
        super(projectile);
        this.numberOfFragments = numberOfFragments;
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
        if (status == Status.TO_FRAGMENT) {
            super.fragment(numberOfFragments);
            status = Status.FRAGMENTED;
        }
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
        super.getProjectile().collide(polycollider2D, timeStep,
                status == Status.FRAGMENTED ? 0.0 : coefficientOfRestitution);
        if (status == Status.INITIAL) {
            status = Status.TO_FRAGMENT;
        }
    }

}

enum Status {
    INITIAL, TO_FRAGMENT, FRAGMENTED
}
