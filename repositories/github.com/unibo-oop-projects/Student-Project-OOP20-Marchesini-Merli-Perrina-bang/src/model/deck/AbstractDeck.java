package model.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.card.Card;

/**
 * An abstract class implementing a deck of cards for the game.
 * 
 * @author Mattia Marchesini
 *
 */
abstract class AbstractDeck implements Deck {

	protected DeckReader reader;
	protected List<Card> cards = null;

	public AbstractDeck(DeckReader reader) {
		this.reader = reader;
	}

	@Override
	public void newGame() {
		this.cards = new ArrayList<>(reader.readCards());
		this.shuffleDeck();
	}

	@Override
	public List<Card> getCards() {
		return this.cards;
	}

	@Override
	public Card nextCard() {
		if (this.cards == null || this.remainigCards() <= 5) {
			this.newGame();
		}
		return this.cards.remove(0);
	}

	@Override
	public List<Card> nextCards(int step) {
		List<Card> removed = new ArrayList<>();
		for (int i = 0; i < step; i++) {
			removed.add(this.nextCard());
		}
		return removed;
	}

	@Override
	public int remainigCards() {
		return this.cards.size();
	}

	@Override
	public void shuffleDeck() {
		Collections.shuffle(this.cards);
	}
}
