package it.unibo.monopoli.model.table;

/**
 * This class represents an implementation of {@link Box}. More specifically it
 * represents the {@link PrisonOrTransit}'s {@link Box} of Monopoly.
 *
 */
public class PrisonOrTransit implements Box {

    private final String name;
    private final int id;

    /**
     * Constructs an implementation of {@link PrisonOrTransit} that needs a name
     * and an ID.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     */
    public PrisonOrTransit(final String name, final int id) {
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
