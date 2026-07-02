package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represent one of the {@link MoneyAction}s of the game. This one is
 * for paying.
 *
 */
public class ToPay extends ToPayAndBePaid {

    /**
     * Construct a new instance of {@link ToPay}. The amount in input is the
     * amount to pay, instead the {@link Player} in input is the one that have
     * to pay.
     * 
     * @param amount
     *            - the amount to pay
     * @throws IllegalArgumentException
     *             - if the amount is less than or equal to zero
     * @throws IllegalArgumentException
     *             - if the amount is more than the {@link Player}'s money
     */
    public ToPay(final int amount) {
        super(-amount);
        if (amount <= 0) {
            throw new IllegalArgumentException("Only positive amount different of zero!");
        }
    }

}
