package clashclass.battle.battlereport;

import clashclass.commons.BuildingTypeComponent;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;


/**
 * Manager for tracking village destruction during battle.
 * Implements DestructionObserver to receive notifications when buildings are destroyed.
 */
public class VillageDestructionManagerImpl extends AbstractComponent implements VillageDestructionManager {

    private final BattleReportController battleReportController;

    /**
     * Constructor that takes a BattleReportController to update.
     *
     * @param battleReportController The controller to update when buildings are destroyed
     */
        public VillageDestructionManagerImpl(final BattleReportController battleReportController) {
        this.battleReportController = battleReportController;
    }

    /**
     * {@inheritDoc}
     * Called when a GameObject (building) is destroyed.
     * Updates the BattleReportController with the destruction information.
     */
    @Override
    public void notifyDestruction(final GameObject destroyedBuilding) {
        if (destroyedBuilding.getComponentOfType(BuildingTypeComponent.class).get()
                .getBuildingType().equals(VillageElementData.WALL)) {
            return;
        }

        // Increase the destruction percentage in the battle report
        battleReportController.increaseDestructionPercentage(destroyedBuilding);

        // Check if the destroyed object has resources to steal
        // This is a placeholder - in a real implementation, you would check if the
        // GameObject has a component that contains resources
        // For example:
        // if (obj.hasComponent(BuildingResourceComponent.class)) {
        //     ResourceManager resources = obj.getComponent(BuildingResourceComponent.class).getResources();
        //     battleReportController.increaseStolenResources(resources);
        // }
    }
}
