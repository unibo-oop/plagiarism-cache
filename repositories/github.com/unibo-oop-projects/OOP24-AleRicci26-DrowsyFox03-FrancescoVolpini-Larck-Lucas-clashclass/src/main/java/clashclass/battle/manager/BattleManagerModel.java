package clashclass.battle.manager;

import clashclass.battle.battlereport.BattleReportView;
import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.troops.TroopType;
import clashclass.gamestate.GameStateManager;
import clashclass.village.Village;

import java.util.Set;

/**
 * Represents the Model-part of the battle manager.
 */
public interface BattleManagerModel {
    /**
     * Sets the game state manager.
     *
     * @param gameStateManager the game state manager
     */
    void setGameStateManager(GameStateManager gameStateManager);

    /**
     * Sets the current selected troop.
     *
     * @param troopType the current selected troop
     */
    void setCurrentSelectedTroop(TroopType troopType);

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
     * Gets the battle village.
     *
     * @return the battle village
     */
    Village getBattleVillage();

    /**
     * Gets the current selected troop.
     *
     * @return the current selected troop
     */
    TroopType getCurrentSelectedTroop();

    /**
     * Creates a new troop in the battle village.
     *
     * @param position the position of the troop
     */
    void createTroop(Vector2D position);

    /**
     * Gets the current active (alive) troops.
     *
     * @return the current active troops
     */
    Set<GameObject> getActiveTroops();

    /**
     * Sets the controller reference.
     *
     * @param controller the controller
     */
    void setController(BattleManagerController controller);

    /**
     * Updates the village state (buildings) for troops.
     *
     * @param destroyedBuilding the building that has been destroyed
     */
    void updateVillageState(GameObject destroyedBuilding);

    /**
     * Updates the troops state (troops) for defense buildings.
     *
     * @param destroyedTroop the troop that has been destroyed
     */
    void updateTroopsState(GameObject destroyedTroop);

    /**
     * Clears the scene.
     */
    void clearScene();

    /**
     * Builds the battle report.
     *
     * @param view the battle report view
     */
    void buildBattleReport(BattleReportView view);

    /**
     * Checks if the battle has already started.
     *
     * @return true if the battle has already started
     */
    boolean isBattleStarted();

    /**
     * Checks if the battle time is finished.
     *
     * @return true if the battle time is finished
     */
    boolean isBattleTimeFinished();

    /**
     * Checks if all the troops are dead.
     *
     * @return true if all the troops are dead
     */
    boolean areAllTroopsDead();

    /**
     * Shows the battle report.
     */
    void showBattleReport();

    /**
     * Gets the battle remaining time.
     *
     * @return the battle remaining time
     */
    long getBattleRemainingTime();
}
