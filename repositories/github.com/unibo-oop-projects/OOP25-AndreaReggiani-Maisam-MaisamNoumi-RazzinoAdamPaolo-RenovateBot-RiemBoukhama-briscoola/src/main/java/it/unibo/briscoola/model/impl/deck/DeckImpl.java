package it.unibo.briscoola.model.impl.deck;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.attributes.CardValue;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.impl.card.StandardCardImpl;

/**
 * Concrete implementation of a deck of cards.
 * This class extends {@link AbstractDeckImpl} and generates the deck by combining
 * all the seeds and values.
 * 
 * @author Andrea Reggiani
 */
public class DeckImpl extends AbstractDeckImpl<Card> {

    /**
     * Constructs and initializes a deck
     * and shuffles the deck to ensure
     * randomness at the start of the match.
     */
    public DeckImpl() {
        super();
        this.initializeBriscolaDeck();
        this.shuffle();
    }

    /**
     * Populates the deck.
     */
    private void initializeBriscolaDeck() {
        for (final CardSeed seed : CardSeed.values()) {
            for (final CardValue value : CardValue.values()) {
                this.addCard(new StandardCardImpl(value, seed));
            }
        }
    }
}
