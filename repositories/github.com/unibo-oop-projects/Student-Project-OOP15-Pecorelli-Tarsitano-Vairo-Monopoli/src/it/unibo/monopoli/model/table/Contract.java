package it.unibo.monopoli.model.table;

/**
 * This interface represent all the contracts for each {@link Ownership}. All
 * that contracts declare the name, the cost, the income and the mortgage value
 * of their own {@link Ownership}.
 *
 */
public interface Contract {

    /**
     * Return the name of this {@link Contract}.
     * 
     * @return {@link Contract}'s name
     */
    String getname();

    /**
     * Returns the {@link Ownership} to which it belongs.
     * 
     * @return the {@link Ownership} to which it belongs
     */
    Ownership getOwnership();

    /**
     * Return the cost to buy the {@link Ownership} to which the
     * {@link Contract} belongs.
     * 
     * @return the cost of the {@link Ownership}
     */
    int getCost();

    /**
     * Return the income of the {@link Ownership} to which the {@link Contract}
     * belongs.
     * 
     * @param income
     *            - the specific {@link IncomeStrategy}
     * 
     * @return the income of the {@link Ownership}
     */
    int getIncome(IncomeStrategy income);

    /**
     * Return the mortgage value of the {@link Ownership} to which the
     * {@link Contract} belongs.
     * 
     * @return the mortgage value of the {@link Ownership}
     */
    int getMortgageValue();

}
