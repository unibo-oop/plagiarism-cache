package model.deck;

import java.util.List;

import model.card.Card;

/**
 * An interface to read cards from a deck.
 * 
 * @author Mattia Marchesini
 *
 */
interface DeckReader {

	List<Card> readCards();
}
