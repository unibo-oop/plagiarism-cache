package it.unibo.pacman.model.entities;

import java.awt.Rectangle;

import it.unibo.pacman.model.utilities.Direction;
import it.unibo.pacman.model.utilities.Position;
import it.unibo.pacman.model.utilities.Status;

public interface Movable extends Entity {
    /**
     * Used to set a new position.
     * 
     * @param newPos {@link Position}.
     */
    void setPosition(Position newPos);

    /**
     * Used to get the direction of the entity.
     * 
     * @return the actual Direction {@link Direction}.
     */
    Direction getDirection();

    /**
     * Used to set the direction of the entity.
     * 
     * @param newDir the new direction to set.
     */
    void setDirection(Direction newDir);

    /**
     * Used to get the status of the entity.
     * 
     * @return the actual status of entity.
     */
    Status getStatus();

    /**
     * Used to set the status of the entity.
     * 
     * @param newStatus the status to set for the entity.
     */
    void setStatus(Status newStatus);

    /**
     * Used to get the bounds at an input position.
     * 
     * @param position the position to calculate rectangle from.
     * @return the rectangle {@link Rectangle} bounds for the entity at position
     *         {@link Position}.
     */
    Rectangle getBoundsAt(Position position);

    /**
     * Used to calculate the next position of the entity.
     * 
     * @return next position for the entity.
     */
    Position nextPosition();

}
