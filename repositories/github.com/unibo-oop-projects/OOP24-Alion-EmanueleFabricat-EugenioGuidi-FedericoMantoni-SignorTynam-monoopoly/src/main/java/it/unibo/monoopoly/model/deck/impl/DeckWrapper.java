package it.unibo.monoopoly.model.deck.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.model.deck.api.Card;
import it.unibo.monoopoly.model.deck.api.Deck;

/**
 * Proxy of {@link DeckImpl}.
 */
public class DeckWrapper implements Deck {
    private final Deck deck;
    private static final String START_MESSAGE_ERROR = "The method";
    private static final String FINISH_MESSAGE_ERROR = "is not implemented in the Proxy pattern.";

    /**
     * Constructor of the proxy.
     * 
     * @param deck the real Deck.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern Proxy")
    public DeckWrapper(final Deck deck) {
        this.deck = deck;
    }

    /**
     * Proxy version.
     * {@inheritDoc}.
     */
    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'draw' " + FINISH_MESSAGE_ERROR);
    }

    /**
     * Proxy version.
     * {@inheritDoc}.
     */
    @Override
    public Card getActualCard() {
        return new CardImpl(this.deck.getActualCard().getEffectText(), this.deck.getActualCard().getMessage());
    }

    /**
     * Proxy version.
     * {@inheritDoc}.
     */
    @Override
    public void addPrisonCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'addPrisonCard' " + FINISH_MESSAGE_ERROR);
    }

}
