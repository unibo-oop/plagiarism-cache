package it.unibo.arkanoid.subject;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.model.Level;

/**
 * Represents a subclass of brick.
 */
public class IndestructibleBrick extends Brick {

    /**
     * 
     * @param x
     *            coordinate.
     * @param y
     *            coordinate.
     * @param width
     *            to determinate the occupation in the game's space.
     * @param height
     *            to determinate the occupation in the game's space.
     * @param level
     *            the level where the brick acts.
     */
    public IndestructibleBrick(final double x, final double y, final double width, final double height,
            final Level level) {
        super(x, y, width, height, level);
    }

    @Override
    public void handleBallCollision(final CollisionInfo c) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBrickValue() {
        return 0;
    }

}
