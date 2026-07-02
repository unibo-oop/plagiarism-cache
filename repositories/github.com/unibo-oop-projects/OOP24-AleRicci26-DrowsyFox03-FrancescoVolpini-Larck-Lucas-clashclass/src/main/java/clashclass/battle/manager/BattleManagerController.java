package clashclass.battle.manager;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.troops.TroopType;
import clashclass.gamestate.GameStateController;
import clashclass.village.Village;

import java.util.Set;

/**
 * Represents a scene manager for the game state 'battle'.
 */
public interface BattleManagerController extends GameStateController {
    /**
     * Ends the battle.
     */
    void endBattle();

    /**
     * Sets the current selected troop.
     *
     * @param troop the current selected troop
     */
    void setCurrentSelectedTroop(TroopType troop);

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
     * Gets the battle village.
     *
     * @return the battle village
     */
    Village getBattleVillage();

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
     * Gets the battle remaining time.
     *
     * @return the battle remaining time
     */
    long getBattleRemainingTime();
}
