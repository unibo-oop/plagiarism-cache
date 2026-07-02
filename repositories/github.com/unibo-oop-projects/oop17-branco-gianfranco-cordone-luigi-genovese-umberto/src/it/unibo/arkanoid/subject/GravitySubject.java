package it.unibo.arkanoid.subject;

import it.unibo.arkanoid.utility.Vector2D;

/**
 * This class represent the gravity for the {@link Subject}.
 *
 */
public class GravitySubject extends SubjectDecorator {

    private final Vector2D gravity;

    /**
     * Constructor for Gravity, with two arguments.
     * 
     * @param subject
     *                The {@link Subject} to which gravity should be applied.
     * @param gravity
     *                The {@link Vector2D} that represents gravity.
     */
    public GravitySubject(final Subject subject, final Vector2D gravity) {
        super(subject);
        this.gravity = gravity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double deltaTime) {
        this.setVelocity(this.getVelocity().sumVector(this.gravity.multiply(deltaTime)));
        super.update(deltaTime);
    }
}
