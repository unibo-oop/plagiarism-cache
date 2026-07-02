package it.tbt.model.entities.items;

/**
 * Generic Armor.
 */
public class Armor extends ItemImpl implements Equipement {
    private final int defence;

    /**
     * Default constructor.
     * @param name
     * @param defence
     * @param value
     */
    public Armor(final String name, final int defence, final int value) {
        super(name, value);
        this.defence = defence;
    }

    /**
     * get the defence of the armor.
     * @return int
     */
    public final int getDefence() {
        return defence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getName() + " - Defence: " + defence;
    }
}
