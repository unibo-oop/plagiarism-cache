package model.physics.particle;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.collider.Polycollider2D;
import model.physics.particle.environment.Environment;
import model.physics.particle.shape.ParticleShape;

/**
 *
 * Represents a particle.
 *
 * @author Nicola Zamagni
 *
 */
public abstract class AbstractParticle implements Particle {

    private static final double EPSILON = 10.0 * Double.MIN_VALUE;
    private static final double TIME_TOLERANCE = 1.0e-12; /* second */

    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    private Vector2D previousPosition;
    private Vector2D previousVelocity;
    private Vector2D previousAcceleration;

    private boolean stationary;

    private final double mass;

    private final Environment environment;

    private final ParticleShape shape;

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
     * @throws IllegalArgumentException
     *             mass must be positive
     */
    protected AbstractParticle(final Vector2D position, final Vector2D velocity, final double mass,
            final Environment environment, final ParticleShape shape) throws IllegalArgumentException {
        super();
        this.position = position;
        this.velocity = velocity;
        acceleration = Vector2D.ZERO;
        setPrevious();
        this.mass = mass;
        this.environment = environment;
        this.shape = shape;
        if (this.mass <= 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * Copy constructor.
     *
     * @param particle
     *            particle
     */
    protected AbstractParticle(final Particle particle) {
        this(particle.getPosition(), particle.getPreviousVelocity(), particle.getMass(), particle.getEnvironment(),
                particle.getShape());
    }

    @Override
    public final Vector2D getPosition() {
        return position;
    }

    @Override
    public final Vector2D getVelocity() {
        return velocity;
    }

    @Override
    public final Vector2D getAcceleration() {
        return acceleration;
    }

    @Override
    public final Vector2D getPreviousPosition() {
        return previousPosition;
    }

    @Override
    public final Vector2D getPreviousVelocity() {
        return previousVelocity;
    }

    @Override
    public final Vector2D getPreviousAcceleration() {
        return previousAcceleration;
    }

    /**
     *
     * Sets the current position of the particle.
     *
     * @param position
     *            position, in metre
     */
    protected final void setPosition(final Vector2D position) {
        this.position = position;
    }

    /**
     *
     * Sets the current velocity of the particle.
     *
     * @param velocity
     *            velocity, in metres per second
     */
    protected final void setVelocity(final Vector2D velocity) {
        this.velocity = velocity;
    }

    /**
     *
     * Sets the current acceleration of the particle.
     *
     * @param acceleration
     *            acceleration, in metres per second squared
     */
    protected final void setAcceleration(final Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    /**
     *
     * Sets the previous values of position, velocity and acceleration of the
     * particle.
     *
     */
    protected final void setPrevious() {
        previousPosition = position;
        previousVelocity = velocity;
        previousAcceleration = acceleration;
    }

    @Override
    public final boolean isStationary() {
        return stationary;
    }

    /**
     *
     * Set the status of the particle.
     *
     * @param stationary
     *            true if the particle is stationary, false otherwise
     */
    protected final void setStationary(final boolean stationary) {
        this.stationary = stationary;
        if (stationary) {
            setVelocity(Vector2D.ZERO);
        }
    }

    @Override
    public final double getMass() {
        return mass;
    }

    @Override
    public final Environment getEnvironment() {
        return environment;
    }

    @Override
    public final ParticleShape getShape() {
        return shape;
    }

    @Override
    public abstract void update(double timeStep) throws IllegalArgumentException;

    /* Function to minimize */
    private double getDistanceFromCollider(final Polycollider2D polycollider2D, final double time) {
        /* create a copy of the particle back in time of a timeStep */
        final Particle p = new BasicParticleCreator().createParticle(getPreviousPosition(), getPreviousVelocity(),
                getEnvironment(), getMass(), getShape());
        /* take the particle forward in time */
        p.update(time);
        /* return the distance of the particle from the polycollider */
        return polycollider2D.getDistanceFromPoint(p.getPosition(), p.getVelocity());
    }

    /* Golden-section search */
    private double getCollisionTime(final Polycollider2D polycollider2D, final double ta, final double tb) {
        double a = Math.min(ta, tb);
        double b = Math.max(ta, tb);

        final double r = (Math.sqrt(5.0) - 1.0) / 2.0;
        final int iterationLimit = (int) Math.ceil(Math.log(TIME_TOLERANCE / (b - a)) / Math.log(r));
        double x1 = r * (a - b) + b;
        double x2 = r * (b - a) + a;
        double f1 = Math.abs(getDistanceFromCollider(polycollider2D, x1));
        double f2 = Math.abs(getDistanceFromCollider(polycollider2D, x2));

        for (int i = 0; b - a >= TIME_TOLERANCE && i <= iterationLimit; i++) {
            if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = r * (a - b) + b;
                f1 = Math.abs(getDistanceFromCollider(polycollider2D, x1));
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = r * (b - a) + a;
                f2 = Math.abs(getDistanceFromCollider(polycollider2D, x2));
            }
        }

        return getDistanceFromCollider(polycollider2D, Math.min(a, b)) >= 0 ? Math.min(a, b) : Math.min(ta, tb);
    }

    /* Fix cinematic quantities after collision */
    private void collisionFix(final Polycollider2D polycollider2D, final double timeStep,
            final double coefficientOfRestitution) {
        /* find collision time */
        final double tc = getCollisionTime(polycollider2D, 0.0, timeStep);
        /* create a copy of the particle back in time of a timeStep */
        final Particle p = new BasicParticleCreator().createParticle(getPreviousPosition(), getPreviousVelocity(),
                getEnvironment(), getMass(), getShape());
        /* take the particle forward in time to the time of collision */
        p.update(tc);
        /* find the normal to the polycollider */
        final Vector2D normal = polycollider2D.getNormal(p.getPosition(), getPosition(), p.getVelocity(),
                getVelocity());
        /* reflect velocity vector */
        final Vector2D reflectedVelocity = normal.scalarMultiply(2.0 * normal.dotProduct(p.getVelocity().negate()))
                .add(p.getVelocity()).scalarMultiply(coefficientOfRestitution);
        /* fix cinematic quantities after collision */
        setPosition(p.getPosition());
        setVelocity(reflectedVelocity);
    }

    @Override
    public final void collide(final Polycollider2D polycollider2D, final double timeStep,
            final double coefficientOfRestitution) throws IllegalArgumentException {
        if (coefficientOfRestitution < 0.0 || coefficientOfRestitution > 1.0) {
            throw new IllegalArgumentException();
        }
        /* perfectly inelastic collision */
        if (Math.abs(coefficientOfRestitution) < EPSILON || Math.abs(getVelocity().getNorm()) < EPSILON) {
            collisionFix(polycollider2D, timeStep, coefficientOfRestitution);
            setStationary(true);
            /* other types of collision */
        } else {
            collisionFix(polycollider2D, timeStep, coefficientOfRestitution);
        }
    }

    @Override
    public final double getKineticEnergy() {
        return 0.5 * mass * Math.pow(getVelocity().getNorm(), 2);
    }

}
