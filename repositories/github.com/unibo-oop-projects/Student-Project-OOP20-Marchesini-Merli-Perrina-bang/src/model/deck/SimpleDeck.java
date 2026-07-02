package model.deck;

/**
 * A class implementing a deck of cards for the game.
 * 
 * @author Davide
 *
 */
public class SimpleDeck extends AbstractDeck {

	public SimpleDeck() {
		super(new JSONDeckReader());
	}
}
