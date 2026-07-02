package it.unibo.monoopoly.model.deck.api;

/**
 * The interface that implemented the deck of cards,
 * and its management.
 */
public interface Deck {
    /**
     * Draw the next {@link Card} and set actualCard field. 
     */
    void draw();
    /**
     * Gets the drawn {@link Card}.
     * 
     * @return the drawn {@link Card}.
     */
    Card getActualCard();
    /**
     * To add a: go to prison card, in the discard pile.
     */
    void addPrisonCard();
}
