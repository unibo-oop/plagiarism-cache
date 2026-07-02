package it.unibo.arkanoid.subject;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.model.Level;

/**
 * Represents a subclass of {@link Brick}.
 */

public class MultipleBrick extends Brick {

    private static final int VALUE = 10;
    private int lives;

    /**
     * 
     * @param lives
     *            live of the brick.
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
    public MultipleBrick(final int lives, final double x, final double y, final double width, final double height,
            final Level level) {
        super(x, y, width, height, level);
        this.lives = lives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleBallCollision(final CollisionInfo c) {
        this.lives--;
        if (lives <= 0) {
            this.removeObject();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBrickValue() {
        return VALUE;
    }

}
