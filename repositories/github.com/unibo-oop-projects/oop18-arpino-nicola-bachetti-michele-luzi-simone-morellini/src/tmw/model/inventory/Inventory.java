package tmw.model.inventory;

import java.util.List;
import java.util.Optional;

import tmw.model.item.Item;

/**
 * Interface to represent the character's inventory where the items he collects
 * on the map are placed.
 */
public interface Inventory {

    /**
     * This method returns if the inventory is full or not.
     * 
     * @return whether the inventory is full.
     */
    boolean isFull();

    /**
     * This method add the passed item in the Inventory.
     * 
     * @param item The item to add.
     */
    void addItem(Item item);

    /**
     * This method remove an item from the Inventory.
     * 
     * @param index The index of the item to be removed.
     */
    void removeItem(int index);

    /**
     * This method return the item in a specific position of the inventory. It will
     * be called when the user decides to use that item. If there is no item in this
     * position an Optional.empty will be returned
     * 
     * @param index The index of the item
     * @return an optional of the item
     */
    Optional<Item> getItem(int index);

    /**
     * Returns a list of the inventory's items.
     * 
     * @return the List of items in the inventory.
     */
    List<Optional<Item>> getAll();
}
