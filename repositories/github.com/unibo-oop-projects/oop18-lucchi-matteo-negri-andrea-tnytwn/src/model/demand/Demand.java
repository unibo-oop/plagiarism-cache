package model.demand;

import java.util.Map;
import java.util.Optional;

import model.construction.ConstructionType;
import model.resources.ResourceType;

/**
 * This interface is used to specify the items required to unlock an objective/achievements.
 */
public interface Demand {

    /**
     * The method return the list of the required resources to unlock an objective/achievements.
     * 
     * @return
     *          a Map with the required resources
     */
    Optional<Map<ResourceType, Integer>> getResources();

    /**
     * The method return the list of the required buildings to unlock an objective/achievements.
     * 
     * @return
     *          a Map with the required buildings
     */
    Optional<Map<ConstructionType, Integer>> getBuildings();
}
