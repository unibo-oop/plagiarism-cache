package clashclass.elements.buildings;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Maps building types to their factory methods.
 *
 * @param <F> the building factory type
 */
public class BuildingFactoryMapper<F extends BuildingFactory> {
    private final Map<VillageElementData, Function<VectorInt2D, GameObject>> buildingIdToFactory;
    private final F factory;

    /**
     * Constructs the building factory mapper.
     *
     * @param factory the factory
     */
    public BuildingFactoryMapper(final F factory) {
        this.factory = Objects.requireNonNull(factory);
        this.buildingIdToFactory = new EnumMap<>(VillageElementData.class);
        initializeMap();
    }

    private void initializeMap() {
        // Map each building type to its corresponding factory method
        buildingIdToFactory.put(VillageElementData.TOWN_HALL, factory::createTownHall);
        buildingIdToFactory.put(VillageElementData.WALL, factory::createWall);
        buildingIdToFactory.put(VillageElementData.CANNON, factory::createCannon);
        buildingIdToFactory.put(VillageElementData.ARCHER_TOWER, factory::createArcherTower);
        buildingIdToFactory.put(VillageElementData.GOLD_STORAGE, factory::createGoldStorage);
        buildingIdToFactory.put(VillageElementData.ELIXIR_STORAGE, factory::createElixirStorage);
        buildingIdToFactory.put(VillageElementData.GOLD_EXTRACTOR, factory::createGoldExtractor);
        buildingIdToFactory.put(VillageElementData.ELIXIR_EXTRACTOR, factory::createElixirExtractor);
        buildingIdToFactory.put(VillageElementData.ARMY_CAMP, factory::createArmyCamp);
        buildingIdToFactory.put(VillageElementData.BARRACKS, factory::createBarracks);
    }

    /**
     * Get factory method for creating a specific building type.
     *
     * @param buildingType The type of building to create
     *
     * @return Function that creates the building at a given position
     */
    public Function<VectorInt2D, GameObject> getFactoryFor(final VillageElementData buildingType) {
        return Optional.ofNullable(buildingIdToFactory.get(buildingType))
                .orElseThrow(() -> new IllegalArgumentException(
                        "No factory for " + buildingType));
        }

    /**
     * Gets the factory.
     *
     * @return the factory
     */
    public F getFactory() {
        return factory;
    }
}
