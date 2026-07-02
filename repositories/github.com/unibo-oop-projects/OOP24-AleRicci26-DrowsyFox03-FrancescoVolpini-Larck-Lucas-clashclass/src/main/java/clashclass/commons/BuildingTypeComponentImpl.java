package clashclass.commons;

import clashclass.ecs.AbstractComponent;
import clashclass.elements.buildings.VillageElementData;

/**
 * Represents a {@link BuildingTypeComponent} implementation.
 */
public class BuildingTypeComponentImpl extends AbstractComponent implements BuildingTypeComponent {
    private final VillageElementData buildingType;

    /**
     * Constructs the component with the specified building type.
     *
     * @param buildingType the building type to associate with this component
     */
    public BuildingTypeComponentImpl(final VillageElementData buildingType) {
        this.buildingType = buildingType;
    }

    /**
     * @inheritDoc
     */
    @Override
    public VillageElementData getBuildingType() {
        return this.buildingType;
    }
}
