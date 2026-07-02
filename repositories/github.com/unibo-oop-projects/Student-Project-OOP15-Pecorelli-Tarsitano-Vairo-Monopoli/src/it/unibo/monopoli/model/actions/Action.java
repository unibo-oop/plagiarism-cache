package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This interfaces represent the contract that all actions have to realize. Each
 * different action must implements its own operation to be taken.
 *
 */
@FunctionalInterface
public interface Action {

    /**
     * This is the main method for every {@link Action}: it performs witch
     * action the specific class represent.
     * 
     * @param player
     *            - the {@link Player} that have to execute this {@link Action}
     */
    void play(Player player);

}
