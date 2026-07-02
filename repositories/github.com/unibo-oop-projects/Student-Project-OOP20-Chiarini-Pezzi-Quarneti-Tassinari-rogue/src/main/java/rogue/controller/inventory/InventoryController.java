package rogue.controller.inventory;

import rogue.model.items.Item;
import rogue.model.items.inventory.Inventory;
import rogue.model.items.inventory.ScrollContainer;
import rogue.model.items.scroll.Scroll;

/**
 * An interface modeling the player's {@link Inventory} controller.
 */
public interface InventoryController {

    /**
     * Use an item when clicked with First Mouse button.
     * @param col of the inventory slot.
     * @param row of the inventory slot.
     * @return true if correctly used, false otherwise.
     */
    boolean onPrimaryClick(int col, int row);

    /**
     * Remove an item when clicked with Second Mouse button. 
     * @param col of the inventory slot.
     * @param row of the inventory slot.
     * @return true if correctly removed, false otherwise.
     */
    boolean onSecondaryClick(int col, int row);

    /**
     * Swaps two items when both clicked with the Middle Mouse Button.
     * @param col of the second inventory slot.
     * @param row of the second inventory slot.
     * @param swapping of the first inventory slot.
     * @return true if correctly swapped, false otherwise.
     */
    boolean onMiddleClick(int col, int row, int swapping);

    /**
     * Detaches the ring when clicked with First Mouse Button.
     * @return true if correctly removed, false otherwise.
     */
    boolean onRingContainer();

    /**
     * Gets the item in the given slot.
     * @param col of the second inventory slot.
     * @param row of the second inventory slot.
     * @return Item or null if the slot was empty.
     */
    Item getItem(int col, int row);

    /**
     * Get the amount of the item in the given slot.
     * @param col of the second inventory slot.
     * @param row of the second inventory slot.
     * @return amount of item in the slot, 0 if empty
     */
    int getAmount(int col, int row);

    /**
     * Get the currently active scroll.
     * @return active scroll, null if there's no active scroll.
     */
    Scroll getActiveScroll();

    /**
     * Get the active scroll duration.
     * @return currently active scroll remaining turns.
     */
    int getActiveScrollDuration();

    /**
     * Check if there's an active ring.
     * @return true if there's an active ring, false otherwise.
     */
    boolean checkActiveRing();

    /**
     * Get the player's inventory.
     * @return the player's inventory
     */
    Inventory getInventory();

    /**
     * Get the player's scroll container.
     * @return the player's scroll container
     */
    ScrollContainer getScrollContainer();
}
