package rogue.model.items.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.util.Pair;
import rogue.model.creature.Player;
import rogue.model.events.AbstractEventPublisher;
import rogue.model.events.InventoryEvent;
import rogue.model.items.Item;
import rogue.model.items.scroll.Scroll;
import rogue.model.items.scroll.ScrollImpl;

/**
 * An implementation for an {@link Inventory}.
 *
 */
public class InventoryImpl extends AbstractEventPublisher implements Inventory {

    private static final int INVENTORY_SIZE = 20;
    private static final int ITEM_AMOUNT_MAX = 10;

    private static final String OUT_OF_INVENTORY_MESSAGE = "Given index is out of the inventory.";
    private static final String FULL_INVENTORY_MESSAGE = "Inventory is full.";

    /**
     * Player's to which apply the item's use.
     */
    private final Player player;
    /*
     * Scroll container for the scroll effect.
     */
    private final ScrollContainer scrollContainer;
    /**
     * Inventory map.
     */
    private final Map<Integer, Pair<Optional<Item>, Integer>> inventory = new HashMap<>(INVENTORY_SIZE);

    public InventoryImpl(final Player player) {
        scrollContainer = new ScrollContainerImpl(player);
        this.player = player;
        for (int i = 1; i <= INVENTORY_SIZE; i++) {
            this.inventory.put(i, new Pair<>(Optional.empty(), 0));
        }
    }

    /**
     * Use the item in the given slot.
     * @param index of the inventory slot to select.
     * @return true if the inventory was correctly updated, false if there
     * was no inventory update (Item's use returned false).
     * @throws OutOfInventoryException if given index is out of the inventory.
     */
    public boolean useItem(final int index) throws OutOfInventoryException {
        if (inventory.containsKey(index)) {
            if (inventory.get(index).getKey().isPresent()) {
                /*
                 * Save to use Item.
                 */
                final Item toUse = inventory.get(index).getKey().get();
                /*
                 * Use the item, check if correctly used.
                 * if the item is scroll item, activate it.
                 */
                if (toUse.getClass().equals(ScrollImpl.class)) {
                    this.scrollContainer.activateScroll((Scroll) toUse);
                }
                if (!toUse.use(this.player)) {
                    /*
                     * If not correctly used the item can't be consumed,
                     * can't update inventory.
                     */
                    return false;
                } else  {
                /*
                 * Update Inventory.
                 */
                final int amount = inventory.get(index).getValue();
                if (amount == 1) {
                    //Last of that Item in inventory, make the optional empty.
                    inventory.put(index, new Pair<>(Optional.empty(), 0));
                } else {
                    //Lower amount of item by one after use.
                    inventory.put(index, new Pair<>(Optional.of(toUse), amount - 1));
                }
                this.post(new InventoryEvent<>(this));
                return true;
                }
            } else {
                return false;
            }
        }
        throw new OutOfInventoryException(OUT_OF_INVENTORY_MESSAGE);
    }

    /**
     * Get the item in the given slot.
     * @param index of the inventory slot to select.
     * @return the item contained in the given inventory slot.
     * @throws OutOfInventoryException if given index is out of the inventory.
     */
    public Optional<Item> getItem(final int index) throws OutOfInventoryException {
        if (this.inventory.containsKey(index)) {
            return this.inventory.get(index).getKey();
        }
        throw new OutOfInventoryException(OUT_OF_INVENTORY_MESSAGE);
    }

