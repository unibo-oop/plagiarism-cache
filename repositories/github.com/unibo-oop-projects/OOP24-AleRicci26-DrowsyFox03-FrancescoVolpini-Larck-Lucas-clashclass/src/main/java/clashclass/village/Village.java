package clashclass.village;

import clashclass.commons.GameConstants;
import clashclass.commons.GridTileData2D;
import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.commons.CommonGameObjectFactoryImpl;
import clashclass.elements.commons.CommonGameObjectsFactory;
import clashclass.resources.Player;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a village, which holds the player data and the list of game objects in the village.
 */
public class Village {
    private final Player player;
    private final Set<GameObject> buildings;
    private final GameObject[][] groundGrid;
    private final GameObject[][] objectGrid;
    private final CommonGameObjectsFactory commonGameObjectFactory;

    /**
     * Constructs the village.
     *
     * @param player the player that owns the village
     */
        public Village(final Player player) {
        this.player = player;
        this.groundGrid = new GameObject[GameConstants.VILLAGE_SIZE][GameConstants.VILLAGE_SIZE];
        this.objectGrid = new GameObject[GameConstants.VILLAGE_SIZE][GameConstants.VILLAGE_SIZE];
        this.commonGameObjectFactory = new CommonGameObjectFactoryImpl();
        this.buildings = new HashSet<>();

        this.createGroundTiles();
    }

    private void createGroundTiles() {
        IntStream.range(0, GameConstants.VILLAGE_SIZE).forEach(i ->
                IntStream.range(0, GameConstants.VILLAGE_SIZE).forEach(j -> {
                    this.groundGrid[i][j] = this.commonGameObjectFactory
                            .createVillageGroundTile(new VectorInt2D(i, j));
                }));
    }

    /**
     * Places a building in a specific point of the village grid.
     *
     * @param building the building to place
     * @param position the position
     * @param width the building rowSpan
     * @param height the building colSpan
     */
    public void placeBuilding(
            final GameObject building,
            final VectorInt2D position,
            final int width,
            final int height) {
        this.buildings.add(building);
        IntStream.range(0, width).forEach(i ->
                IntStream.range(0, height).forEach(j -> {
                    this.objectGrid[position.x() - i][position.y() - j] = building;
                }));
    }

    /**
     * Places a building in a specific point of the village grid.
     *
     * @param building the building to place
     */
    public void placeBuilding(final GameObject building) {
        final var tileData = building.getComponentOfType(GridTileData2D.class).get();
        this.placeBuilding(building, tileData.getPosition(), tileData.getRowSpan(), tileData.getColSpan());
    }

    /**
     * Removes a building from village grid.
     *
     * @param building the building to remove
     */
    public void removeBuilding(final GameObject building) {
        final var tileData = building.getComponentOfType(GridTileData2D.class).get();
        final var position = tileData.getPosition();

        this.buildings.remove(building);
        IntStream.range(0, tileData.getRowSpan()).forEach(i ->
                IntStream.range(0, tileData.getColSpan()).forEach(j -> {
                    this.objectGrid[position.x() - i][position.y() - j] = null;
                }));
    }

    /**
     * Checks if a cell already contains a building.
     *
     * @param position the position of the cell in the grid
     *
     * @return true if cell is busy
     */
    public boolean isCellBusy(final VectorInt2D position) {
        return this.objectGrid[position.x()][position.y()] != null;
    }

    /**
     * Checks if a position is outside of the grid bounds.
     *
     * @param position the position
     *
     * @return true if the position is outside
     */
    public boolean isCellOutsideOfGrid(final VectorInt2D position) {
        return position.x() < 0 || position.y() < 0
                || position.x() >= GameConstants.VILLAGE_SIZE
                || position.y() >= GameConstants.VILLAGE_SIZE;
    }

    /**
     * Gets a building at a specific position of the grid.
     *
     * @param position the position
     *
     * @return the building
     */
    public GameObject getBuildingAtPosition(final VectorInt2D position) {
        return this.objectGrid[position.x()][position.y()];
    }

    /**
     * Gets the player of this village.
     *
     * @return the player
     */
        public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets the buildings of this village.
     *
     * @return the set of buildings
     */
    public Set<GameObject> getBuildings() {
        return this.buildings.stream().collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Gets the ground tiles of this village.
     *
     * @return the set of ground tiles
     */
    public Set<GameObject> getGroundObjects() {
        return Arrays.stream(this.groundGrid)
                .flatMap(Arrays::stream)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Gets the set of buildings.
     *
     * @return the set of buildings
     */
        public Set<GameObject> getGameObjects() {
        return this.buildings;
    }
}
