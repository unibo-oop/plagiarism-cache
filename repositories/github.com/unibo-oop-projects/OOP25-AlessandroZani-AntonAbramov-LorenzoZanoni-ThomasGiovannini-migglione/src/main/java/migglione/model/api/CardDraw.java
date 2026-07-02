package migglione.model.api;

import migglione.model.impl.Card;

/**
 * Interface used as a reference for a Strategy method.
 * 
 * <p>
 * In fact, it grants the possibility to create different
 * implementation of different kinds of draws,
 * an essential peculiarity in case one would
 * want to add more game modes.
 */
public interface CardDraw {
    /**
     * A simple method to return the next card from the deck.
     * 
     * @return an Object Card, and depending on the
     *         implementation it can eliminate the card from
     *         the deck (standard) or it can change depending
     *         on what game mode is on.
     */
    Card getCard();

    /**
     * Checks the current state of the deck.
     * 
     * @return true if the deck is empty, false otherwise
     */
    boolean isDeckEmpty();

    /**
     * Getter for the size of the list of the cards remaining to draw.
     * 
     * @return the size of the list of the cards still able to be drawn
     */
    int getSizeDeck();
}
