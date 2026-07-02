package clashclass.battle.battlereport;

import clashclass.battle.destruction.DestructionObserver;
import clashclass.ecs.GameObject;

/**
 * Interface for the VillageDestructionManager.
 * Responsible for tracking the destruction of village elements and updating the battle report.
 */
public interface VillageDestructionManager extends DestructionObserver {

    /**
     * Notify the destruction of a GameObject.
     * Updates the battle report with the increased destruction percentage.
     *
     * @param obj the object that has been destroyed
     */
    @Override
    void notifyDestruction(GameObject obj);
}
