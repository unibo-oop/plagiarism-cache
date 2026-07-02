package model.demand;

import java.util.Map;
import java.util.Optional;

import model.construction.ConstructionType;
import model.resources.ResourceType;

/**
 * This class is used to save the resources and/or the buildings required to unlock an objective/achievements.
 */
public class DemandImpl implements Demand {

    private final Optional<Map<ResourceType, Integer>> requiredResources;
    private final Optional<Map<ConstructionType, Integer>> requiredBuildings;

    /**
     * 
     * @param resources
     *          list of necessary resources
     * @param buildings
     *          list of necessary buildings
     */
    public DemandImpl(final Map<ResourceType, Integer> resources, final Map<ConstructionType, Integer> buildings) {
        this.requiredResources = Optional.ofNullable(resources);
        this.requiredBuildings = Optional.ofNullable(buildings);
    }

    /*
     * (non-Javadoc)
     * @see model.demand.Demand#getResources()
     */
    @Override
    public Optional<Map<ResourceType, Integer>> getResources() {
        return this.requiredResources;
    }

    /*
     * (non-Javadoc)
     * @see model.demand.Demand#getBuildings()
     */
    @Override
    public Optional<Map<ConstructionType, Integer>> getBuildings() {
        return this.requiredBuildings;
    }
}
