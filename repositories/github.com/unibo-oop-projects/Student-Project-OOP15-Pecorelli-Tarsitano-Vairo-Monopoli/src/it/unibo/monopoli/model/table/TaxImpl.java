package it.unibo.monopoli.model.table;

/**
 * This class represents an implementation of {@link Tax}. More specifically it
 * represents the {@link Tax}'s {@link Box} of Monopoly.
 *
 */
public class TaxImpl implements Tax {

    private final String name;
    private final int id;
    private final int cost;

    /**
     * Constructs an implementation of {@link TaxImpl} that needs a name, an ID
     * and a cost.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     * @param cost
     *            - of this {@link Box}
     */
    public TaxImpl(final String name, final int id, final int cost) {
        this.name = name;
        this.id = id;
        this.cost = cost;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

}
