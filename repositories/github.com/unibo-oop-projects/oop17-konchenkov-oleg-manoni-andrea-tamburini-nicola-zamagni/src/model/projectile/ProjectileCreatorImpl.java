package model.projectile;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.particle.BasicParticleCreator;
import model.physics.particle.environment.Environment;

/**
 *
 * A projectile creator.
 *
 * @author Nicola Zamagni
 *
 */
public final class ProjectileCreatorImpl implements ProjectileCreator {

    private BasicProjectile createBasicProjectile(final ProjectileType projectileType, final Vector2D position,
            final Vector2D velocity, final Environment environment) {
        return new BasicProjectile(
                new BasicParticleCreator().createParticle(position, velocity, environment, projectileType.getMass(),
                        projectileType.getShape()),
                projectileType.getBlastRadius(), projectileType.getFragmentationParameters());
    };

    @Override
    public Projectile createProjectile(final ProjectileType projectileType, final Vector2D position,
            final Vector2D velocity, final Environment environment) {

        if (projectileType == ProjectileType.BOUNCY) {
            /* bouncy parameters */
            final double bouncingTime = 20.0;

            return new Bouncy(createBasicProjectile(projectileType, position, velocity, environment), bouncingTime);

        } else if (projectileType == ProjectileType.FRAG) {
            /* frag parameters */
            final int numberOfFragments = 5;

            return new Frag(createBasicProjectile(projectileType, position, velocity, environment), numberOfFragments);

        } else if (projectileType == ProjectileType.CLUSTER_EXPLOSIVE) {
            /* cluster parameters */
            final int numberOfFragments = 5;
            final double fragmentationTime = 5.0;

            return new Explosive(new Cluster(createBasicProjectile(projectileType, position, velocity, environment),
                    fragmentationTime, numberOfFragments));

        } else if (projectileType == ProjectileType.CLUSTER_BOUNCY) {
            /* cluster parameters */
            final int numberOfFragments = 5;
            final double fragmentationTime = 5.0;
            /* bouncy parameters */
            final double bouncingTime = 20.0;

            return new Bouncy(new Cluster(createBasicProjectile(projectileType, position, velocity, environment),
                    fragmentationTime, numberOfFragments), bouncingTime);

        } else if (projectileType == ProjectileType.CLUSTER_CLUSTER_EXPLOSIVE) {
            /* first cluster parameters */
            final int numberOfFragments1 = 2;
            final double fragmentationTime1 = 5.0;
            /* second cluster parameters */
            final int numberOfFragments2 = 3;
            final double fragmentationTime2 = 10.0;

            return new Explosive(
                    new Cluster(new Cluster(createBasicProjectile(projectileType, position, velocity, environment),
                            fragmentationTime1, numberOfFragments1), fragmentationTime2, numberOfFragments2));

        } else if (projectileType == ProjectileType.CLUSTER_CLUSTER_BOUNCY) {
            /* first cluster parameters */
            final int numberOfFragments1 = 2;
            final double fragmentationTime1 = 5.0;
            /* second cluster parameters */
            final int numberOfFragments2 = 3;
            final double fragmentationTime2 = 10.0;
            /* bouncy parameters */
            final double bouncingTime = 20.0;

            return new Bouncy(
                    new Cluster(new Cluster(createBasicProjectile(projectileType, position, velocity, environment),
                            fragmentationTime1, numberOfFragments1), fragmentationTime2, numberOfFragments2),
                    bouncingTime);

        } else {
            return new Explosive(createBasicProjectile(projectileType, position, velocity, environment));
        }
    }

}
