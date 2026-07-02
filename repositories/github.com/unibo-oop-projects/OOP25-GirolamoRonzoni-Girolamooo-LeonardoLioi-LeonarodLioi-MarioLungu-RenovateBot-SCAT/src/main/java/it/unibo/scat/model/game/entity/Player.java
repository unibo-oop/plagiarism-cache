package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Player" entity.
 */
public class Player extends AbstractEntity {
    private static long lastShotTime;

    /**
     * Creates a new Player entity.
     * 
     * @param type   the type of the player.
     * @param x      the initial x coordinate.
     * @param y      the initial y coordinate.
     * @param width  the witdh of the player.
     * @param height the height of the player.
     * @param health the initial health of the player.
     * 
     */
    public Player(final EntityType type, final int x, final int y, final int width, final int height,
            final int health) {
        super(type, x, y, width, height, health);
    }

    /**
     * @param direction the direction of the movement.
     * 
     */
    public void move(final Direction direction) {
        if (direction == Direction.LEFT) {
            moveLeft();
        } else {
            moveRight();
        }
    }

    /**
     * Moves the player one unit to the left.
     */
    private void moveLeft() {
        setPosition(getPosition().getX() - 1, getPosition().getY());
    }

    /**
     * Moves the player one unit to the right.
     */
    private void moveRight() {
        setPosition(getPosition().getX() + 1, getPosition().getY());
    }

    /**
     * Getter.
     * 
     * @return the last shot time
     * 
     */
    public static long getLastShotTime() {
        return lastShotTime;
    }

    /**
     * Setter.
     * 
     * @param shotTime the shotTime
     * 
     */
    public static void setLastShotTime(final long shotTime) {
        lastShotTime = shotTime;
    }

}
