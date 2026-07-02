package it.unibo.monopoli.model.table;

import java.util.List;

/**
 * This interface represent all the groups of {@link Ownership}s present in the
 * game. Indeed, every {@link Ownership}s belongs to a group.
 *
 */
public interface Group {

    /**
     * Return a {@link List} of the members of this {@link Group}.
     * 
     * @return a {@link List} of {@link Ownership}s
     */
    List<Ownership> getMembers();


    /**
     * Return the name of this {@link Group}.
     * 
     * @return the name of this {@link Group}
     */
    String getName();

}
