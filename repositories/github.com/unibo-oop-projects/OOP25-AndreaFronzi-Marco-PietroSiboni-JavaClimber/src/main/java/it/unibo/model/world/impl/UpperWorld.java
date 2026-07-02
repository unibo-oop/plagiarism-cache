package it.unibo.model.world.impl;

import it.unibo.model.world.api.AbstractQueueWorld;
import it.unibo.model.world.api.BoundWorld;

/**
 * Concrete implementation of the queue-based world.
 * Used to store entities that are waiting to be moved into the real world.
 */
public class UpperWorld extends AbstractQueueWorld {

    /**
     * Constructs a new UpperWorld.
     * 
     * @param boundWorld the bound of the world.
     */
    public UpperWorld(final BoundWorld boundWorld) {
        super(boundWorld);
    }

}
