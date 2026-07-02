package model.buildingscounter;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import model.construction.ConstructionType;

/**
 * This class implements a counter that maintain the number of every type of building.
 */
public class BuildingsCounterImpl implements BuildingsCounter {

    private final Map<ConstructionType, Integer> buildingsCounterMap;

    /**
     * Initialize the Map with every type of buildings and set every counter to 0.
     */
    public BuildingsCounterImpl() {
        buildingsCounterMap = new TreeMap<>();
        Arrays.asList(ConstructionType.values()).forEach(n -> {
            buildingsCounterMap.put(n, Integer.valueOf(0));
        });
    }

    /*
     * (non-Javadoc)
     * @see model.buildingscounter.BuildingsCounter#addBuilding(model.buildings.BuildingName)
     */
    @Override
    public void addBuilding(final ConstructionType name) {
        final int oldValue = buildingsCounterMap.get(name);
        buildingsCounterMap.put(name, oldValue + 1);
    }

    /*
     * (non-Javadoc)
     * @see model.buildingscounter.BuildingsCounter#removeBuilding(model.buildings.BuildingName)
     */
    @Override
    public void removeBuilding(final ConstructionType name) {
        final int oldValue = buildingsCounterMap.get(name);
        if (oldValue >= 1) {
            buildingsCounterMap.put(name, oldValue - 1);
        }
    }

    /*
     * (non-Javadoc)
     * @see model.buildingscounter.BuildingsCounter#getNumberOf(model.buildings.BuildingName)
     */
    @Override
    public int getNumberOf(final ConstructionType name) {
        return buildingsCounterMap.get(name);
    }

}
