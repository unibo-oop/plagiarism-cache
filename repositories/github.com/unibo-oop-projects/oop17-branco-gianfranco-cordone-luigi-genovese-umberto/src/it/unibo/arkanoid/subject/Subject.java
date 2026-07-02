package it.unibo.arkanoid.subject;

import java.util.Optional;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.event.Event;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * An entity in the game world. That has a position, speed and a body.
 *
 */
public interface Subject {

    /**
     * Get the position of subject in the world.
     * 
     * @return x and y coordinate of subject.
     */
    Vector2D getPosition();

    /**
     * Set the position of subject in the world.
     * 
     * @param position
     *            is the representation of x and y coordinate.
     */
    void setPosition(Vector2D position);

    /**
     * It's useful to determinate if subject collide with another one.
     * 
     * @param subject
     *            in the world.
     * @return true if the subject collide with another one, false otherwise.
     */
    Optional<Vector2D> intersect(Subject subject);

    /**
     * Update the state of the subject.
     * 
     * @param deltaTime
     *            Elapsed time.
     */
    void update(double deltaTime);

    /**
     * Set the velocity of subject.
     * 
     * @param velocity
     *            of subject.
     */
    void setVelocity(Vector2D velocity);

    /**
     * Get the velocity of subject.
     * 
     * @return the value of the velocity
     */
    Vector2D getVelocity();

    /**
     * It'useful for determinate the Subject's width.
     * 
     * @return the subject's width.
     */
    double getWidth();

    /**
     * It'useful for determinate the Subject's height.
     * 
     * @return the subject's height.
     */
    double getHeight();

    /**
     * Setter for the Subject's width.
     * 
     * @param height
     *            for Subject's height.
     * 
     */
    void setHeight(double height);

    /**
     * Setter for the Subject's width.
     * 
     * @param width
     *            for Subject's width.
     * 
     */
    void setWidth(double width);

    /**
     * 
     * @return a subjectType
     */
    SubjectType getSubjectType();

    /**
     * 
     * @return collision event
     */
    Event<CollisionInfo> getCollisionEvent();

    /**
     * 
     * @return the event destroyed
     */
    Event<Subject> getDestroyedEvent();

    /**
     * 
     * @param collisionInfo
     *            information about collision.
     */
    void notifyCollision(CollisionInfo collisionInfo);
}
