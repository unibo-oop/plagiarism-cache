package model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import libs.CircularList;
import model.card.Card;
import model.deck.Deck;

/**
 * An interface implementing a game table. The interface contains function to
 * handle the players and the deck.
 * 
 * @author Davide Merli
 *
 */
public interface Table {

	enum Message {
		CHOOSE_PLAYER, CHOOSE_PLAYER_WITH_DISTANCE, CHOOSE_CARD
	}

	/**
	 * Returns the deck.
	 * 
	 * @return deck
	 */
	Deck getDeck();

	/**
	 * Returns a list containing all discarded cards.
	 * 
	 * @return discard pile
	 */
	List<Card> getDiscardPile();

	/**
	 * Adds a card to the discard pile.
	 * 
	 * @param card
	 */
	void discardCard(final Card card);

	/**
	 * Returns a list of alive players.
	 * 
	 * @return a CircularList of players
	 */
	CircularList<Player> getPlayers();

	/**
	 * Removes a player from a list of players.
	 * 
	 * @param player
	 */
	void removePlayer(final Player player);

	/**
	 * Returns the current player.
	 * 
	 * @return current player
	 */
	Player getCurrentPlayer();

	/**
	 * Sets the current player.
	 * 
	 * @param player
	 */
	void setCurrentPlayer(Player player);

	/**
	 * Sets next player
	 */
	void nextPlayer();

	/**
	 * Returns the turn observable.
	 * 
	 * @return turn observable
	 */
	TurnObservable<Player> getChoosePlayerObservable();

	/**
	 * Returns a turnObservable containing a card and the player who chose it.
	 * 
	 * @return
	 */
	TurnObservable<Map<Card, Player>> getChooseCardsObservable();

	/**
	 * Adds a card to the list of used cards in current turn.
	 * 
	 * @param cardName
	 */
	void playerUsedCard(final String cardName);

	/**
	 * Returns the list of cards played in the current turn.
	 * 
	 * @return
	 */
	List<String> getPlayerUsedCards();

	/**
	 * Returns a message.
	 * 
	 * @return message
	 */
	Message getMessage();

	/**
	 * Sets a message.
	 * 
	 * @param message
	 */
	void setMessage(Message message);

	/**
	 * Returns a set of possible targets.
	 * 
	 * @return
	 */
	Set<Player> getChosenPlayerList();

	/**
	 * Sets the possible targets of an action.
	 * 
	 * @param chosenPlayerList
	 */
	void choosePlayer(final Set<Player> chosenPlayerList);
}
