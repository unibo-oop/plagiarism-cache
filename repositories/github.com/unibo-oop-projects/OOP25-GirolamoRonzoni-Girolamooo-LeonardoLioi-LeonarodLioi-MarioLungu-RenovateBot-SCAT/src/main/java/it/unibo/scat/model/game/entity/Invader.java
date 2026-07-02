package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.Constants;
import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Invader" entity.
 */
public final class Invader extends AbstractEntity {

    private static long lastShotTime;
    private Direction currDirection = Direction.RIGHT;
    private Direction nextDirection = Direction.DOWN;

    /**
     * Creates a new invader entity.
     * 
     * @param type   the type of the invader.
     * @param x      the initial x coordinate
     * @param y      the initial y coordinate
     * @param width  the witdh of the invader
     * @param height the height of the invader
     * @param health the initial health of the invader
     * 
     */
    public Invader(final EntityType type, final int x, final int y, final int width, final int height,
            final int health) {
        super(type, x, y, width, height, health);
    }

    /**
     * Moves the invader based on the current direction.
     */
    public void move() {
        switch (currDirection) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case DOWN -> moveDown();
            default -> {
            }
        }
    }

    /**
     * Moves the invader one unit to the left.
     */
    private void moveLeft() {
        setPosition(getPosition().getX() - 1, getPosition().getY());
    }

    /**
     * Moves the invader one unit to the right.
     *
     */
    private void moveRight() {
        setPosition(getPosition().getX() + 1, getPosition().getY());
    }

    /**
     * Moves the invader down by one unit.
     */
    private void moveDown() {
        setPosition(getPosition().getX(), getPosition().getY() + 1);
    }

    /**
     * Returns the current movement direction of the Invader.
     * 
     * @return the current {@link Direction}
     */
    public Direction getCurrDirection() {
        return currDirection;
    }

    /**
     * Returns the next movement direction of the Invader.
     * 
     * @return the next {@link Direction}
     */
    public Direction getNextDirection() {
        return nextDirection;
    }

    /**
     * Reset the invader to its initial state.
     * Calls the reset and then restores the initial movement directions,
     * and resets the shooting colldown.
     * 
     */
    @Override
    public void reset() {

        super.reset();
        setCurrDirection(Direction.RIGHT);
        setNextDirection(Direction.DOWN);
        setLastShotTime(0);
    }

    /**
     * Sets the current movement direction of this invader.
     * 
     * @param dir the new current direction
     */
    public void setCurrDirection(final Direction dir) {
        currDirection = dir;
    }

    /**
     * Sets the next movement direction of this invader.
     * 
     * @param dir the next direction to be applied.
     */
    public void setNextDirection(final Direction dir) {
        nextDirection = dir;
    }

    /**
     * Returns the time of the last shot fired by an invader.
     * 
     * @return the last invader shot time
     */
    public static long getLastShotTime() {
        return lastShotTime;
    }

    /**
     * Updates the time of the last shot fired by an invader.
     * 
     * @param shotTime the time of the last shot
     */
    public static void setLastShotTime(final long shotTime) {
        lastShotTime = shotTime;
    }

    /**
     * @return the number of points dropped when it dies, based on the type of
     *         invader.
     */
    @Override
    public int getEntityPoints() {
        return switch (this.getType()) {
            case INVADER_1 -> Constants.POINTS_INVADER1;
            case INVADER_2 -> Constants.POINTS_INVADER2;
            case INVADER_3 -> Constants.POINTS_INVADER3;
            case BONUS_INVADER -> Constants.POINTS_BONUS_INVADER;
            default -> Constants.ZERO;
        };
    }
}
