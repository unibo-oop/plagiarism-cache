package model.deck;

import java.util.List;
/**
 *
 */
public interface Deck {

    /**
     * create the Deck with the numbers of cards.
     */
    void createDeck();

    /**
     * @return the first card of deck.
     */
    Card showCards();

    /**
     * @return the set of field cards.
     */
    List<Card> showFieldCards();

    /**
     * adds a card into fieldcards set.
     * 
     * @param index
     *  the index of fieldcard that has to be changed.
     */
    void addCard(int index);

    /**
     * remove a card into fieldcards set at selected index.
     * @param index is the card which will removed.
     */
    void removeCard(int index);

    /**
     * Returns all card of Deck.
     * @return  A List that contains all cards of deck and field.
     */
    List<Card> getDeckCards();

}
