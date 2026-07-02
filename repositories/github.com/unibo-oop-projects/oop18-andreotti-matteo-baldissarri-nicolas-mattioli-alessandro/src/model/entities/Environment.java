package model.entities;

import java.util.Optional;

import org.dyn4j.geometry.Rectangle;

/**
 * Models the game environment and the logical representation of the entities.
 */
public interface Environment {
    /**
     * @param position The position to compare
     * @return If the position is within the boundaries
     */
    boolean canMove(Position position);

    /**
     * Move the entity in a new position.
     * 
     * @param x The horizontal displacement
     * @param y The vertical displacement
     */
    void move(double x, double y);

    /**
     * Set entity's position.
     * 
     * @param position The new position
     */
    void setPosition(Position position);

    /**
     * @return The entity position
     */
    Position getPosition();

    /**
     * @return The boundaries of the entity's environment
     */
    Optional<Border> getBorders();

    /**
     * @return The shape of the entity
     */
    Rectangle getShape();

}
