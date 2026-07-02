package it.unibo.monopoli.model.table;

import it.unibo.monopoli.model.mainunits.Owner;

/**
 * This class represents an implementation of {@link ClassicOwnership}. More
 * specifically it represents the {@link Station}s' {@link Box} of Monopoly.
 *
 */
public class Station extends ClassicOwnership {

    /**
     * Constructs an implementation of {@link Start} that needs a name, an ID
     * and a {@link Owner}.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     * @param owner
     *            - the {@link Owner} of the {@link Station}
     */
    public Station(final String name, final int id, final Owner owner) {
        super(name, id, owner);
    }

}
