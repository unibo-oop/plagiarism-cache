package model.entities;

import java.util.Optional;

/**
 * An interface modeling a dynamic entity with methods to manage the current
 * position/direction.
 *
 */

public interface DynamicEntity extends Entity {

    /**
     * Moves the character in the dir direction.
     * 
     * @param dir
     *            The direction chosen.
     */
    void move(Optional<Movement> dir);

    /**
     * This methods returns the current direction.
     * 
     * @return A movement representing the current direction.
     */
    Movement getCurrentDirection();

    /**
     * Sets the new direction for the entity.
     * 
     * @param dir
     *            The new direction.
     */
    void setDirection(Movement dir);

    /**
     * Sets the current status of the entity.
     * 
     * @param status
     *            An Enum describing the entity's current status.
     */
    void setStatus(EntityStatus status);

    /**
     * Returns the current Entity status.
     * 
     * @return An Enum entry describing the entity status.
     */
    EntityStatus getStatus();

    /**
     * Method to update the entity : e.g: apply gravity.
     */
    void update();

    /**
     * Adds a new movement to be processed by the entity, if it is not currently
     * possible, the thread is temporarily blocked.
     *
     * @param dir
     *            The new movement to process.
     */
    void addMovement(Movement dir);

}
