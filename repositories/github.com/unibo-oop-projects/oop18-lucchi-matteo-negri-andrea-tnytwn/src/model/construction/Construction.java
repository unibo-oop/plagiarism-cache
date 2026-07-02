package model.construction;

import javafx.util.Pair;

/**
 * Interface used to indicate every kind of element that can be placed on the Game map.
 * Refactored after Andrea Scarpellini left the project, for various reasons.
 */
public interface Construction {
    /**
     * @return BuildingType
     *          The category of this building.
     */
    ConstructionType getType();
    /**
     * @return Pair<Integer, Integer>
     *          The position on the map of this building.
     */
    Pair<Integer, Integer> getPosition();
    /**
     * @return boolean
     *          true: if the building is reached by a road-type building.
     *          false: if the building isn't reached by a road-type building.
     */
    boolean isReachedByRoad();
    /**
     * @return boolean
     *          true: if the building is locked and unable to produce or consume resources.
     *          false: if the building is doing fine.
     */
    boolean isLocked();
}
