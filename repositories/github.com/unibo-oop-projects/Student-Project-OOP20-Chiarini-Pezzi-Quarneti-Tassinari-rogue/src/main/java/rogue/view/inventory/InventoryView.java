package rogue.view.inventory;

import rogue.controller.inventory.InventoryController;
import rogue.model.events.InventoryEvent;
import rogue.model.items.inventory.Inventory;
import rogue.model.items.inventory.OutOfInventoryException;

/**
 * An interface for the {@link Inventory} display.
 */
public interface InventoryView {

    /**
     * Updates the inventory when updated by the
     * controller.
     * @param event Inventory event.
     * @throws OutOfInventoryException if reading inventory with invalid index.
     */
    void update(InventoryEvent<Inventory> event) throws OutOfInventoryException;

    /**
     * Passes the controller to the InventoryView.
     * @param controller of the inventoryView
     */
    void init(InventoryController controller);

}
