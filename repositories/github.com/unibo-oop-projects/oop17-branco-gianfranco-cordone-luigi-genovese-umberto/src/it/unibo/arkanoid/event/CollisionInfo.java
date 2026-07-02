package it.unibo.arkanoid.event;

import it.unibo.arkanoid.subject.Subject;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * 
 * this class is useful to know info about a collision.
 *
 */
public class CollisionInfo {

    private final Vector2D sideOfCollision;
    private final Subject subject;

    /**
     * 
     * @param pointOfCollision
     *            the collision point
     * @param subject
     *            the subject with collided
     */
    public CollisionInfo(final Vector2D pointOfCollision, final Subject subject) {
        this.sideOfCollision = pointOfCollision;
        this.subject = subject;
    }

    /**
     * 
     * @return the collision point
     */
    public Vector2D getSideOfCollision() {
        return sideOfCollision;
    }

    /**
     * 
     * @return the subject hit
     */
    public Subject getSubject() {
        return subject;
    }

}
