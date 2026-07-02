package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;

/**
 * This is the interface that specifies witch instances are to be used depending
 * on the chosen game's version.
 *
 */
public interface GameVersion {

    /**
     * This method return an instance of the only one {@link Bank}, specific of
     * this version.
     * 
     * @return a {@link Bank}
     */
    Bank getBank();

    /**
     * This method return all the {@link Box}es required for the specific
     * version.
     *
     * @return a {@link List} of {@link Box}es
     */
    List<Box> getAllBoxes();

    /**
     * This method return all the {@link Deck}s required for the specific
     * version.
     *
     * @return a {@link List} of {@link Deck}s
     */
    List<Deck> getDecks();

    /**
     * Returns the next {@link Player}.
     * 
     * @return the next {@link Player}
     */
    Player getNextPlayer();

    /**
     * It does finish the actual {@link Player} round and returns the
     * {@link Player} next which have to play.
     * 
     * @return the {@link Player} which have to play
     */
    Player endOfTurnAndNextPlayer();

    /**
     * Removes the specific {@link Player} from the list of the game's
     * {@link Player}.
     * 
     * @param player
     *            - the {@link Player} to remove
     * @return - the next {@link Player} 
     */
    Player removePlayer(Player player);

    /**
     * Returns a {@link List} with the numbers obtained with the roll of dices
     * and moves the {@link Player}'s {@link Pawn}.
     * 
     * @return a {@link List} with the numbers obtained with the roll of dices
     */
    List<Integer> toRollDices();

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
    boolean getNextCardsAction(Box box, Card card, Player player);

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

}
