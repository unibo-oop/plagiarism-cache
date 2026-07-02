package clashclass.commons;

import clashclass.ecs.Component;
import clashclass.elements.buildings.VillageElementData;

/**
 * Interface representing the building type component.
 * Provides a method to retrieve the building type associated with a GameObject.
 */
public interface BuildingTypeComponent extends Component {
    /**
     * Returns the building type associated with this component.
     *
     * @return the building type as a VillageElementData enum
     */
    VillageElementData getBuildingType();
}
