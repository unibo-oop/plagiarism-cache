package it.tbt.commons.customtypes;

import it.tbt.model.entities.items.Item;
import javafx.util.Pair;

import java.io.Serial;

/**
 * The {@code ItemPair} class represents a pair consisting of an {@link Item} and an {@code Integer} value.
 * It extends the {@link javafx.util.Pair} class.
 * <p>
 * This class provides a convenient way to associate an item with its corresponding quantity.
 * </p>
 */
public class ItemPair extends Pair<Item, Integer> {

    @Serial
    private static final long serialVersionUID = 6613096542675407381L;

    /**
     * Creates a new {@code ItemPair} with the specified key and value.
     *
     * @param key   The item representing the key for this pair.
     * @param value The integer representing the value associated with the key.
     */
    public ItemPair(final Item key, final Integer value) {
        super(key, value);
    }

    /**
     * Returns a string representation of this {@code ItemPair}.
     * The string representation includes the item's string representation and the associated quantity.
     *
     * @return A string representation of this {@code ItemPair}.
     */
    @Override
    public String toString() {
        return this.getKey().toString() + "\t\tx" + this.getValue();
    }
}
