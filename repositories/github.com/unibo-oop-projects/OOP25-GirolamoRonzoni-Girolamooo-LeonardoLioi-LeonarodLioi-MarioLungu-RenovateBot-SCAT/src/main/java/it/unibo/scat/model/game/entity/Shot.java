package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Shot" entity.
 */
public class Shot extends AbstractEntity {
    private final Direction direction;

    /**
     * Creates a new Shot entity.
     * 
     * @param type      the type of the shot.
     * @param x         the initial x coordinate.
     * @param y         the initial y coordinate.
     * @param width     the witdh of the shot.
     * @param height    the height of the shot.
     * @param health    the initial health of the shot.
     * @param direction the direction of the shot.
     * 
     */
    public Shot(final EntityType type, final int x, final int y, final int width, final int height,
            final int health, final Direction direction) {
        super(type, x, y, width, height, health);
        this.direction = direction;
    }

    /**
     * Moves the shot in the direction it was fired.
     * The position is updated by one unit vertically, depending on the direction.
     */
    public void move() {
        switch (direction) {
            case UP:
                setPosition(getPosition().getX(), getPosition().getY() - 1);
                break;
            case DOWN:
                setPosition(getPosition().getX(), getPosition().getY() + 1);
                break;
            default:
                break;
        }
    }

    /**
     * Getter.
     * 
     * @return the direction of the shot.
     * 
     */
    public Direction getDirection() {
        return direction;
    }
}
