package it.tbt.model.entities.items;

import it.tbt.model.entities.EntityImpl;

/**
 * Generic item.
 */
class ItemImpl extends EntityImpl implements Item {
    private final int value;

    /**
     * Item constructor.
     * @param name
     * @param value     the value of the item
     */
    protected ItemImpl(final String name, final int value) {
        super(name);
        this.value = value;
    }

    /**
     * Return the monetary value of the item.
     * @return item's value
     */
    @Override
    public final int getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Item: " + getName();
    }
}
