package it.unibo.oop.manpac.view.maze.entities;

import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;

/**
 * Interface for any phantom in view.
 *
 */
public interface Phantom {

    /**
     * Method to get the name of the entity.
     * 
     * @return The name
     */
    Entity getName();

    /**
     * Get the second-last direction of the Phantom.
     * 
     * @return The second-last direction
     */
    Directions getSecondLastDirection();

    /**
     * Setter for the phantom's fear.
     * 
     * @param fear the new fear status of a phantom
     *
     */
    void setFear(boolean fear);

    /**
     * Getter for the phantom's fear.
     * 
     * @return true if the phantom is feared
     *
     */
    boolean isScared();

    /**
     * Change the position of the phantom from inside the cell to the outside.
     */
    void goOut();
}
