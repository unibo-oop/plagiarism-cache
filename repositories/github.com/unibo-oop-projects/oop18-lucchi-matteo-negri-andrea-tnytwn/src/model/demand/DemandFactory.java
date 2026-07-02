package model.demand;

import java.util.Map;

import model.construction.ConstructionType;
import model.resources.ResourceType;

/**
 * This interface is used to implements a factory of Demands.
 */
public interface DemandFactory {
    /**
     * The method allows the creation of a Demand for an objective/achievement that require only resources to be unlocked.
     * 
     * @param resources 
     *          the required resources for the objective/achievement
     * @return
     *          the Demand object
     */
    Demand createDemandOfResources(Map<ResourceType, Integer> resources);

    /**
     * The method allows the creation of a Demand for an objective/achievement that require only buildings to be unlocked.
     * 
     * @param buildings
     *          the required buildings for the objective/achievement
     * @return
     *          the Demand object
     */
    Demand createDemandOfBuildings(Map<ConstructionType, Integer> buildings);

    /**
     * The method allows the creation of a Demand for an objective/achievement that require both resources and buildings to be unlocked.
     * 
     * @param resources
     *          the required resources for the objective/achievement
     * @param buildings
     *          the required buildings for the objective/achievement
     * @return
     *          the Demand object
     */
    Demand createCompleteDemand(Map<ResourceType, Integer> resources, Map<ConstructionType, Integer> buildings);
}
