package model.projectile;

import model.physics.collider.Polycollider2D;

/**
 *
 * Projectile concrete decorator to add an "explode on impact" behavior.
 *
 * @author Nicola Zamagni
 *
 */
public class Explosive extends ProjectileDecorator {

    /**
     *
     * Constructor.
     *
     * @param projectile
     *            projectile
     */
    protected Explosive(final AbstractProjectile projectile) {
        super(projectile);
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
        super.getProjectile().collide(polycollider2D, timeStep, 0.0);
    }

}
