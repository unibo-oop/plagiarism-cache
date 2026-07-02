package it.unibo.monoopoly.model.notary.api;

import java.util.Optional;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * Manage the operations of buying properties and check actions caused by
 * {@link Buyable} cells.
 */
public interface Notary {

    /**
     * Tells how the active {@link Player} is going to interact with the
     * {@link Buyable} property is on.
     * 
     * @param player the player to check
     * @param cell   the cell of the property to check
     * @return the type of event if this occurs
     */
    Optional<Event> checkProperty(Player player, Cell cell);

    /**
     * Set the given player as owner of the property.
     * 
     * @param player the player who buys
     * @param cell   the property to buy
     */
    void buyProperty(Player player, Buyable cell);

    /**
     * Check if the cell is buyable by the actual {@link Player}.
     * 
     * @param cell         the actual cell where the player is currently
     * @param actualPlayer the actual player in this {@link MainModel}
     * @return if the player can buy the cell
     */
    boolean isActionBuy(Cell cell, Player actualPlayer);

}
