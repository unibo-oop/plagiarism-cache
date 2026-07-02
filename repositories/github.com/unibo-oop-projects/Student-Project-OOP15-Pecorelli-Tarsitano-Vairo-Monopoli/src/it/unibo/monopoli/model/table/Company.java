package it.unibo.monopoli.model.table;

import it.unibo.monopoli.model.mainunits.Owner;

/**
 * This class represents the classic Companies present in the classic version of
 * Monopoly.
 *
 */
public class Company extends ClassicOwnership {

    /**
     * Constructs a {@link ClassicOwnership} that is a {@link Company}. it needs
     * a name, an id and an {@link Owner}.
     * 
     * @param name
     *            - of the {@link Company}
     * @param id
     *            - of the {@link Company}
     * @param owner
     *            - of the {@link Company}
     */
    public Company(final String name, final int id, final Owner owner) {
        super(name, id, owner);
    }

}
