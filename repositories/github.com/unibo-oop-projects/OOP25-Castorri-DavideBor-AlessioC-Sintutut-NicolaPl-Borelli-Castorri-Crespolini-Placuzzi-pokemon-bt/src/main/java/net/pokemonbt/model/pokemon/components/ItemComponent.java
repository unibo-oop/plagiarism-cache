package net.pokemonbt.model.pokemon.components;

import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.item.Item;
import net.pokemonbt.utility.Clone;

import java.io.Serial;
import java.util.Objects;

/**
 * Handles a {@link Pokemon}'s item presence and behaviour activation.
 * It is an Optional implementation though, so it may be
 * complete in the future.
 */
public class ItemComponent extends AbstractPokeComponent implements Clone<ItemComponent> {
    @Serial
    private static final long serialVersionUID = 1L;

    private String itemId;

    /**
     * This component at moment only handles the presence of a String.
     * 
     * @param itemId The {@link String} indicating the {@link Item} that
     *      the current {@link Pokemon} is holding.
     */
    public ItemComponent(final String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return 'true' if this {@link Pokemon} has an item.
     */
    public boolean isItemPresent() {
        return !this.itemId.isEmpty();
    }

    /**
     * @return 'true' if the item is successfully removed.
     *      'false' when it wasn't possible or the item was already missing.
     */
    public boolean removeItem() {
        if ("".equals(this.itemId)) {
            this.itemId = "";
            return true;
        }
        return false;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public ItemComponent copyOf() {
        return new ItemComponent(this.itemId);
    }

    /**
     * {@inheritDoc}
     *
     * @param o {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ItemComponent that = (ItemComponent) o;
        return Objects.equals(this.itemId, that.itemId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this.itemId);
    }
}
