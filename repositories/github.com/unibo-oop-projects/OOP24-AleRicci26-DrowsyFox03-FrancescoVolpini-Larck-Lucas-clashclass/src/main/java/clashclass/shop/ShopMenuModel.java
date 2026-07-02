package clashclass.shop;

import clashclass.gamestate.GameStateManager;
import clashclass.village.manager.PlayerVillageController;

/**
 * Represents a shop menu model.
 */
public interface ShopMenuModel {
    /**
     * Gets the shop manager.
     *
     * @return the shop manager
     */
    ShopManager getShopManager();

    /**
     * Sets the game state manager.
     *
     * @param gameStateManager the game state manager
     */
    void setGameStateManager(GameStateManager gameStateManager);

    /**
     * Gets the game state manager.
     *
     * @return the game state manager
     */
    GameStateManager getGameStateManager();

    /**
     * Tries to buy an item.
     *
     * @param item the itme
     *
     * @return true if the item has been bought
     */
    boolean tryToBuyItem(ShopItem item);

    /**
     * Sets the player village controller reference.
     *
     * @param playerVillageController the player village controller
     */
    void setPlayerVillageController(PlayerVillageController playerVillageController);
}
