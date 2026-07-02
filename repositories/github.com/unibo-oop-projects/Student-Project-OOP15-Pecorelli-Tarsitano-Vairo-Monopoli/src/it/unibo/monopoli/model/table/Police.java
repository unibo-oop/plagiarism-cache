package it.unibo.monopoli.model.table;

/**
 * This class represents an implementation of {@link Box}. More specifically it
 * represents the {@link Police}'s {@link Box} of Monopoly.
 *
 */
public class Police implements Box {

    private final String name;
    private final int id;

    /**
     * Constructs an implementation of {@link Police} that needs a name, an ID
     * and the prison's {@link Box}.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     */
    public Police(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }

}
