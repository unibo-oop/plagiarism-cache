package it.unibo.monoopoly.common;

import it.unibo.monoopoly.model.deck.api.Card;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Functional;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * Represents the various events triggered by a cell or a card.
 */
public enum Event {

    /**
     * Event triggered by finishing in a {@link Buyable} cell owned by another
     * {@link Player}.
     * Involves in the payment to the player owner
     */
    RENT_PAYMENT,
    /**
     * Event triggered by some {@link Functional} cell, involving the payment of a
     * fixed tax.
     */
    TAX_PAYMENT,
    /**
     * Event triggered by finishing in a {@link Buyable} cell owned by bank,
     * involving the choice of the player to buy or not buy the property.
     */
    BUY_PROPERTY,
    /**
     * Actions represents having to draw a {@link Card}.
     */
    DRAW,
    /**
     * Actions represents that a: get out of jail {@link Card} it has been drawn.
     */
    FREE_CARD,
    /**
     * Actions represents having to move a the actual {@link Player}.
     */
    MOVE_CARD,
    /**
     * Actions represents having to pay an amount.
     */
    CARD_PAYMENT,
    /**
     * Actions represents having to go to prison the actual {@link Player}.
     */
    PRISON,
    /**
     * Actions represents having to receive an amount.
     */
    RECEIVE_CARD,
    /**
     * Actions represents having to sell house in an property.
     */
    SELL_HOUSE,
    /**
     * Actions represents having to mortgage a property.
     */
    MORTGAGE_PROPERTY,
    /**
     * Actions represents that the current player is in bankrupt.
     */
    BANKRUPT


}
