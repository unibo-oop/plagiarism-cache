package clashclass.battle.battlereport;

import clashclass.ecs.GameObject;
import clashclass.gamestate.GameStateManager;
import clashclass.resources.ResourceManager;

/**
 * Interface for the Battle Report Controller.
 * Manages the battle report data including destruction percentage and stolen resources.
 */
public interface BattleReportController {
    /**
     * Increases the destruction percentage of the village.
     * This is called when a building is destroyed.
     *
     * @param destroyedBuilding the destroyed building
     */
    void increaseDestructionPercentage(GameObject destroyedBuilding);

    /**
     * Increases the amount of stolen resources.
     *
     * @param resourceManager The resource manager containing the resources to be added
     */
    void increaseStolenResources(ResourceManager resourceManager);

    /**
     * Sets the total number of troops used in the battle.
     *
     * @param count The total number of troops used
     */
    void setTroopCount(int count);

    /**
     * Get the total number of troops used in the battle.
     *
     * @return The total number of troops used
     */
    int getTroopCount();

    /**
     * Gets the village destruction percentage.
     *
     * @return the destruction percentage
     */
    double getDestructionPercentage();

    /**
     * Shows the menu.
     */
    void show();

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
     * Goes back to the player village.
     */
    void goBackToVillage();
}
