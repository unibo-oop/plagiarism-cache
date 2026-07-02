package it.unibo.arkanoid.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.event.Event;
import it.unibo.arkanoid.event.EventSource;
import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * Abstract class for {@link Subject} in the game's world.
 *
 */
public abstract class AbstractSubject implements Subject {

    private Vector2D position;
    private Vector2D velocity;
    private double width;
    private double height;
    private final Level currentLevel;
    private final Event<CollisionInfo> collisionEvent;
    private final Event<Subject> destroyEvent;
    private final List<Subject> collidingSubjects;

    /**
     * 
     * @param x
     *            coordinate.
     * @param y
     *            coordinate.
     * @param velocity
     *            represent the velocity of subject.
     * @param width
     *            for the bounds of subject.
     * @param height
     *            for the bounds of subject.
     * @param level
     *            the level where this subject is used
     */

    public AbstractSubject(final double x, final double y, final double width, final double height,
            final Vector2D velocity, final Level level) {
        this.position = new Vector2D(x, y);
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.currentLevel = level;
        this.collisionEvent = new EventSource<>();
        this.collisionEvent.register(this::handleCollision);
        this.destroyEvent = new EventSource<>();
        this.collidingSubjects = new ArrayList<>();
        this.collisionEvent.register(c -> this.collidingSubjects.add(c.getSubject()));
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D getPosition() {
        return new Vector2D(this.position.getX(), this.position.getY());
    }

    /**
     * {@inheritDoc}
     */
    public void setPosition(final Vector2D position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    public Optional<Vector2D> intersect(final Subject subject) {
        final double w = 0.5 * (this.width + subject.getWidth());
        final double h = 0.5 * (this.height + subject.getHeight());
        final double dx = this.position.getX() - subject.getPosition().getX();
        final double dy = this.position.getY() - subject.getPosition().getY();

        if (Math.abs(dx) <= w && Math.abs(dy) <= h) {
            final double wy = w * dy;
            final double hx = h * dx;

            if (wy > hx) {
                if (wy > -hx) {
                    return Optional.of(Vector2D.DOWN);
                } else {
                    return Optional.of(Vector2D.RIGHT);
                }
            } else {
                if (wy > -hx) {
                    return Optional.of(Vector2D.LEFT);
                } else {
                    return Optional.of(Vector2D.UP);
                }
            }
        }
        return Optional.empty();

    }

    /**
     * {@inheritDoc}
     */
    public void update(final double deltaTime) {
        this.setPosition(this.getPosition().sumVector(this.getVelocity().multiply(deltaTime)));
        this.collidingSubjects.removeAll(this.collidingSubjects.stream()
                                                               .filter(s -> !this.intersect(s).isPresent())
                                                               .collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    public void setVelocity(final Vector2D velocity) {
        this.velocity = velocity;
    }

    /**
     * {@inheritDoc}
     */
    public Vector2D getVelocity() {
        return this.velocity;
    }

    /**
     * {@inheritDoc}
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    public void setHeight(final double height) {
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    public void setWidth(final double width) {
        this.width = width;
    }

    /**
     * 
     * @return The actual level
     */
    protected Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * 
     * @return collision event
     */
    public Event<CollisionInfo> getCollisionEvent() {
        return collisionEvent;
    }

    /**
     * 
     */
    protected final void removeObject() {
        this.destroyEvent.trigger(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event<Subject> getDestroyedEvent() {
        return this.destroyEvent;
    }

    /**
     * 
     * @param collision
     *            information about the Collision.
     */
    protected abstract void handleCollision(CollisionInfo collision);

    @Override
    public final void notifyCollision(final CollisionInfo collisionInfo) {
        if (!this.collidingSubjects.contains(collisionInfo.getSubject())) {
            this.collisionEvent.trigger(collisionInfo);
        }
    }

}
