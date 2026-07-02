package clashclass.village.manager;

import clashclass.elements.buildings.VillageElementData;
import clashclass.gamestate.GameStateController;
import clashclass.village.Village;

/**
 * Represents a player village controller.
 */
public interface PlayerVillageController extends GameStateController {
    /**
     * Opens the shop menu.
     */
    void openShop();

    /**
     * Goes to battle mode.
     */
    void openBattleMode();

    /**
     * Adds a building to the village.
     *
     * @param buildingType the type of the building
     */
    void addBuilding(VillageElementData buildingType);

    /**
     * Gets the player village.
     *
     * @return the player village
     */
    Village getPlayerVillage();
}
