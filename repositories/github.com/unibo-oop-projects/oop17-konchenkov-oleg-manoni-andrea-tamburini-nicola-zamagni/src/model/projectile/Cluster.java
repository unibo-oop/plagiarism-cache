package model.projectile;

/**
 *
 * Projectile concrete decorator to add a cluster behavior.
 *
 * @author Nicola Zamagni
 *
 */
public class Cluster extends ProjectileDecorator {

    private final double fragmentTime;
    private final int numberOfFragments;
    private double elapsedTime;
    private boolean fragmented;

    /**
     *
     * Constructor.
     *
     * @param projectile
     *            projectile
     * @param fragmentTime
     *            time interval before fragmentation
     * @param numberOfFragments
     *            number of fragment
     */
    protected Cluster(final AbstractProjectile projectile, final double fragmentTime, final int numberOfFragments) {
        super(projectile);
        this.fragmentTime = fragmentTime;
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
        elapsedTime += timeStep;
        if (elapsedTime >= fragmentTime && !fragmented) {
            super.fragment(numberOfFragments);
            fragmented = true;
        }
        super.getProjectile().update(timeStep);
    }

}
