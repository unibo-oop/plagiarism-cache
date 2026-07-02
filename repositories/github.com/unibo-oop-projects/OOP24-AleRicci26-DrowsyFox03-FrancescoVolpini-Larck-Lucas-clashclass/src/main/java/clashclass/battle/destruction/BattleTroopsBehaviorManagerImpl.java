package clashclass.battle.destruction;

import clashclass.battle.manager.BattleManagerController;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of BattleTroopsBehaviorManager.
 * This class is used to notify troops about destruction events in battle.
 * It calls AiNodesBuilder to update the behavior of troops.
 */
public class BattleTroopsBehaviorManagerImpl extends AbstractComponent implements BattleTroopsBehaviorManager {
    private final BattleManagerController battleManagerController;
    private final List<GameObject> troops;

    /**
     * Constructor for BattleTroopsBehaviorManagerImpl.
     * Initializes the list of troops.
     *
     * @param battleManagerController the battle manager controller
     */
        public BattleTroopsBehaviorManagerImpl(final BattleManagerController battleManagerController) {
        this.troops = new ArrayList<>();
        this.battleManagerController = battleManagerController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDestruction(final GameObject destroyedBuilding) {
        // When a building is destroyed, update the behavior of troops
        updateTroopsBehavior(destroyedBuilding);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTroopsBehavior(final GameObject destroyedBuilding) {
        this.battleManagerController.updateVillageState(destroyedBuilding);

        // For now, just log that the behavior is being updated
        // System.out.println("Updating troops behavior after destruction event");
    }

    /**
     * Adds a troop to the list of troops to be managed.
     *
     * @param troop the troop to add
     */
    public void addTroop(final GameObject troop) {
        if (troop != null && !troops.contains(troop)) {
            troops.add(troop);
        }
    }

    /**
     * Removes a troop from the list of troops to be managed.
     *
     * @param troop the troop to remove
     */
    public void removeTroop(final GameObject troop) {
        troops.remove(troop);
    }
}
