package model.physics.particle;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.particle.environment.Environment;
import model.physics.particle.environment.GravityOnlyEnvironment;
import model.physics.particle.environment.QuadraticDragEnvironment;
import model.physics.particle.shape.ParticleShape;

/**
 *
 * A particle creator.
 *
 * @author Nicola Zamagni
 *
 */
public final class BasicParticleCreator implements ParticleCreator {

    @Override
    public Particle createParticle(final Vector2D position, final Vector2D velocity, final Environment environment,
            final double mass, final ParticleShape shape) throws IllegalArgumentException {

        if (environment instanceof QuadraticDragEnvironment) {
            return new QuadraticDragParticle(position, velocity, mass, environment, shape);
        } else if (environment instanceof GravityOnlyEnvironment) {
            return new GravityOnlyParticle(position, velocity, mass, environment, shape);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Particle createParticle(final Particle particle) throws IllegalArgumentException {

        if (particle instanceof QuadraticDragParticle) {
            return new QuadraticDragParticle(particle);
        } else if (particle instanceof GravityOnlyParticle) {
            return new GravityOnlyParticle(particle);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
