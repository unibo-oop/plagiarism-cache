package it.unibo.controller.shop.api;

import java.util.List;

import it.unibo.model.shop.api.ShopItem;
import it.unibo.view.shop.api.ShopView;

/**
 * Controller for managing shop transaction and upgrades.
 */
public interface ShopController {

    /**
     * Set the view for this controller and refresh it with current data.
     * 
     * @param view the shop view to set
     */
    void setView(ShopView view);

    /**
     * Attemps to purchase the next level of permanent jump power up.
     */
    void upgradeJump();

    /**
     * Attems to purchase the next level of permanent speed power up.
     */
    void upgradeSpeed();

    /**
     * Purchase a temporary power up by its index in shop catalog.
     * 
     * @param index the position of the item in the temporary items list
     */
    void buyTemporaryItem(int index);

    /**
     * Purchase a skin by its index in shop catalog.
     * 
     * @param index the position of the skin in the skins list
     */
    void buySkin(int index);

    /**
     * Get the list of skins available in the shop.
     * 
     * @return a list of skins in the shop
     */
    List<ShopItem> getSkins();

    /**
     * Get the list of temporary power ups available in the shop.
     * 
     * @return a list of temporary power ups in the shop
     */
    List<ShopItem> getPermanetUpgrades();

    /**
     * Get the list of temporary power ups available in the shop.
     * 
     * @return a list of temporary power ups in the shop
     */
    List<ShopItem> getTemporaryUpgrades();

    /**
     * Get the number of coins currently owned by the player.
     * 
     * @return the number of coins
     */
    int getCoins();

    /**
     * Checks if an item is already owned by the player.
     * 
     * @param item the item to check
     * @return true if owned, false otherwise
     */
    boolean isOwned(ShopItem item);

    /**
     * Get the current level of a specific permanent power up type, based on the
     * prefix of the item Id.
     * 
     * @param prefix the Id prefix for the power up type
     * @return the current level of the power up type
     */
    int getCurrentLevel(String prefix);

    /**
     * Open the shop view.
     */
    void openShop();

    /**
     * Open the inventory view.
     */
    void openInventory();

    /**
     * Exit from the Shop and back to the Menù.
     */
    void exit();

}
