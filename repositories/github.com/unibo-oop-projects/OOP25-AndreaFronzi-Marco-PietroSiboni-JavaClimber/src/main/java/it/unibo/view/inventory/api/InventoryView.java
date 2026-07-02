package it.unibo.view.inventory.api;

import java.util.List;

import it.unibo.model.shop.api.ShopItem;

/**
 * Interface for the inventory view.
 */
public interface InventoryView {

    /**
     * Display the inventory view.
     */
    void display();

    /**
     * Update the inventory view with the current state of owned skins, equipped
     * skin, permanent items, selected jump and speed levels, owned temporary items,
     * their active status, and remaining duration.
     * 
     * @param ownedSkins     the list of owned skins by the player
     * @param equippedSkinId the ID of the currently equipped skin
     * @param allPermItems   the list of all permanent items
     * @param selectedJump   the currently selected jump level
     * @param maxJump        the maximum jump level owned
     * @param selectedSpeed  the currently selected speed level
     * @param maxSpeed       the maximum speed level owned
     * @param ownedTemp      the list of owned temporary items
     * @param tempStatus     the list of active statuses for the temporary items
     * @param tempDuration   the list of remaining durations for the temporary items
     */
    void updateInventory(List<ShopItem> ownedSkins, String equippedSkinId, List<ShopItem> allPermItems,
            int selectedJump, int maxJump, int selectedSpeed, int maxSpeed, List<ShopItem> ownedTemp,
            List<Boolean> tempStatus, List<Integer> tempDuration);

    /**
     * Update the displayed number of coins in the inventory view.
     * 
     * @param coins the current number of coins owned by the player
     */
    void updateCoins(int coins);

    /**
     * Close the inventory view.
     */
    void close();
}
