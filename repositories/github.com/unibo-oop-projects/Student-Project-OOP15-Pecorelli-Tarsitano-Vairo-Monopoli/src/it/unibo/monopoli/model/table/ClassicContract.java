package it.unibo.monopoli.model.table;

/**
 * This is a classic implementation of {@link Contract}.
 *
 */
public class ClassicContract implements Contract {

    private final Ownership ownership;
    private final int cost;

    /**
     * Constructs an instance of {@link ClassicContract}. It needs the
     * {@link Ownership} to which it belongs and its own cost.
     * 
     * @param ownership
     *            - to which it belongs
     * @param cost
     *            - {@link Ownership}'s cost
     */
    public ClassicContract(final Ownership ownership, final int cost) {
        this.ownership = ownership;
        this.cost = cost;
    }

    @Override
    public String getname() {
        return this.ownership.getName();
    }

    @Override
    public Ownership getOwnership() {
        return this.ownership;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public int getIncome(final IncomeStrategy income) {
        return income.getIncome();
    }

    @Override
    public int getMortgageValue() {
        return this.cost / 2;
    }

}
