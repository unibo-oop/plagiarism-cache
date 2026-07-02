package it.unibo.model.world.impl;

import it.unibo.model.world.api.BoundWorld;

/**
 * Implementation of {@link BoundWorld}.
 */
public class BoundWorldImpl implements BoundWorld {

    /**
     * The bound of the world on the Y axis. 
     */
    private final BoundY boundY;

    /**
     * The bound of the world on the Y axis. 
     */
    private final Boundary boundX;

    /**
     * Creates a new {@link BoundWorldImpl} with the given bounds.
     * 
     * @param boundY the bound of the world on the Y axis
     * @param boundX the bound of the world on the X axis
     */
    public BoundWorldImpl(final BoundY boundY, final Boundary boundX) {
        this.boundY = boundY;
        this.boundX = boundX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoundY getBoundY() {
        return this.boundY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boundary getBoundX() {
        return this.boundX;
    }

}
