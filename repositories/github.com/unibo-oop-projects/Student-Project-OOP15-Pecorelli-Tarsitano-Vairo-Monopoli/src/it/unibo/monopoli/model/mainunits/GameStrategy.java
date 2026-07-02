package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;

/**
 * This interface holds the strategy for initialize the game with the right
 * {@link GameVersion}.
 *
 */
public interface GameStrategy {

    /**
     * Returns a {@link List} of the game's {@link Player}s.
     * 
     * @return a {@link List} of the {@link Player}s
     */
    List<Player> getPlayers();

    /**
     * Return the game's {@link Bank}.
     * 
     * @return the {@link Bank}
     */
    Bank getBank();

    /**
     * Returns a {@link List} with all the game's table's {@link Box}es.
     * 
     * @return a {@link List} of all {@link Box}es
     */
    List<Box> getBoxes();

    /**
     * This method performs all the {@link Card}'s {@link Action}s that have not
     * yet been carried and then returns true if the {@link Player} lost, false
     * otherwise.
     * 
     * @param box
     *            - the {@link Box} on which is located the {@link Player}
     * @param card
     *            - the {@link Card} drew
     * @param player
     *            - the {@link Player} who is playing
     * @return true if the {@link Player} lost, false otherwise
     */
    boolean getNextCardsActions(Box box, Card card, Player player);

    /**
     * Return true if the {@link Player} have enough money, false otherwise.
     * This method is uses when the {@link Player} has to pay but he hasn't
     * enough money, so he has to sell some of his properties. After, if he has
     * enough money, ha can go back to play.
     * 
     * @param player
     *            - the {@link Player} who is playing
     * @param moneyToPay
     *            - the money that he has to pay
     * @return true if he have enough money
     */
    boolean haveEnoughMoney(Player player, int moneyToPay);

    /**
     * Returns a {@link List} of all the {@link Deck}s in the game.
     * 
     * @return a {@link List} of {@link Deck}s
     */
    List<Deck> getDecks();

    /**
     * Returns a {@link List} with the numbers obtained with the roll of dices
     * and moves the {@link Player}'s {@link Pawn}.
     * 
     * @param player
     *            - the {@link Player} who is playing
     * @return a {@link List} with the numbers obtained with the roll of dices
     */
    List<Integer> toRollDices(Player player);

}
