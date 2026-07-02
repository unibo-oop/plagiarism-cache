package clashclass.elements.buildings;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;

/**
 * Represents a Factory for buildings creation.
 */
public interface BuildingFactory {
    /**
     * Creates a town hall, the main building of the village.
     *
     * @param position world position of the town hall
     *
     * @return the GameObject that represents the town hall
     */
    GameObject createTownHall(VectorInt2D position);

    /**
     * Creates a wall.
     *
     * @param position world position of the wall
     *
     * @return the GameObject that represents the wall
     */
    GameObject createWall(VectorInt2D position);

    /**
     * Creates a cannon.
     *
     * @param position world position of the cannon
     *
     * @return the GameObject that represents the cannon
     */
    GameObject createCannon(VectorInt2D position);

    /**
     * Creates an archer tower.
     *
     * @param position world position of the archer tower
     *
     * @return the GameObject that represents the archer tower
     */
    GameObject createArcherTower(VectorInt2D position);

    /**
     * Creates a gold storage.
     *
     * @param position world position of the gold storage
     *
     * @return the GameObject that represents the gold storage
     */
    GameObject createGoldStorage(VectorInt2D position);

    /**
     * Creates an elixir storage.
     *
     * @param position world position of the elixir storage
     *
     * @return the GameObject that represents the elixir storage
     */
    GameObject createElixirStorage(VectorInt2D position);

    /**
     * Creates a gold extractor.
     *
     * @param position world position of the gold extractor
     *
     * @return the GameObject that represents the gold extractor
     */
    GameObject createGoldExtractor(VectorInt2D position);

    /**
     * Creates an elixir extractor.
     *
     * @param position world position of the elixir extractor
     *
     * @return the GameObject that represents the elixir extractor
     */
    GameObject createElixirExtractor(VectorInt2D position);

    /**
     * Creates an army camp.
     *
     * @param position world position of the army camp
     *
     * @return the GameObject that represents the army camp
     */
    GameObject createArmyCamp(VectorInt2D position);

    /**
     * Creates the barracks.
     *
     * @param position world position of the barracks
     *
     * @return the GameObject that represents the barracks
     */
    GameObject createBarracks(VectorInt2D position);
}
