package it.tbt.model.entities.items;

/**
 * Antidote, should remove the POISONED status.
 */
public class Antidote extends ItemImpl implements Consumable {

    /**
     * Default constructor.
     * @param name
     * @param value
     */
    public Antidote(final String name, final int value) {
        super(name, value);
    }

    /**
     * Alternative constructor.
     * @param value
     */
    public Antidote(final int value) {
        super("Antidote", value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getName();
    }
}
