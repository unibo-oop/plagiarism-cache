package it.unibo.arkanoid.subject;

import java.util.Optional;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.event.Event;
import it.unibo.arkanoid.event.EventSource;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * Decorator class of {@link Subject}.
 *
 */
public abstract class SubjectDecorator implements Subject {

    private final Subject subject;
    private final Event<Subject> destroyEvent;

    /**
     * 
     * @param subject
     *            The Subject to decorate.
     */
    public SubjectDecorator(final Subject subject) {
        this.subject = subject;
        this.destroyEvent = new EventSource<>();
        subject.getDestroyedEvent().register(s -> this.destroyEvent.trigger(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getPosition() {
        return this.subject.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Vector2D position) {
        this.subject.setPosition(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Vector2D> intersect(final Subject subject) {
        return this.subject.intersect(subject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double deltaTime) {
        this.subject.update(deltaTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final Vector2D velocity) {
        this.subject.setVelocity(velocity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getVelocity() {
        return this.subject.getVelocity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.subject.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.subject.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeight(final double height) {
        this.subject.setHeight(height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidth(final double width) {
        this.subject.setWidth(width);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubjectType getSubjectType() {
        return this.subject.getSubjectType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event<CollisionInfo> getCollisionEvent() {
        return this.subject.getCollisionEvent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event<Subject> getDestroyedEvent() {
        return this.destroyEvent;
    }

    @Override
    public final void notifyCollision(final CollisionInfo collisionInfo) {
        this.subject.notifyCollision(collisionInfo);
    }

    /**
     * Returns the subject decorated by this decorator.
     * 
     * @return the decorated subject.
     */
    public Subject getDecoratedSubject() {
        return this.subject;
    }
}
