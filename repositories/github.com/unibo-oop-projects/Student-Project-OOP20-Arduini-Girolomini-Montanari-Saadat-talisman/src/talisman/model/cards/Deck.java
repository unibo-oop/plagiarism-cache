package talisman.model.cards;

import java.util.List;
import java.util.Queue;

/**
 * Interface that models a deck.
 * 
 * @author Abtin Saadat
 * 
 */
public interface Deck {
    /**
     * 
     * @return The drawn card.
     */
    Card draw();
    /**
     * 
     * @return The number of cards in the deck.
     */
    List<Card> getCards();
    int getNumberOfCards();
    DeckType getType();
    void shuffle();
}
