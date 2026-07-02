package it.unibo.briscoola.model.api.deck;

import java.util.List;
import java.util.Optional;
import it.unibo.briscoola.model.api.card.Card;

/**
 * Interface of the Deck of the game, or pile of card not used.
 * 
 * @author Andrea Reggiani
 * 
 * @param <T> type of the vcards contained in the deck
 */
public interface Deck<T extends Card> {

    /**
     * Shuffle the deck randomicaly.
     */
    void shuffle();

    /**
     * Try to draw a card from the top, that is considered the end of the Deck.
     * 
     * @return an Optional
     */
    Optional<T> draw();

    /**
     * Is not removing any card,
     * is used only to display the card at the start of the match.
     * 
     * @return the Dominant seed for the match
     */
    Optional<T> getBriscolaSeed();

    /**
     * Return the number of cards left in the deck to display in the game.
     * 
     * @return the number of cards
     */
    int getCurrentSize();

    /**
     * Restart the deck when the match is over,
     * if the player want to start a new match but also to save the winning streak.
     * 
     * @param newInitialSetOfCards restart the deck
     */
    void refillDeck(List<T> newInitialSetOfCards);
}
