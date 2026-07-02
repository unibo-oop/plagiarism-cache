package model.physics.particle;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.particle.environment.Environment;
import model.physics.particle.shape.ParticleShape;

/**
 *
 * A particle creator.
 *
 * @author Nicola Zamagni
 *
 */
public interface ParticleCreator {

    /**
     *
     * Particle creator.
     *
     * @param position
     *            position, in metre
     * @param velocity
     *            velocity, in metres per second
     * @param environment
     *            environment
     * @param mass
     *            mass, in kilogram
     * @param shape
     *            shape
     * @return particle
     *
     * @throws IllegalArgumentException
     *             environment must be an instance of an existing implementation
     */
    Particle createParticle(Vector2D position, Vector2D velocity, Environment environment, double mass,
            ParticleShape shape) throws IllegalArgumentException;

    /**
     *
     * Particle creator.
     *
     * @param particle
     *            particle
     * @return particle
     * @throws IllegalArgumentException
     *             particle must be an instance of an existing implementation
     */
    Particle createParticle(Particle particle) throws IllegalArgumentException;
}
