package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This abstract class represent the common contract for all classes that
 * represent {@link Action}s for paying for being paid.
 *
 */
public abstract class ToPayAndBePaid extends MoneyAction {

    /**
     * This constructor serves to get the amount to pay or to be paid.
     * 
     * @param amount
     *            - to pay or to be paid
     */
    protected ToPayAndBePaid(final int amount) {
        super(amount);
    }

    @Override
    protected void strategy(final Player player) {
    };

}
