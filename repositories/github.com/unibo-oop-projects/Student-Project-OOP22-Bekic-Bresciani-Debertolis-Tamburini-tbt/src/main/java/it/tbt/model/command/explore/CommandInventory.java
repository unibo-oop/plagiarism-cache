package it.tbt.model.command.explore;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.command.api.Command;
import it.tbt.model.statechange.InventoryTrigger;

/**
 * Command to switch to {@link it.tbt.model.GameState#INVENTORY}.
 */

public class CommandInventory implements Command {

    private final InventoryTrigger inventoryTrigger;

    /**
     * @param inventoryTrigger which will be used to trigger the Inventory GameState.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "The Command pattern encapsulates the objects on which perform the operations.")
    public CommandInventory(final InventoryTrigger inventoryTrigger) {
        this.inventoryTrigger = inventoryTrigger;
    }

    /**
     * Triggers the {@link it.tbt.model.GameState#INVENTORY}.
     */
    @Override
    public void execute() {
        this.inventoryTrigger.triggerInventory();
    }
}
