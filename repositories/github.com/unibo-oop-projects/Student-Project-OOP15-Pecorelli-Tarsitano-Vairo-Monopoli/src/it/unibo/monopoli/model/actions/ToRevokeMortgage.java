package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link MoneyAction}s of the game. This one
 * allows to revoke mortgage on {@link Ownership}s.
 *
 */
public class ToRevokeMortgage extends ToMortgageAndRevoke {

    private final Ownership ownership;

    /**
     * Construct a new instance of {@link ToRevokeMortgage}. The
     * {@link Ownership} in input is the {@link Ownership} with the mortgage to
     * revoke.
     * 
     * @param ownership
     *            - the {@link Ownership} with the mortgage to revoke
     */
    public ToRevokeMortgage(final Ownership ownership) {
        super(-ownership.getContract().getMortgageValue());
        this.ownership = ownership;
    }

    @Override
    protected void strategy(final Player player) {
        this.ownership.setMortgage(false);
    }

}
