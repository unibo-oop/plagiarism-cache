package model.projectile;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.particle.environment.Environment;

/**
 *
 * A projectile creator.
 *
 * @author Nicola Zamagni
 *
 */
public interface ProjectileCreator {

    /**
     *
     * Particle creator.
     *
     * @param projectileType
     *            projectile type
     * @param position
     *            position, in metre
     * @param velocity
     *            velocity, in metres per second
     * @param environment
     *            environment
     * @return projectile
     */
    Projectile createProjectile(ProjectileType projectileType, Vector2D position, Vector2D velocity,
            Environment environment);

}
