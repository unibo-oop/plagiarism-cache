package it.unibo.model.shop.api;

import java.util.List;

/**
 * Interface representing the shop manager responsible for handling shop
 * operations.
 */
public interface ShopManager {

    /**
     * Get the catalog of shop items.
     * 
     * @return a list of shop items available in the catalog
     */
    List<ShopItem> getCatalog();

    /**
     * Attempt to buy a shop item.
     * 
     * @param item the shop item to be purchased
     * @return true if the purchase was successful, false otherwise
     */
    boolean buyItem(ShopItem item);

    /**
     * Check if a shop item is already owned.
     * 
     * @param item the shop item to check
     * @return true if the item is already owned, false otherwise
     */
    boolean isAlreadyOwned(ShopItem item);

    /**
     * Check if the player can afford to buy a shop item.
     * 
     * @param item the shop item to check
     * @return true if the player has enough coins to buy the item, false otherwise
     */
    boolean canBuyItem(ShopItem item);

    /**
     * Get the player's inventory.
     * 
     * @return the player's {@link Inventory}
     */
    Inventory getInventory();

    /**
     * Get all skin items avaiable.
     * 
     * @return a list of skins in the catalog
     */
    List<ShopItem> getSkins();

    /**
     * Get all permanent power ups avaiable.
     * 
     * @return a list of permanent power up
     */
    List<ShopItem> getPermanentUpgrades();

    /**
     * Get all temporary power ups avaiable.
     * 
     * @return a list of temporary power up
     */
    List<ShopItem> getTemporaryUpgrades();

    /**
     * Get the total amount of coins of the player.
     * 
     * @return the total amount of coins
     */
    int getCoins();
}
