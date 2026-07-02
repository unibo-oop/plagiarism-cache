package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represent one of the {@link MoneyAction}s of the game. This one
 * allows to be paid.
 *
 */
public class ToBePaid extends ToPayAndBePaid {

    /**
     * Construct a new instance of {@link ToBePaid}. The amount in input is the
     * amount with which the {@link Player} is paid.
     * 
     * @param amount
     *            - the amount with which the {@link Player} is paid.
     * @throws IllegalArgumentException
     *             - if the amount is less than or equal to zero
     */
    public ToBePaid(final int amount) {
        super(amount);
        if (amount <= 0) {
            throw new IllegalArgumentException("Only positive amount different of zero!");
        }
    }

}
