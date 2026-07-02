package model.entities;

import model.property.Property;
import utils.Counter;

/**
 * Model the Stuntman.
 */
public interface Character extends Entity {

    /**
     * Set if the Stuntman is climbing a floor.
     * 
     * @param climbing The status of the stuntman
     */
    void setClimbing(boolean climbing);

    /**
     * @return If the Stuntman is climbing a floor
     */
    boolean isClimbing();

    /**
     * @throws UnsupportedOperationException {@link Stuntman} cannot move down
     */
    void moveDown();

    /**
     * @return The life of the character
     */
    Property getLife();

    /**
     * @return The floors scaled by the stuntman
     */
    Counter getCounterFloors();

}
