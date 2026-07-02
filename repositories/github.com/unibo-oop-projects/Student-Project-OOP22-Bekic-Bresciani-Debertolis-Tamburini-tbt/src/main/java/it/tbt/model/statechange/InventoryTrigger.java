package it.tbt.model.statechange;

/**
 * Class for triggering an Inventory GameState on a StateObserver object.
 */
public class InventoryTrigger {

    private final StateObserver stateObserver;

    /**
     * @param stateObserver the state observer to be notified.
     */
    public InventoryTrigger(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }

    /**
     * Constructor replace of clone.
     * @param inventoryTrigger to clone.
     */
    public InventoryTrigger(final InventoryTrigger inventoryTrigger) {
        this.stateObserver = inventoryTrigger.stateObserver;
    }

    /**
     * Notifies the stateObserver of the will on changing the GameState.
     */
    public void triggerInventory() {
        this.stateObserver.onInventory();
    }


}
