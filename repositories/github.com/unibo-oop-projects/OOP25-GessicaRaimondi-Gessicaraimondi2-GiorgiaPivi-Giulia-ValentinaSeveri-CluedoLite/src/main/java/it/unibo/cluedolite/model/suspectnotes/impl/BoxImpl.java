package it.unibo.cluedolite.model.suspectnotes.impl;

import it.unibo.cluedolite.model.creationcards.impl.AbstractCard;
import it.unibo.cluedolite.model.suspectnotes.api.State;
import it.unibo.cluedolite.model.suspectnotes.api.Box;

/**
 * Implementation of {@link Box}.
 * Represents a single entry in the suspect notes,
 * storing a card and its current state.
 */
public class BoxImpl implements Box {
    private final AbstractCard name;
    private State state;

    /**
     * Creates a new {@link BoxImpl} with the given card.
     * The initial state is set to {@link State#POSSIBLE}.
     *
     * @param name the {@link AbstractCard} associated with this box
     */
    public BoxImpl(final AbstractCard name) {
        this.name = name;
        this.state = State.POSSIBLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void excludeCard() {
        this.state = State.EXCLUDED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractCard getCard() {
        return name;
    }
}
