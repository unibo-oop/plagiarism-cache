package it.unibo.arkanoid.subject;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * 
 * Implementation of the object Brick that must be destroyed.
 *
 */
public abstract class Brick extends AbstractSubject {

    /**
     * 
     * @param x
     *            a position x.
     * @param y
     *            a position y.
     * @param width
     *            to determinate the occupation in the game's space.
     * @param height
     *            to determinate the occupation in the game's space.
     * @param level
     *            The level where Brick is present.
     */

    public Brick(final double x, final double y, final double width, final double height, final Level level) {
        super(x, y, width, height, new Vector2D(0, 0), level);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubjectType getSubjectType() {
        return SubjectType.BRICK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleCollision(final CollisionInfo collision) {
        if (collision.getSubject().getSubjectType() == SubjectType.BALL) {
            this.handleBallCollision(collision);
        }
    }

    /**
     * Method used when a brick is hit by the ball.
     * 
     * @param c
     *            object of CollisionInfo
     */
    public abstract void handleBallCollision(CollisionInfo c);

    /**
     * 
     * @return the value of the brick destroyed.
     */
    public abstract int getBrickValue();
}
