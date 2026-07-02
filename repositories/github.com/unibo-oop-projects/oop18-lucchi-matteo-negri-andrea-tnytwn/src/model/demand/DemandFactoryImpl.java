package model.demand;

import java.util.Map;

import model.construction.ConstructionType;
import model.resources.ResourceType;

/**
 * This class is used to implements a factory that allows the Demands creation.
 */
public class DemandFactoryImpl implements DemandFactory {

    /*
     * (non-Javadoc)
     * @see model.demand.DemandFactory#createDemandOfResources(java.util.Map)
     */
    @Override
    public Demand createDemandOfResources(final Map<ResourceType, Integer> resources) {
            if (resources.isEmpty()) {
                throw new IllegalArgumentException("The number of resources MUST BE at least 1!");
            } else {
                return new DemandImpl(resources, null);
            }
    }

    /*
     * (non-Javadoc)
     * @see model.demand.DemandFactory#createDemandOfBuildings(java.util.Map)
     */
    @Override
    public Demand createDemandOfBuildings(final Map<ConstructionType, Integer> buildings) {
            if (buildings.isEmpty()) {
                throw new IllegalArgumentException("The number of buildings MUST BE at least 1!");
            } else {
                return new DemandImpl(null, buildings);
            }
    }

    /*
     * (non-Javadoc)
     * @see model.demand.DemandFactory#createCompleteDemand(java.util.Map, java.util.Map)
     */
    @Override
    public Demand createCompleteDemand(final Map<ResourceType, Integer> resources, final Map<ConstructionType, Integer> buildings) {
            if (resources.isEmpty() || buildings.isEmpty()) {
                throw new IllegalArgumentException("The number of buildings and/or resources MUST BE at least 1!");
            } else {
                return new DemandImpl(resources, buildings);
            }
    }
}
