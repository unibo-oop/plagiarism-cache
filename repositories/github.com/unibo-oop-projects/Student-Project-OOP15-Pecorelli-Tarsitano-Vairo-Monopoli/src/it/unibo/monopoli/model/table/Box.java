package it.unibo.monopoli.model.table;

/**
 * This interface represent all the {@link Box}es in the game's table. Each box
 * has a name and an ID.
 *
 */
public interface Box {

    /**
     * Return the name that identifies the {@link Box}.
     * 
     * @return the name of the {@link Box}
     */
    String getName();

    /**
     * Return the ID of the {@link Box}.
     * 
     * @return the ID of the {@link Box}
     */
    int getID();

}
