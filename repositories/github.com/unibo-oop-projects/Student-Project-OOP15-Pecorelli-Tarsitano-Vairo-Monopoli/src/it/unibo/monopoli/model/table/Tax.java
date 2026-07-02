package it.unibo.monopoli.model.table;

/**
 * This interface represent the {@link Tax}es of this game. They are particular
 * {@link Box}es because they also have a cost to pay.
 *
 */
public interface Tax extends Box {

    /**
     * Return the {@link Tax}'s cost.
     * 
     * @return {@link Tax}'s cost
     */
    int getCost();

}
