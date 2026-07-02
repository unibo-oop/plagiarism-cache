package clashclass.battle.battlereport;

import clashclass.ecs.GameObject;
import clashclass.gamestate.GameStateManager;
import clashclass.resources.ResourceManager;

/**
 * Interface for the Battle Report Model.
 * Defines the data structure and operations for battle reports.
 */
public interface BattleReportModel {
    /**
     * Get the current destruction percentage of the village.
     *
     * @return The destruction percentage (0-100)
     */
    double getDestructionPercentage();

    /**
     * Set the destruction percentage of the village.
     *
     * @param percentage The new destruction percentage (0-100)
     */
    void setDestructionPercentage(double percentage);

    /**
     * Increase the destruction percentage by a calculated amount.
     * This is called when a building is destroyed.
     *
     * @param destroyedBuilding the destroyed building
     */
    void increaseDestructionPercentage(GameObject destroyedBuilding);

    /**
     * Get the number of stars earned in the battle (0-3).
     * In Clash of Clans style:
     * - 1 star: 50% destruction
     * - 2 stars: Town Hall destroyed
     * - 3 stars: 100% destruction
     *
     * @return The number of stars (0-3)
     */
    int getStars();

    /**
     * Get the resources stolen during the battle.
     *
     * @return The ResourceManager containing stolen resources
     */
    ResourceManager getStolenResources();

    /**
     * Set the resources stolen during the battle.
     *
     * @param resources The ResourceManager containing stolen resources
     */
    void setStolenResources(ResourceManager resources);

    /**
     * Add resources to the stolen resources.
     *
     * @param resources The ResourceManager containing resources to add
     */
    void addStolenResources(ResourceManager resources);

    /**
     * Check if the Town Hall has been destroyed.
     *
     * @return true if the Town Hall is destroyed, false otherwise
     */
    boolean isTownHallDestroyed();

    /**
     * Set the Town Hall destruction status.
     *
     * @param destroyed true if the Town Hall is destroyed, false otherwise
     */
    void setTownHallDestroyed(boolean destroyed);

    /**
     * Get the total number of troops used in the battle.
     *
     * @return The total number of troops used
     */
    int getTroopCount();

    /**
     * Set the total number of troops used in the battle.
     *
     * @param count The total number of troops used
     */
    void setTroopCount(int count);

    /**
     * Check if the battle resulted in a victory.
     * A victory is achieved if at least 1 star is earned.
     *
     * @return true if the battle was a victory, false otherwise
     */
    boolean isVictory();

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
}
