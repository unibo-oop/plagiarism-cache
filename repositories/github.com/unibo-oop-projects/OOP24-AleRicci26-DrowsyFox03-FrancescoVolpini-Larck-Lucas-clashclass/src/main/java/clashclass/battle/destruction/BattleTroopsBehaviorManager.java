package clashclass.battle.destruction;

import clashclass.ecs.GameObject;

/**
 * Interface for managing the behavior of troops in battle.
 * This interface is used to notify troops about destruction events in battle.
 */
public interface BattleTroopsBehaviorManager extends DestructionObserver {
    /**
     * Updates the behavior of troops based on the current state of the battle.
     * This method should be called whenever the state of the battle changes.
     *
     * @param destroyedBuilding the destroyed building.
     */
    void updateTroopsBehavior(GameObject destroyedBuilding);
}
