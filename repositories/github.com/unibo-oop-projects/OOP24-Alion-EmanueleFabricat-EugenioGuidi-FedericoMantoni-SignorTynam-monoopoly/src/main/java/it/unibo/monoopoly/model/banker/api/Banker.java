package it.unibo.monoopoly.model.banker.api;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.common.Message;
import it.unibo.monoopoly.model.gameboard.api.Buildable;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * Interface that implements a banker which when a {@link Player},
 * in indebted chooses the correct operation for the player to perform,
 * or, if necessary, sends the player into bankruptcy.
 */
public interface Banker {
    /**
     * Select the right Operations to pay the amount,
     * or sends the player into bankruptcy.
     * 
     * @param player on which to perform the operation.
     * @return the {@link Message} to {@link MainModel}.
     */
    Event selectOperations(Player player);

    /**
     * Sell a house on the given input property, and pay the player.
     * 
     * @param property to sell.
     * @param player   to pay.
     */
    void sellHouse(Buildable property, Player player);

    /**
     * Mortgage the given input property, and pay the player.
     * 
     * @param property to be mortgaged.
     * @param player   to pay.
     */
    void mortgageProperty(Buyable property, Player player);
}
