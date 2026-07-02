package it.unibo.model.shop.api;

import java.util.Map;
import java.util.Set;

import it.unibo.model.persistence.api.SaveState;

/**
 * Interface representing the inventory of a player in the shop.
 */
public interface Inventory {

    /**
     * Add an item to the inventory.
     * 
     * @param itemId the ID of the item to add
     */
    void addItem(String itemId);

    /**
     * Check if an item is in the inventory.
     * 
     * @param itemId the ID of the item to check
     * @return true if the item is in the player inventory, false otherwise
     */
    boolean hasItem(String itemId);

    /**
     * Get the set of owned item IDs.
     * 
     * @return a set of owned item IDs
     */
    Set<String> getOwnedItems();

    /**
     * Sets a skin as selected.
     * 
     * @param itemId the ID of the skin to select
     */
    void equipSkin(String itemId);

    /**
     * Deselect the currently selected skin.
     */
    void deselectSkin();

    /**
     * Get the currently selected skin, if any.
     * 
     * @return a string with the selected skin ID, or default skin if none is
     *         selected
     */
    String getSelectedSkin();

    /**
     * Add a consumable item with its duration in matches.
     * 
     * @param itemId          the ID of the consumable item to add
     * @param matchesDuration the duration of the consumable in matches
     */
    void addConsumable(String itemId, int matchesDuration);

    /**
     * Get the remaining matches for each consumable item.
     * 
     * @return a map of consumable item IDs to their remaining matches
     */
    Map<String, Integer> getConsumablesStatus();

    /**
     * Update the status of consumables, reducing their remaining matches by one.
     */
    void updateConsumables();

    /**
     * Initialize the inventory with data load from file.
     * 
     * @param state containing all the data
     */
    void loadState(SaveState state);

    /**
     * @return the currenly selected jump level.
     */
    int getSelectedJumpLevel();

    /**
     * Sets the jump level to use.
     * 
     * @param level level of the jump to select
     */
    void setSelectedJumpLevel(int level);

    /**
     * @return the currenly selected speed level.
     */
    int getSelectedSpeedLevel();

    /**
     * Sets the speed level to use.
     * 
     * @param level level of the speed to select
     */
    void setSelectedSpeedLevel(int level);

    /**
     * Toggle the activation status of temporary power ups.
     * Deselect automatically previus items with the same type.
     * 
     * @param itemId  id of the item to toggle
     * @param factory the factory to retrieve item type information
     */
    void toggleConsumable(String itemId, ShopItemFactory factory);

    /**
     * @return the Set of Id of currently active temporary power ups.
     */
    Set<String> getActiveConsumables();

    /**
     * @return the total amount of coins available in the inventory.
     */
    int getTotalCoins();

    /**
     * Adds coins to the inventory.
     * 
     * @param amount the amount to add.
     */
    void addCoins(int amount);

    /**
     * Spend coins from the inventory if enough coins are available.
     * 
     * @param amount the amount to spend.
     * @return true if the transaction is successful, false otherwise.
     */
    boolean spendCoins(int amount);

    /**
     * Sets the exact amount of coins.
     * 
     * @param coins the total coins.
     */
    void setTotalCoins(int coins);

    /**
     * Get the ShopItemFactory.
     * 
     * @return the ShopItemFactory instance
     */
    ShopItemFactory getFactory();
}
