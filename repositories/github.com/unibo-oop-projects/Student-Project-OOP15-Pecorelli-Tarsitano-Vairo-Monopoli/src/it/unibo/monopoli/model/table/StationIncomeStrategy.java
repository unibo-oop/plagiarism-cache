package it.unibo.monopoli.model.table;

/**
 * This class represent the strategy for the {@link Station}s' income values.
 *
 */
public class StationIncomeStrategy extends BusinessesIncomeStrategy {

    private static final int ONE_STATION = 25;
    private static final int TWO_STATION = 50;
    private static final int THREE_STATION = 100;
    private static final int FOUR_STATION = 200;

    /**
     * Constructs an instance of {@link Station}s' {@link IncomeStrategy}. It
     * needs the {@link Station} to which you want to calculate the income
     * values.
     * 
     * @param ownership
     *            - to which you want to calculate the income values
     * @throws IllegalArgumentException
     *             - if the {@link Ownership} in input isn't a {@link Station}
     */
    public StationIncomeStrategy(final Ownership ownership) {
        super(ownership);
        if (!(ownership instanceof Station)) {
            throw new IllegalArgumentException("Only for stations");
        }
    }

    @Override
    protected int getBusinessesIncome(final int size) {
        switch (size) {
        case 1:
            return ONE_STATION;
        case 2:
            return TWO_STATION;
        case 3:
            return THREE_STATION;
        case 4:
            return FOUR_STATION;
        default:
            throw new IllegalArgumentException();
        }
    }

}
