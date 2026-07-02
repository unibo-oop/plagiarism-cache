package unibo.exiled.model.item;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the player inventory.
 */
public final class InventoryImpl implements Inventory {

    /**
     * An association between an item and an integer, to have an ordered graphical list.
     */
    private final Map<Item, Integer> itemsList;

    /**
     * Constructor of the inventory implementation.
     */
    public InventoryImpl() {
        itemsList = new HashMap<>();
    }

    @Override
    public void addItem(final Item item) {
        if (containsItem(item)) {
            itemsList.put(item, itemsList.get(item) + 1);
        } else {
            itemsList.putIfAbsent(item, 1);
        }
    }

    @Override
    public void addItem(final Item item, final int quantity) {
        itemsList.put(item, quantity);
    }

    @Override
    public void removeItem(final Item item) {
        if (containsItem(item)) {
            final int quantity = itemsList.get(item);
            if (quantity > 1) {
                itemsList.put(item, quantity - 1);
            } else if (quantity == 1) {
                itemsList.remove(item);
            }
        }
    }

    @Override
    public Integer getItemQuantity(final Item item) {
        return itemsList.get(item);
    }

    @Override
    public boolean containsItem(final Item item) {
        return itemsList.containsKey(item);
    }

    @Override
    public Map<Item, Integer> getItems() {
        return Collections.unmodifiableMap(itemsList);
    }
}
