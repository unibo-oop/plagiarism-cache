package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This interface represent all the players. They all have a name that
 * identifies them, a {@link Pawn} that identifies them in the game, some
 * {@link Ownership}s and some money.
 *
 */
public interface Player extends Owner {

    /**
     * Return the name of the {@link Player}.
     * 
     * @return the name of the {@link Player}
     */
    String getName();

    /**
     * Return the {@link Pawn} of the {@link Player}.
     * 
     * @return the {@link Pawn} of the {@link Player}
     */
    Pawn getPawn();

    /**
     * Return the {@link List} of {@link Ownership} belonging to the
     * {@link Player}.
     * 
     * @return a {@link List} of {@link Ownership}
     */
    List<Ownership> getOwnerships();

    /**
     * Adds a {@link Card} to the {@link Player}.
     * 
     * @param card
     *            - the {@link Card} to add
     */
    void addCard(Card card);

    /**
     * Removes a {@link Card} from the {@link Player}.
     * 
     * @param card
     *            - the {@link Card} to remove
     */
    void removeCard(Card card);

    /**
     * Return a {@link List} of player's {@link Card}s.
     * 
     * @return a {@link List} of {@link Card}s
     */
    List<Card> getCards();

    /**
     * Returns true if the {@link Player} has already rolled {@link Dice}s,
     * false if hasn't.
     * 
     * @return true if the {@link Player} has already rolled {@link Dice}s
     */
    boolean dicesAlreadyRolled();

    /**
     * Sets if the {@link Player} rolled {@link Dice}s (true) or not (false).
     * 
     * @param alreadyRolled
     *            - true if {@link Player} already rolled {@link Dice}s,
     *            otherwise, false
     */
    void setDicesRoll(boolean alreadyRolled);

    /**
     * Returns true if the {@link Player} is in prison, false if he isn't.
     * 
     * @return true if the {@link Player} is in prison
     */
    boolean isInPrison();

    /**
     * Returns the last {@link Card} that the {@link Player} drew from a
     * {@link Deck}.
     * 
     * @return the last {@link Card} drew from a {@link Deck}
     * @throws NullPointerException
     *             - if no {@link Card} was draw.
     */
    Card lastCardDrew();

    /**
     * Sets the {@link Card} that the {@link Player} drew from a {@link Deck}.
     * 
     * @param lastCard
     *            - the {@link Card} drew from a {@link Deck}
     */
    void setLastCardDrew(Card lastCard);

    /**
     * Returns the last numbers obtained with the roll of {@link Dice}s.
     * 
     * @return the last numbers obtained with the roll of {@link Dice}s.
     */
    List<Integer> lastDicesNumber();

    /**
     * Sets the last numbers obtained with the roll of {@link Dice}s.
     * 
     * @param numbers
     *            - the last numbers obtained with the roll of {@link Dice}s.
     */
    void setLastDicesNumber(List<Integer> numbers);

    /**
     * Returns true if the {@link Player} had paid all his debts, otherwise
     * false.
     * 
     * @return true if the {@link Player} had paid all his debts
     */
    boolean areDebtsPaid();

    /**
     * Sets true if the {@link Player} had paid all his debts, otherwise false.
     * 
     * @param arePaid
     *            - a boolean that is true if the {@link Player} had paid all
     *            his debts, otherwise false
     */
    void setDebts(boolean arePaid);

    /**
     * Sets if the {@link Player} is going to prison (true) or not (false).
     * 
     * @param isGoingToPrison
     *            - true if {@link Player} is going to prison, otherwise, false
     */
    void setPrison(boolean isGoingToPrison);

    /**
     * Add a new {@link Ownership}.
     * 
     * @param ownership
     *            - the one to add
     */
    void addOwnership(Ownership ownership);

    /**
     * Remove a {@link Ownership} from the {@link Player}.
     * 
     * @param ownership
     *            - the one to remove
     */
    void removeOwnership(Ownership ownership);

    /**
     * Return the money belonging to the {@link Player}.
     * 
     * @return the money belonging to the {@link Player}
     */
    int getMoney();

    /**
     * Set the money belonging to the {@link Player}.
     * 
     * @param amount
     *            - the amount of money to set
     */
    void setMoney(int amount);

    /**
     * Returns true if the {@link Player} is human, otherwise false.
     * 
     * @return true if the {@link Player} is human
     */
    boolean isHuman();

    /**
     * Returns how many turns the {@link Player} has been in prison's
     * {@link Box}.
     * 
     * @return how many turns the {@link Player} has been in prison
     */
    int howManyTurnsHasBeenInPrison();

    /**
     * Increments the turns that the {@link Player} passed in prison.
     */
    void incrementsTurnsInPrison();

    /**
     * Sets if next one will be the first launch (true) or not (false).
     * 
     * @param isTheFirst
     *            - true if next one will be the first launch, otherwise false
     */
    void setIfIsTheFirstLaunch(boolean isTheFirst);

    /**
     * Returns if the launch is the first one of the {@link Player}'s turn
     * (true) or not (false).
     * 
     * @return if the launch is the first one of the {@link Player}'s turn
     *         (true)
     */
    boolean isTheFirtsLaunch();

}
