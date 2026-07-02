package it.unibo.monopoli.model.table;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class is for the businesses' {@link Box} income values.
 *
 */
public abstract class BusinessesIncomeStrategy extends AbstractIncomeStartegy {

    private final Ownership ownership;

    /**
     * The constructor needs the {@link Ownership} to which you want to
     * calculate the income value.
     * 
     * @param ownership
     *            - the {@link Ownership} to which you want to calculate the
     *            income value
     */
    public BusinessesIncomeStrategy(final Ownership ownership) {
        super(ownership);
        this.ownership = ownership;
    }

    @Override
    protected int getSpecificIncome(final List<Ownership> allMembers) {
        final List<Ownership> playersMembers = new LinkedList<>();
        allMembers.stream().filter(m -> ((Player) this.ownership.getOwner()).getOwnerships().contains(m))
                .forEach(m -> playersMembers.add(m));
        return this.getBusinessesIncome(playersMembers.size());
    }

    /**
     * Returns the income of the specific businesses' {@link Box}.
     * 
     * @param size
     *            - the size of the {@link List} of all the members owned by the
     *            {@link Player}
     * @return the specific income value
     */
    protected abstract int getBusinessesIncome(final int size);

}
