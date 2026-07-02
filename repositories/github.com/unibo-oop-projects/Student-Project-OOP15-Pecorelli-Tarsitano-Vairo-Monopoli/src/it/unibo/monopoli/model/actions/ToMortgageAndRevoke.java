package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This abstract class represent the common contract of classes that want to
 * mortgage or revoke a mortgage on a {@link Ownership}.
 *
 */
public abstract class ToMortgageAndRevoke extends MoneyAction {

    /**
     * This constructor serves to get the amount of the mortgage or the revoke.
     * 
     * @param amount
     *            - of the of the mortgage or the revoke
     */
    protected ToMortgageAndRevoke(final int amount) {
        super(amount);
    }

    @Override
    protected abstract void strategy(final Player player);

}
