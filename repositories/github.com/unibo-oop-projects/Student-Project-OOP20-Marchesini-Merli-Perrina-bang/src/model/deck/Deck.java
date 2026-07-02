package model.deck;

import java.util.List;

import model.card.Card;

/**
 * An interface implementing a deck of cards for the game.
 * 
 * @author Mattia Marchesini
 *
 */
public interface Deck {

	/**
	 * Creates new deck.
	 */
	void newGame();

	/**
	 * Gets all remaining cards in the deck.
	 * 
	 * @return
	 */
	List<Card> getCards();

	/**
	 * Returns the next card in the deck.
	 * 
	 * @return card
	 */
	Card nextCard();

	/**
	 * Returns a list containing the next cards.
	 * 
	 * @param step the number of cards to draw from the deck
	 * @return a list of cards
	 */
	List<Card> nextCards(int step);

	/**
	 * Returns the number of remaining cards in the deck.
	 * 
	 * @return
	 */
	int remainigCards();

	/**
	 * Shuffles the deck.
	 */
	void shuffleDeck();
}
