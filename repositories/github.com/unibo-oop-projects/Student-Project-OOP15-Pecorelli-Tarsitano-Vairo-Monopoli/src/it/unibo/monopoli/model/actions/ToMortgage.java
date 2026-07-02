package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link MoneyAction}s of the game. This one
 * allows to mortgage an {@link Ownership}s.
 *
 */
public class ToMortgage extends ToMortgageAndRevoke {

    private final Ownership ownership;

    /**
     * Construct a new instance of {@link ToMortgage}. The {@link Ownership} in
     * input is the {@link Ownership} to mortgage.
     * 
     * @param ownership
     *            - the {@link Ownership} to mortgage
     * @throws IllegalArgumentException
     *             - if the {@link Ownership} is a {@link Land} and in its
     *             {@link LandGroup} there are some {@link Building}s
     */
    public ToMortgage(final Ownership ownership) {
        super(ownership.getContract().getMortgageValue());
        this.ownership = ownership;
    }

    @Override
    protected void strategy(final Player player) {
        this.ownership.setMortgage(true);
    }

}
