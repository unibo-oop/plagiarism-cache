package rogue.model.items.inventory;

import java.util.Optional;

import rogue.model.events.EventPublisher;
import rogue.model.items.Item;

/**
 * An interface for modeling a game Inventory.
 *
 */
public interface Inventory extends EventPublisher {

    /**
     * Use the item contained in the index slot.
     * @param index of the slot to select.
     * @return true if the item was correctly used, false if 
     * item cannot be consumed.
     * @throws OutOfInventoryException if given an invalid index.
     */
    boolean useItem(int index) throws OutOfInventoryException;

    /**
     * Get the item contained in the index slot of the inventory.
     * @param index of the wanted slot.
     * @return Optional.empty if given slot is empty, Optional.of(Item)
     * otherwise.
     * @throws OutOfInventoryException if given an invalid index.
     */
    Optional<Item> getItem(int index) throws OutOfInventoryException;

    /**
     * Get the amount of an Item in a given slot.
     * @param index
     * @return amount of item in index, 0 if empty slot.
     * @throws OutOfInventoryException if given an invalid index.
     */
    int getAmount(int index) throws OutOfInventoryException;

    /**
     * Add an item to the inventory.
     * @param item to add to the inventory.
     * @return true if item was correctly added, false if
     * the inventory is full of that item.
     * @throws InventoryIsFullException if the inventory is full.
     */
    boolean addItem(Item item) throws InventoryIsFullException;

    /**
     * Swaps the contents of two slots.
     * @param first slot to swap.
     * @param second slot to swap.
     * @return true if the two slots have been correctly swapped, false
     * if given two empty slots indexes.
     * @throws OutOfInventoryException if given an invalid index.
     */
    boolean swap(int first, int second) throws OutOfInventoryException;

    /**
     * Get the scroll container which contains currently active
     * scroll (could also be empty).
     * @return ScrollContainer associated with the inventory.
     */
    ScrollContainer getScrollContainer();

    /**
     * Remove the item in the given slot.
     * @param index of the slot to remove the contained item.
     * @return true if item correctly removed, false if slot is empty.
     * @throws OutOfInventoryException if given an invalid index.
     */
    boolean remove(int index) throws OutOfInventoryException;

    /**
     * Updates the inventory view.
     */
    void updateInventory();

}
