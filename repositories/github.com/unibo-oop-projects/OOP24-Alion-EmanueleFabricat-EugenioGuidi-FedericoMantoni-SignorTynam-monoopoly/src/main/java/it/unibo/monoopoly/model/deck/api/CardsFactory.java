package it.unibo.monoopoly.model.deck.api;

import java.util.List;

/**
 * The factory to create the cards.
 */
public interface CardsFactory {
    /**
     * Create the {@link List} of all {@link Card}s.
     * 
     * @return the {@link List} of the cards in an {@link Deck}
     */
    List<Card> createDeck();
}
