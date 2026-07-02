package it.unibo.monopoli.model.table;

/**
 * This interface represent the {@link Contract}s of {@link Land}s. They are
 * different from other {@link Contract}s because they have also the cost and
 * the income of the {@link Building}s.
 *
 */
public interface LandContract extends Contract {

    /**
     * Return the cost to build a {@link Building} on the {@link Land}.
     * 
     * @return the cost to build a {@link Building}
     */
    int getCostForEachBuilding();

    /**
     * Return the income of the {@link Land} to which the {@link Contract}
     * belongs.
     * 
     * @return the income of the {@link Land}
     */
    int getLandIncome();

}
