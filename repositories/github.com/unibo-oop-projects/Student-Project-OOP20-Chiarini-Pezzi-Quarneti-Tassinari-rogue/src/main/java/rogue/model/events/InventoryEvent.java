package rogue.model.events;

import rogue.model.items.inventory.Inventory;

/**
 * A class representing the change of the player's inventory.
 *
 * @param <L> the type of Inventory affected by the change.
 */
public class InventoryEvent<L extends Inventory> implements Event {

    private final L inventory;

    /**
     * Creates a new LifeEvent.
     * @param inventory that just changed
     */
    public InventoryEvent(final L inventory) {
        this.inventory = inventory;
    }

    /**
     * @return the inventory
     */
    public L getInventory() {
        return this.inventory;
    }

}
