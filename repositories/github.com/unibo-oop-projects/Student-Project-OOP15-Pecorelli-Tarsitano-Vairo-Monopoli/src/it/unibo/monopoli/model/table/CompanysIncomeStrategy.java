package it.unibo.monopoli.model.table;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represent the strategy for the Companies' income values.
 *
 */
public class CompanysIncomeStrategy extends BusinessesIncomeStrategy {

    private static final int ONE_COMPANY = 4;
    private static final int TWO_COMPANIES = 10;

    private final Player player;

    /**
     * Constructs an instance of Companies' {@link IncomeStrategy}. It needs the
     * {@link Ownership} to which you want to calculate the income values.
     * 
     * @param ownership
     *            - to which you want to calculate the income values
     * @param player
     *            - the {@link Player} that have to pay the income value
     */
    public CompanysIncomeStrategy(final Ownership ownership, final Player player) {
        super(ownership);
        this.player = player;
    }

    @Override
    protected int getBusinessesIncome(final int size) {
        switch (size) {
        case 1:
            return this.player.lastDicesNumber().stream().reduce((b, b1) -> b + b1).get() * ONE_COMPANY;
        case 2:
            return this.player.lastDicesNumber().stream().reduce((b, b1) -> b + b1).get() * TWO_COMPANIES;
        default:
            throw new IllegalArgumentException();
        }
    }

}
