package it.unibo.arkanoid.subject;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.model.Level;

/**
 * 
 * Represents a subclass of {@link Brick}.
 *
 */
public class SimpleBrick extends Brick {

    private static final int VALUE = 5;

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
     *            the level where the brick acts
     */
    public SimpleBrick(final double x, final double y, final double width, final double height, final Level level) {
        super(x, y, width, height, level);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleBallCollision(final CollisionInfo c) {
        this.removeObject();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBrickValue() {
        return VALUE;
    }

}