    /**
     * Adds an item to the Inventory.
     * @param item to add to the inventory.
     * @return true if the item was correctly added to the inventory, 
     * false if the inventory is full or if the inventory contains the
     * limit amount for the item.
     * @throws InventoryIsFullException if the inventory is full.
     */
    public boolean addItem(final Item item) throws InventoryIsFullException {
        /*
         * Checks if item is already contained in inventory.
         */
        for (int i = 1; i <= INVENTORY_SIZE; i++) {
            /*
             * skip empty slots
             */
             if (!this.inventory.get(i).getKey().isEmpty() && this.inventory.get(i).getKey().get().equals(item)) {
                 /*
                  * Inventory already contains the item to add.
                  * Increase it's quantity.
                  * Check if item's quantity is already at max.
                  */
                 if (this.inventory.get(i).getValue().equals(ITEM_AMOUNT_MAX)) {
                      /*
                      * Item's amount already at max, cannot add item to
                      * inventory.
                      */
                     return false;
                 } else {
                     /*
                      * Increase item's amount.
                      */
                     this.inventory.put(i, new Pair<>(this.inventory.get(i).getKey(), 
                     this.inventory.get(i).getValue() + 1));
                     this.post(new InventoryEvent<>(this));
                     return true;
                 }
             }
        }
        /*
         * Item's not already contained in inventory.
         * Look for first Empty slot
         */
        for (int j = 1; j <= INVENTORY_SIZE; j++) {
            if (this.inventory.get(j).getKey().isEmpty()) {
                /*
                 * Empty slot found, insert item.
                 */
                this.inventory.put(j, new Pair<>(Optional.of(item), 1));
                this.post(new InventoryEvent<>(this));
                return true;
            }
        }
        /*
         * No empty slot found, inventory is full.
         */
        throw new InventoryIsFullException(FULL_INVENTORY_MESSAGE);
    }

    /**
     * Swaps two items in the inventory.
     * @param first slot to swap.
     * @param second slot to swap.
     * @return true if correctly swapped, false if given
     * two empty slots.
     * @throws OutOfInventoryException if the given index is out of the inventory.
     */
    public boolean swap(final int first, final int second) throws OutOfInventoryException {
        /*
         * Even if this method is given a occupied slot and
         * an empty slot it works as a move method.
         * If given two empty slots nothing happens.
         */
        if (this.inventory.containsKey(first) && this.inventory.containsKey(second)) {
        /*
         * Check if both slots are empty.
         */
        if (this.inventory.get(first).getKey().isEmpty()
         && this.inventory.get(second).getKey().isEmpty()) {
            /*
             * Both slots are empty return false.
             */
            this.post(new InventoryEvent<>(this));
            return false;
        } else {
            /*
             * Execute swap.
             */
            final Pair<Optional<Item>, Integer> toSwap = this.inventory.get(first);
            this.inventory.put(first, this.inventory.get(second));
            this.inventory.put(second, toSwap);
            this.post(new InventoryEvent<>(this));
            return true;
        }
        }
        throw new OutOfInventoryException(OUT_OF_INVENTORY_MESSAGE);
    }

    /**
     * Get the inventory's Scroll Container.
     * @return ScrollContainer associated with inventory.
     */
    public ScrollContainer getScrollContainer() {
        return this.scrollContainer;
    }

    /**
     * Remove an item in the inventory.
     * @param index of the slot to remove.
     * @return true if correctly used, false 
     * @throws OutOfInventoryException if given index is out of the inventory.
     */
    public boolean remove(final int index) throws OutOfInventoryException {
        if (this.inventory.containsKey(index)) {
            if (this.inventory.get(index).getKey().isEmpty()) {
                /*
                 * given slot is empty, return false
                 */
                return false;
            }
            /*
             * remove item contained in slot.
             */
            inventory.put(index, new Pair<>(Optional.empty(), 0));
            this.post(new InventoryEvent<>(this));
            return true;
        }
        throw new OutOfInventoryException(OUT_OF_INVENTORY_MESSAGE);
    }

    /**
     * Get the amount of an Item in a given slot.
     * @param index of the wanted slot.
     * @return amount of the item in the slot, 0 if
     * empty slot.
     * @throws OutOfInventoryException if given index is out of the inventory.
     */
    public int getAmount(final int index) throws OutOfInventoryException {
        if (this.inventory.containsKey(index)) {
            return this.inventory.get(index).getValue();
        }
        throw new OutOfInventoryException(OUT_OF_INVENTORY_MESSAGE);
    }

    /**
     * Updates the inventory view.
     */
    public void updateInventory() {
        /*
         * Simply calls the subscriber method to update.
         */
        this.post(new InventoryEvent<>(this));
    }

}
