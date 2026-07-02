package it.unibo.monopoli.model.actions;

import java.util.List;

import it.unibo.monopoli.model.mainunits.Dice;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This is the interface for the strategy to use when a {@link Player} invokes
 * the {@link ToRollDices}'s {@link Action}.
 *
 */
public interface DicesStrategy {

    /**
     * Returns a {@link List} of the {@link Dice}s to use.
     * 
     * @return a {@link List} of {@link Dice}s
     */
    List<Dice> getDices();

    /**
     * When you roll {@link Dice}s, sometimes you also want to take another
     * action. This method allows to do it.
     * 
     * @param player
     *            - the {@link Player} who rolled
     */
    void nowPlay(Player player);

}
