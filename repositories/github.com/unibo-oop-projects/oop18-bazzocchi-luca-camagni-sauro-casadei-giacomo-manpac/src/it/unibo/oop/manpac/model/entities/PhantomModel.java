package it.unibo.oop.manpac.model.entities;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.score.Eatable;

/**
 * Interface for any phantom.
 *
 */
public interface PhantomModel extends Eatable {

    /**
     * Getter for the phantom's name.
     * 
     * @return the phantom's name
     *
     */
    Entity getName();

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
    boolean isFeared();

    /**
     * Generate a new direction for the phantom.
     * 
     * @param generator the strategy of direction's generation
     * @return what happened after the direction change
     *
     */
    Action generateDirection(PhantomDirectionGenerator generator);

}
