package it.unibo.controller.inventory.api;

import java.util.List;

import it.unibo.model.shop.api.ShopItem;
import it.unibo.view.inventory.api.InventoryView;

/**
 * Controller interface for managing activation of power ups and skins.
 */
public interface InventoryController {

    /**
     * Set the view for this controller and refresh it with current data.
     * 
     * @param view the inventory view to set
     */
    void setView(InventoryView view);

    /**
     * Select a skin from the list of skins.
     * 
     * @param index index in the list of skins
     */
    void selectSkin(int index);

    /**
     * Increases the level of jump to the next.
     */
    void plusJump();

    /**
     * Decreases the level of jump to the previus.
     */
    void minusJump();

    /**
     * Increases the level of speed to the next.
     */
    void plusVelocity();

    /**
     * Decreases the level of speed to the previus.
     */
    void minusVelocity();

    /**
     * Activate/Disactivate a temporary power up.
     * 
     * @param index index of temporary power ups in the inventory
     */
    void toggleTemporaryItem(int index);

    /**
     * Get the currently selected jump level.
     * 
     * @return the currently selected jump level
     */
    int getSelectedJumpLevel();

    /**
     * Get the currently selected speed level.
     * 
     * @return the currently selected speed level
     */
    int getSelectedSpeedLevel();

    /**
     * Get the maximum jump level owned by the player.
     * 
     * @return the maximum jump level owned by the player
     */
    int getMaxJumpLevelOwned();

    /**
     * Get the maximum speed level owned by the player.
     * 
     * @return the maximum speed level owned by the player
     */
    int getMaxSpeedLevelOwned();

    /**
     * Get the currently equipped skin.
     * 
     * @return the id of the currently equipped skin
     */
    String getEquippedSkin();

    /**
     * Get the list of owned skins.
     * 
     * @return the list of owned skins
     */
    List<ShopItem> getOwnedSkins();

    /**
     * Get the list of owned temporary power ups.
     * 
     * @return the list of owned temporary power ups
     */
    List<ShopItem> getOwnedTempItems();

    /**
     * Get the list of temporary power ups status.
     * 
     * @return the list of temporary power ups status
     */
    List<Boolean> getTempItemsStatus();

    /**
     * Open the inventory view.
     */
    void openInventory();

    /**
     * Open the shop view.
     */
    void openShop();

    /**
     * Exit from the Inventory and back to the menù.
     */
    void exit();

    /**
     * Returns the remaining matches for each consumable item in the order of their
     * IDs.
     * 
     * @return a list of remaining matches for each consumable item, sorted by item
     *         ID
     */
    List<Integer> getTempItemsDuration();
}
