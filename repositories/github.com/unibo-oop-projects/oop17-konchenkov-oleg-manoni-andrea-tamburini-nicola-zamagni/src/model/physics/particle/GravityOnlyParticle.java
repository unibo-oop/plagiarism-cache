package model.physics.particle;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.particle.environment.Environment;
import model.physics.particle.environment.GravityOnlyEnvironment;
import model.physics.particle.shape.ParticleShape;

/**
 *
 * Represents a particle affected only by an uniform gravitational field.
 *
 * @author Nicola Zamagni
 *
 */
public final class GravityOnlyParticle extends AbstractParticle {

    /**
     *
     * Constructor.
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
     */
    protected GravityOnlyParticle(final Vector2D position, final Vector2D velocity, final double mass,
            final Environment environment, final ParticleShape shape) {
        super(position, velocity, mass, environment, shape);
        setAcceleration(((GravityOnlyEnvironment) getEnvironment()).getGravitationalAcceleration());
        setPrevious();
    }

    /**
     *
     * Copy constructor.
     *
     * @param particle
     *            particle
     */
    public GravityOnlyParticle(final Particle particle) {
        super(particle);
        setAcceleration(((GravityOnlyEnvironment) getEnvironment()).getGravitationalAcceleration());
        setPrevious();
    }

    @Override
    public void update(final double timeStep) throws IllegalArgumentException {
        if (timeStep < 0) {
            throw new IllegalArgumentException();
        }
        if (!isStationary()) {
            setPrevious();
            setPosition(getPosition()
                    .add(getVelocity().scalarMultiply(timeStep).add(((GravityOnlyEnvironment) getEnvironment())
                            .getGravitationalAcceleration().scalarMultiply(Math.pow(timeStep, 2) * 0.5))));
            setVelocity(getVelocity().add(((GravityOnlyEnvironment) getEnvironment()).getGravitationalAcceleration()
                    .scalarMultiply(timeStep)));
        }

    }

}
