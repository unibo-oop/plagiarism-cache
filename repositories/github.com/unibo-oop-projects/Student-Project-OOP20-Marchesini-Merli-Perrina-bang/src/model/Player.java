package model;

import java.util.List;
import java.util.Set;

import model.card.Card;

/**
 * An interface implementing a player.
 * 
 * @author Ryan Perrina
 *
 */
public interface Player {

	/**
	 * Returns the name of the player.
	 * 
	 ** @return player name
	 */
	public String getName();

	/**
	 * Sets the range of player.
	 * 
	 * @param sight
	 */
	public void setRange(final int sight);

	/**
	 * Returns of the player.
	 * 
	 * @return the sight
	 */
	public int getSight();

	/**
	 * Returns the role of the player.
	 * 
	 * @return role
	 */
	public Role getRole();

	/**
	 * Returns a list containing the hand of cards of the player.
	 * 
	 * @return a list of cards
	 */
	public List<Card> getCards();

	/**
	 * Adds a card to the hand of cards of the player.
	 * 
	 * @param card
	 */
	public void addCard(final Card card);

	/**
	 * Removes a card from the hand of the player.
	 * 
	 * @param card
	 */
	public void removeCard(final Card card);

	/**
	 * Returns a set containing the active cards of the player.
	 * 
	 * @return a set of cards
	 */
	public Set<Card> getActiveCards();

	/**
	 * Adds a card to the list of active cards.
	 * 
	 * @param card
	 */
	public void addActiveCard(final Card card);

	/**
	 * Removes a card from the active cards of the player.
	 * 
	 * @param card
	 */
	public void removeActiveCard(final Card card);

	/**
	 * Returns the current life points of the player.
	 * 
	 * @return life points
	 */
	public int getLifePoints();

	/**
	 * Modifies the player life points by an input value.
	 * 
	 * @param points
	 */
	public void modifyLifePoints(final int points);

	/**
	 * Returns true if the player has a retreat.
	 * 
	 * @return
	 */
	int getRetreat();

	/**
	 * Returns a list of cards with an input name, taken from the hand of the
	 * player.
	 * 
	 * @param name the name of the card
	 * @return a list of cards
	 */
	List<Card> getCardsByName(final String name);

	/**
	 * Returns a list of cards with an input name, taken from the active cards of
	 * the player.
	 * 
	 * @param name the name of the card
	 * @return a list of cards
	 */
	List<Card> getActiveCardsByName(final String name);

	/**
	 * Plays a card.
	 * 
	 * @param name  the card name
	 * @param table
	 */
	void playCard(final String name, final Table table);

	/**
	 * Modifies player sight by an input value.
	 * 
	 * @param sight
	 */
	void modifySight(final int sight);

	/**
	 * Modifies player retreat by an input value.
	 * 
	 * @param retreat
	 */
	void modifyRetreat(final int retreat);

	/**
	 * Adds a protection.
	 */
	void addProtection();

	/**
	 * Removes a protection.
	 */
	void removeProtection();

	/**
	 * Returns true if the player has a protection
	 * 
	 * @return
	 */
	boolean hasProtection();

	/**
	 * Returns true if the player has a prison.
	 * 
	 * @return
	 */
	boolean hasPrison();

	/**
	 * Sets a boolean which determines if the player is in prison.
	 * 
	 * @param hasPrison
	 */
	void setPrison(final boolean hasPrison);

	/**
	 * Adds a weapon to the player.
	 * 
	 * @param card
	 */
	void addWeapon(final String card);

	/**
	 * Removes the player weapon.
	 */
	void removeWeapon();
}
