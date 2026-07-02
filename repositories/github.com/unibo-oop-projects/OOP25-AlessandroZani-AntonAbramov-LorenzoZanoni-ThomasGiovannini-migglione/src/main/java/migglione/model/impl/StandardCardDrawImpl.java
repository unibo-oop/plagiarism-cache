package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;
import migglione.model.api.CardDraw;
import migglione.model.api.Deck;

/**
 * Standard implementation of CardDraw.
 * 
 * <p>
 * This rapresent the main method to draw cards from the deck,
 * ad is used in the main mode of the game.
 * 
 * <p>
 * When a player draws a card, it is removed from the set of
 * cards that can be extracted, so that the game can end
 * when there are no more cards in the deck and every player
 * has no cards in their hand.
 */
public final class StandardCardDrawImpl implements CardDraw {

    private final List<Card> cards;

    /**
     * Constructor of the class.
     * 
     * <p>
     * By using the deck in its definition, the constructor
     * makes that the cards that are able to be drawn
     * are the one shuffled in the chosen Deck implementation
     * 
     * @param deck is the deck which implementation can
     *             be freely chosen and will make the base of the
     *             cards that can be drawn after being shuffled
     */
    public StandardCardDrawImpl(final Deck deck) {
        deck.shuffle();
        this.cards = new ArrayList<>(deck.getDeck());
    }

    @Override
    public Card getCard() {
        return this.cards.removeFirst();
    }

    @Override
    public boolean isDeckEmpty() {
        return this.cards.isEmpty();
    }

    @Override
    public int getSizeDeck() {
        return this.cards.size();
    }
}
