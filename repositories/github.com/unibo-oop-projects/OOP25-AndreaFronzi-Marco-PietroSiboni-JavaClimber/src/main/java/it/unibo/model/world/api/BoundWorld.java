package it.unibo.model.world.api;

import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;

/**
 * Interface representing the boundaries of the world.
 * It provides methods to access the vertical and horizontal boundaries of the
 * world.
 */
public interface BoundWorld {

    /**
     * Gets the vertical boundary of the world.
     * 
     * @return the BoundY instance representing the vertical boundary
     */
    BoundY getBoundY();

    /**
     * Gets the horizontal boundary of the world.
     * 
     * @return the Boundary instance representing the horizontal boundary
     */
    Boundary getBoundX();

}
