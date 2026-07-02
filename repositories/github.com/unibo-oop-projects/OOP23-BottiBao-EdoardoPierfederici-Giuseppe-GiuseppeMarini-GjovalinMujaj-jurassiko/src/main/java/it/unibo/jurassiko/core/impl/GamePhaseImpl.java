package it.unibo.jurassiko.core.impl;

import it.unibo.jurassiko.core.api.GamePhase;

/**
 * Implementation of the {@link GameEngine} interface.
 */
public class GamePhaseImpl implements GamePhase {

    private Phase index;

    /**
     * Constructor for the GamePhase.
     */
    public GamePhaseImpl() {
        index = Phase.PLACEMENT;
    }

    /**
     * Contructor used to clone a new istance.
     * 
     * @param gp gamePhase
     */
    public GamePhaseImpl(final GamePhase gp) {
        this.index = gp.getPhase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phase getPhase() {
        return index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPhase(final Phase phase) {
        index = phase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goNext() {
        index = Phase.values()[(index.ordinal() + 1) % Phase.values().length];
    }

}
