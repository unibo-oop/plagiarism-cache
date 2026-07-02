package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl;

import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Pair;

/**
 * Implementation of the {@link Pair} interface.
 * 
 * @param <X> the x object.
 * @param <Y> the y object.
 */
public class PairImpl<X, Y> implements Pair<X, Y> {

    /**
     * The x value.
     */
    private final X x;

    /**
     * The y value.
     */
    private final Y y;

    /**
     * Constructs a new PairImpl.
     * 
     * @param x the x value
     * @param y the y value
     */
    public PairImpl(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public X getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Y getY() {
        return this.y;
    }
}
