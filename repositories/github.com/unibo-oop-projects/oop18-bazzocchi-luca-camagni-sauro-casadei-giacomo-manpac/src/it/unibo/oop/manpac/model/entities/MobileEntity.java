package it.unibo.oop.manpac.model.entities;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;

/**
 * Interface for any entity which can move.
 *
 */
public interface MobileEntity {

    /**
     * Setter for the entity's direction.
     * 
     * @param direction the new direction of the entity
     *
     * @return what happened after the change
     */
    Action setDirection(Directions direction);

    /**
     * Getter for the entity's direction.
     * 
     * @return the direction of the entity
     */
    Directions getDirection();

}
