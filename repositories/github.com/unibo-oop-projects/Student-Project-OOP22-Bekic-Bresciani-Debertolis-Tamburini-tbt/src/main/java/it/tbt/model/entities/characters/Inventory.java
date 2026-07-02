package it.tbt.model.entities.characters;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.tbt.model.entities.items.Item;

/**
 * Character's inventory.
 */
public class Inventory {
    private final Map<Item, Integer> items;

    /**
     * Default/empty Constructor.
     */
    public Inventory() {
        this.items = new HashMap<>();
    }

    /**
     * Create a new inventory based on the given collection of items.
     * @param newItems
     */
    public Inventory(final Collection<Item> newItems) {
        this.items = new HashMap<>();
        for (final Item item : newItems) {
            this.items.put(item, 1);
        }
    }

    /**
     * Create a new inventory based on the given map<Item, Integer>.
     * @param newItems
     */
    public Inventory(final Map<Item, Integer> newItems) {
        this.items = new HashMap<>(newItems);
    }

    /**
     * Get inventory contents as a list of items.
     * @return list of items in the inventory
     */
    public Map<Item, Integer> getItems() {
        return Map.copyOf(items);
    }

    /**
     * Add an item to the inventory.
     * @param item
     */
    public void addItem(final Item item) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + 1);
        } else {
            items.put(item, 1);
        }
    }

    /**
     * Remove an item from the inventory.
     * @param item
     * @return true if the item was found and removed
     */
    public boolean removeItem(final Item item) {
        if (items.containsKey(item)) {
            final int count = items.get(item);
            if (count > 1) {
                items.put(item, count - 1);
            } else {
                items.remove(item);
            }
            return true;
        } else {
            return false;
        }
    }
}
