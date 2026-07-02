package unibo.exiled.utilities;

import java.util.Map;
import java.util.Map.Entry;

import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.Item;

/**
 * Utility class for working with inventories.
 */
public final class Inventories {
    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private Inventories() {
    }

    /**
     * Creates a copy of the provided inventory.
     *
     * @param oldInventory The original inventory to be copied.
     * @return A new Inventory that is a copy of the original inventory.
     */
    public static Inventory copyOf(final Inventory oldInventory) {
        final Inventory newInventory = new InventoryImpl();
        final Map<Item, Integer> itemsOldInventory = oldInventory.getItems();
        for (final Entry<Item, Integer> item : itemsOldInventory.entrySet()) {
            newInventory.addItem(item.getKey(), item.getValue());
        }
        return newInventory;
    }
}
