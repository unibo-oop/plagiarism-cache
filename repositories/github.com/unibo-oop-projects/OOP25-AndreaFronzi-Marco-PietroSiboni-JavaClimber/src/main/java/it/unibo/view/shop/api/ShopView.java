package it.unibo.view.shop.api;

import java.util.List;

import it.unibo.model.shop.api.ShopItem;

/**
 * Interface representic the Shop View.
 */
public interface ShopView {

    /**
     * Display the shop view.
     */
    void display();

    /**
     * Update the displayed number of coins in the shop view.
     * 
     * @param coins the current number of coins owned by the player
     */
    void updateCoins(int coins);

    /**
     * Update the shop view with the current list of skins, permanent power ups and
     * temporary power ups available in the shop.
     * 
     * @param skins        the list of available skins
     * @param permUpgrades the list of available permanent power ups
     * @param tempUpgrades the list of available temporary power ups
     */
    void updateItems(List<ShopItem> skins, List<ShopItem> permUpgrades, List<ShopItem> tempUpgrades);

    /**
     * Show a message to the user in the shop view.
     * 
     * @param message the message to be displayed
     */
    void showMessage(String message);

    /**
     * Close the shop view.
     */
    void close();
}
