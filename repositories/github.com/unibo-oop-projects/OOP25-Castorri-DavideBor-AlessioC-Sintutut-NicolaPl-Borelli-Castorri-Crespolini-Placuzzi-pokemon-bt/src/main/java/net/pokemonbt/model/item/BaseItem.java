package net.pokemonbt.model.item;

import java.util.Objects;

/**
 * Base class for all {@link Item}s, representing
 * those that have no special or particular behaviour.
 */
public class BaseItem implements Item {
    private final String id;

    /**
     * @param id The id of the Item.
     */
    public BaseItem(final String id) {
        Objects.requireNonNull(id);

        this.id = id;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getID() {
        return this.id;
    }
}
