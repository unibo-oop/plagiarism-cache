package clashclass.shop;

import clashclass.gamestate.GameStateManager;
import clashclass.village.manager.PlayerVillageController;

/**
 * Represents a shop menu controller.
 */
public interface ShopMenuController {
    /**
     * Gets the shop manager.
     *
     * @return the shop manager
     */
    ShopManager getShopManager();

    /**
     * Tries to buy an item.
     *
     * @param item the itme
     *
     * @return true if the item has been bought
     */
    boolean tryToBuyItem(ShopItem item);

    /**
     * Shows the menu.
     */
    void show();

    /**
     * Hides the menu.
     */
    void hide();

    /**
     * Clears the scene.
     */
    void clearScene();

    /**
     * Sets the game state manager.
     *
     * @param gameStateManager the game state manager
     */
    void setGameStateManager(GameStateManager gameStateManager);

    /**
     * Goes back to village.
     */
    void goBackToVillage();

    /**
     * Sets the player village controller reference.
     *
     * @param playerVillageController the player village controller
     */
    void setPlayerVillageController(PlayerVillageController playerVillageController);
}
