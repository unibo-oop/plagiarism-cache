package it.unibo.arkanoid.subject;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * Represent the rectangular object controlled by player.
 * 
 *
 */
public class Paddle extends AbstractSubject {

    /**
     * Constructor to create paddle, with x and y coordinate and width and height to
     * describe his body.
     * 
     * @param x
     *            coordinate.
     * @param y
     *            coordinate.
     * @param width
     *            to determinate the occupation in the game's space.
     * @param height
     *            to determinate the occupation in the games's space.
     * @param velocity
     *            The subject's velocity in the game's world.
     * @param level
     *            The level where Paddle is present.
     */
    public Paddle(final double x, final double y, final double width, final double height, final Vector2D velocity,
            final Level level) {
        super(x, y, width, height, velocity, level);
    }

    @Override
    protected void handleCollision(final CollisionInfo collision) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubjectType getSubjectType() {
        return SubjectType.PADDLE;
    }

}
