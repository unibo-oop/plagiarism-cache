package it.unibo.monopoli.model.table;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represents an implementation of {@link Box}. More specifically it
 * represents the {@link Start}'s {@link Box} of Monopoly.
 *
 */
public class Start implements Box {

    private static final int MONEY_TO_PICK_UP = 200;

    private final String name;
    private final int id;

    /**
     * Constructs an implementation of {@link Start} that needs a name and an ID.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     */
    public Start(final String name, final int id) {
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

    /**
     * Returns the values of the money to pick up when some {@link Player} pass
     * from this {@link Box}.
     * 
     * @return the money to pick up
     */
    public static int getMuchToPick() {
        return MONEY_TO_PICK_UP;
    }

}
