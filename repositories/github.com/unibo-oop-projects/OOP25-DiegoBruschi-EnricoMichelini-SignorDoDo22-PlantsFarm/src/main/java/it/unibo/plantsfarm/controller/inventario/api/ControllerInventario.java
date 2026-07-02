package it.unibo.plantsfarm.controller.inventario.api;

import java.util.Map;

import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;

/**
 * Controller interface for the inventory upgrade system.
 * This controller exposes the operations needed by the inventory UI
 * to query the player tools status and to perform upgrade actions.
 * It also manages the connection between the inventory view and the game panel (return a copie of inventory).
 */
public interface ControllerInventario {

    /**
     * Checks whether the given tool can be upgraded according to the current
     * inventory state (e.g., enough experience, not already at max level).
     *
     * @param tool the tool type to check
     * @return true if the tool can be upgraded, false otherwise
     */
    boolean isUpgredable(Tooltype tool);

    /**
     * Requests an upgrade for the given tool.
     * Implementations are expected to update the underlying model and refresh
     * the related inventory view if it is present.
     *
     * @param tool the tool type to upgrade
     */
    void pressUpgradeItem(Tooltype tool);

    /**
     * Creates and registers the inventory view using the provided game panel
     * as the owner/context for focus handling and positioning.
     *
     * @param gamePanel the main game panel used as context for the dialog/view
     */
    void addView(ImplViewGamePanel gamePanel);

    /**
     * Returns a snapshot of the inventory content that can be safely used by the view
     * for rendering purposes without modifying the original inventory structure.
     *
     * @return a snapshot map of tool types to items
     */
    Map<Tooltype, ItemsFarm> getInventoryClone();

    /**
     * Opens the inventory view, making it visible to the player.
     * If the view has not been initialized, this method should handle that case gracefully.
     */
    void openViewInv();

    /**
     * Updates the inventory view to reflect any changes in the inventory state.
     * This method should be called after any operation that modifies the inventory
     * to ensure the UI remains consistent with the underlying data.
     */
    void updateInventory();

}
