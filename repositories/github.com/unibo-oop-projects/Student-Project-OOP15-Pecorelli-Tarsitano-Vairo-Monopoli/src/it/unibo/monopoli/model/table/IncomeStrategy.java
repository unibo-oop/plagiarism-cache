package it.unibo.monopoli.model.table;

/**
 * This is the interface uses to obtain the income value of {@link Ownership}s.
 * 
 */
public interface IncomeStrategy {

    /**
     * Returns the income value of the specific {@link Ownership}.
     * 
     * @return an income value
     */
    int getIncome();

}
