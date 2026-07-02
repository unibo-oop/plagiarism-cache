package unibo.exiled.model.item;

import java.util.Map;

/**
 * The Inventory interface represents a collection of items in a game or
 * application.
 * It provides methods for managing items such as adding, retrieving quantity,
 * removing, and checking item presence.
 */
public interface Inventory {

    /**
     * Adds the specified item to the inventory.
     *
     * @param item The item to be added to the inventory.
     */
    void addItem(Item item);

    /**
     * Adds the specified quantity of the item to the inventory.
     *
     * @param item     The item to be added to the inventory.
     * @param quantity The quantity of the item to be added.
     */
    void addItem(Item item, int quantity);

    /**
     * Retrieves the quantity of the specified item in the inventory.
     *
     * @param item The item whose quantity needs to be retrieved.
     * @return The quantity of the specified item in the inventory.
     */
    Integer getItemQuantity(Item item);

    /**
     * Removes the specified item from the inventory.
     *
     * @param item The item to be removed from the inventory.
     */
    void removeItem(Item item);

    /**
     * Checks if the inventory contains the specified item.
     *
     * @param item The item to check for in the inventory.
     * @return true if the inventory contains the item, false otherwise.
     */
    boolean containsItem(Item item);

    /**
     * returns a map of the objects, the integer specifies the number of that
     * specific item in the inventory.
     *
     * @return the items and the number of the items in the inventory.
     */
    Map<Item, Integer> getItems();
}
