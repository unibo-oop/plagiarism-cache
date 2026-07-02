package model.physics.particle;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.particle.environment.Environment;
import model.physics.particle.environment.QuadraticDragEnvironment;
import model.physics.particle.shape.ParticleShape;

/**
 *
 * Represents a particle affected by fluid drag and an uniform gravitational
 * field. assuming that: the resistance of the fluid can be approximated only by
 * the quadratic term; the drag coefficient can be considered constant.
 *
 * @author Nicola Zamagni
 *
 */
public final class QuadraticDragParticle extends AbstractParticle {

    private static final double EPSILON = 10.0 * Double.MIN_VALUE;

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
    protected QuadraticDragParticle(final Vector2D position, final Vector2D velocity, final double mass,
            final Environment environment, final ParticleShape shape) {
        super(position, velocity, mass, environment, shape);
        setAcceleration(getNetGravitationalAcceleration(
                ((QuadraticDragEnvironment) getEnvironment()).getGravitationalAcceleration()));
        setPrevious();
    }

    /**
     *
     * Copy constructor.
     *
     * @param particle
     *            particle
     */
    public QuadraticDragParticle(final Particle particle) {
        super(particle);
        setAcceleration(getNetGravitationalAcceleration(
                ((QuadraticDragEnvironment) getEnvironment()).getGravitationalAcceleration()));
        setPrevious();
    }

    /* Returns net acceleration taking into account the buoyancy force */
    private Vector2D getNetGravitationalAcceleration(final Vector2D gravitationalAcceleration) {
        if (Math.abs(gravitationalAcceleration.getNorm()) >= EPSILON) {
            return gravitationalAcceleration
                    .add(gravitationalAcceleration.negate().scalarMultiply(1.0 / gravitationalAcceleration.getNorm())
                            .scalarMultiply(((QuadraticDragEnvironment) getEnvironment()).getFluidDensity()
                                    * getShape().getVolume() / getMass()));
        }
        return gravitationalAcceleration;
    }

    private Vector2D f1(final Vector2D velocity) {
        return velocity;
    }

    private Vector2D f2(final Vector2D velocity) {
        return getNetGravitationalAcceleration(
                ((QuadraticDragEnvironment) getEnvironment()).getGravitationalAcceleration())
                        .subtract(velocity.subtract(((QuadraticDragEnvironment) getEnvironment()).getFluidVelocity())
                                .scalarMultiply(getShape().getDragCoeffigent()
                                        * ((QuadraticDragEnvironment) getEnvironment()).getFluidDensity()
                                        * getShape().getReferenceArea() / 2 / getMass()
                                        * velocity.subtract(
                                                ((QuadraticDragEnvironment) getEnvironment()).getFluidVelocity())
                                                .getNorm()));
    }

    /* Runge–Kutta fourth-order method */
    @Override
    public void update(final double timeStep) throws IllegalArgumentException {
        if (timeStep < 0) {
            throw new IllegalArgumentException();
        }
        if (!isStationary()) {
            final Vector2D k1 = f1(getVelocity());
            final Vector2D l1 = f2(getVelocity());
            final Vector2D k2 = f1(getVelocity().add(l1.scalarMultiply(timeStep / 2.0)));
            final Vector2D l2 = f2(getVelocity().add(l1.scalarMultiply(timeStep / 2.0)));
            final Vector2D k3 = f1(getVelocity().add(l2.scalarMultiply(timeStep / 2.0)));
            final Vector2D l3 = f2(getVelocity().add(l2.scalarMultiply(timeStep / 2.0)));
            final Vector2D k4 = f1(getVelocity().add(l3.scalarMultiply(timeStep)));
            final Vector2D l4 = f2(getVelocity().add(l3.scalarMultiply(timeStep)));

            setPrevious();
            // CHECKSTYLE: MagicNumber OFF
            setPosition(getPosition().add(
                    k1.add(k2.scalarMultiply(2.0).add(k3.scalarMultiply(2.0).add(k4))).scalarMultiply(timeStep / 6.0)));
            setVelocity(getVelocity().add(
                    l1.add(l2.scalarMultiply(2.0).add(l3.scalarMultiply(2.0).add(l4))).scalarMultiply(timeStep / 6.0)));
            setAcceleration(f2(getVelocity()));
            // CHECKSTYLE: MagicNumber ON
        }
    }

}
