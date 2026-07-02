package it.unibo.monopoli.model.table;

import java.util.List;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class is for the {@link Land}s' {@link IncomeStrategy}.
 *
 */
public class LandIncomeStrategy extends AbstractIncomeStartegy {

    private static final int ONE_HOME = 5;
    private static final int TWO_HOMES = 15;
    private static final int THREE_HOMES = 35;
    private static final int FOUR_HOMES = 55;
    private static final int ONE_HOTEL = 75;

    private final Ownership ownership;

    /**
     * Constructs an instance of this {@link Land}s' {@link IncomeStrategy}. It
     * needs the {@link Ownership} to which you want to calculate the income
     * value.
     * 
     * @param ownership
     *            - to which you want to calculate the income value
     * @throws IllegalArgumentException
     *             - if the {@link Ownership} in input isn't a {@link Station}
     */
    public LandIncomeStrategy(final Ownership ownership) {
        super(ownership);
        if (!(ownership instanceof Land)) {
            throw new IllegalArgumentException("Only Lands' income");
        }
        this.ownership = ownership;
    }

    @Override
    protected int getSpecificIncome(final List<Ownership> allMembers) {
        final List<Building> build = ((LandGroup) this.ownership.getGroup()).getBuildings();
        switch (build.size()) {
        case 1:
            return (build.get(0) instanceof Home ? ONE_HOME : ONE_HOTEL)
                    * ((ClassicLandContract) this.ownership.getContract()).getLandIncome();
        case 2:
            return ((ClassicLandContract) this.ownership.getContract()).getLandIncome() * TWO_HOMES;
        case 3:
            return ((ClassicLandContract) this.ownership.getContract()).getLandIncome() * THREE_HOMES;
        case 4:
            return ((ClassicLandContract) this.ownership.getContract()).getLandIncome() * FOUR_HOMES;
        default:
            return (((Player) this.ownership.getOwner()).getOwnerships().containsAll(allMembers) ? 2 : 1)
                    * ((ClassicLandContract) this.ownership.getContract()).getLandIncome();
        }
    }

}
