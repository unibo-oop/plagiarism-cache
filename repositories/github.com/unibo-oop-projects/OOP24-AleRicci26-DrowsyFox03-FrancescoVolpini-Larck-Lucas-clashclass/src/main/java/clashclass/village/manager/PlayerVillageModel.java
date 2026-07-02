package clashclass.village.manager;

import clashclass.elements.buildings.VillageElementData;
import clashclass.gamestate.GameStateManager;
import clashclass.shop.ShopMenuView;
import clashclass.village.Village;

/**
 * Represents a player village model.
 */
public interface PlayerVillageModel {
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
     * Gets the player village.
     *
     * @return the player village
     */
    Village getPlayerVillage();

    /**
     * Clears the scene.
     */
    void clearScene();

    /**
     * Build the shop menu.
     *
     * @param controller the player village controller reference
     * @param view the shop menu view
     */
    void buildShop(PlayerVillageController controller, ShopMenuView view);

    /**
     * Opens the shop menu.
     */
    void openShop();

    /**
     * Adds a building to the village.
     *
     * @param buildingType the type of the building
     */
    void addBuilding(VillageElementData buildingType);
}
