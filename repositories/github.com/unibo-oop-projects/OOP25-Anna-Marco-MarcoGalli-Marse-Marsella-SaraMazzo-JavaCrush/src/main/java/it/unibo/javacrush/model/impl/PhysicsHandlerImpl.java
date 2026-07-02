package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.api.RefillEngine;
import it.unibo.javacrush.model.api.StallEngine;

/**
 * Implementation of the {@link PhysicsHandler} interface.
 */
public class PhysicsHandlerImpl implements PhysicsHandler {

    private GravityEngine gravity;
    private RefillEngine refill;
    private final StallEngine stallEngine;

    /**
     * Constructs a new {@link PhysicsHandlerImpl} with the specified gravity and stall engine.
     *
     * @param startGravity the initial gravity engine
     * @param stallEngine the stall engine
     */
    public PhysicsHandlerImpl(final GravityEngine startGravity, final StallEngine stallEngine) {
        this.stallEngine = stallEngine;
        setGravity(startGravity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(final Board board) {
        final boolean moved = gravity.applyGravity(board);
        final boolean refilled = refill.refill(board);
        return moved || refilled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setGravity(final GravityEngine newGravity) {
        this.gravity = newGravity;
        this.refill = new AdaptiveRefill(newGravity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeBoard(final Board board) {
        this.refill.refillAll(board);
        this.stallEngine.resolveStall(board);
    }
}
