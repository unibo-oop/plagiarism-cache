package javawulf.model.map;

import java.util.HashMap;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.util.Pair;

/**
 * An Utility class that creates a HashMap<TilePosition, TileType> from four
 * biomes (and generating central biome, too).
 * The HashMap associates for each position a Tile-type, thus composing the game
 * map.
 * This class will be exclusively used by
 * MapImpl for the sole purpose of making, internally, the map.
 */
public final class MapTilesBuilder {

    private MapTilesBuilder() {
        throw new UnsupportedOperationException("This class cannot be instantiated (Utility class)");
    }

    /**
     * 
     * @param biomes is a list that have four biomes of tiles map to be build.
     * @return a built map tiles.
     */
    public static java.util.Map<TilePosition, TileType> buildTiles(final List<Biome> biomes) {
        return build(biomes);
    }

    private static java.util.Map<TilePosition, TileType> build(final List<Biome> biomes) {
        final HashMap<TilePosition, TileType> tiles = new HashMap<>();
        for (final var biomeOffSet : BiomeQuadrant.values()) {
            buildSpacesBiome(tiles, biomeOffSet, biomes.get(biomeOffSet.getPos()).getRooms(), Room.DEFAULT_TYPE);
            buildSpacesBiome(tiles, biomeOffSet, biomes.get(biomeOffSet.getPos()).getCorridors(),
                    Corridor.DEFAULT_TYPE);
        }
        buildCentralBiome(tiles);
        buildFinisherTiles(tiles);
        return tiles;
    }

    /**
     * Used to build spaces in a specific biome.
     * 
     * @param tiles            HashMap where build (w*h) spaces
     * @param biomeQuadrant    used for take the specific position quadrant offset
     * @param spaces           a list of Pair (SpacePosition, Space) to build in the
     *                         HashMap
     * @param defaultSpaceTile to use for build spaces
     */
    private static void buildSpacesBiome(final java.util.Map<TilePosition, TileType> tiles,
            final BiomeQuadrant biomeQuadrant, final List<Pair<TilePosition, Space>> spaces,
            final TileType defaultSpaceTile) {
        for (final var space : spaces) {
            buildSpace(tiles,
                    new TilePosition(space.getKey().getX() + biomeQuadrant.getOffset().getX(),
                            space.getKey().getY() + biomeQuadrant.getOffset().getY()),
                    space.getValue(), defaultSpaceTile);
        }
    }

    /**
     * Used to build a specific space inside the whole map.
     * 
     * @param tiles
     * @param posToStart       where build Space inside map
     * @param space
     * @param defaultSpaceTile to use for build space
     */
    private static void buildSpace(final java.util.Map<TilePosition, TileType> tiles,
            final TilePosition posToStart, final Space space,
            final TileType defaultSpaceTile) {
        for (int y = posToStart.getY(); y < posToStart.getY() + space.getHeight(); y++) {
            for (int x = posToStart.getX(); x < posToStart.getX() + space.getWidth(); x++) {
                tiles.put(new TilePosition(x, y), defaultSpaceTile);
            }
        }
    }

    private static void buildCentralBiome(final java.util.Map<TilePosition, TileType> tiles) {
        // CHECKSTYLE: MagicNumber OFF
        // In the central biome, the position of the spaces was choosen arbitrarily: the
        // use of final variables to identify the
        // tilePositions of each space would have been less comprehensive.
        // For this reason it was decided to suppress the warning checkstyles.
        buildSpace(tiles, new TilePosition(Biome.SIZE, 3), new Corridor(Map.WIDTH_CENTRAL_BIOME, 2), TileType.CORRIDOR);
        buildSpace(tiles, new TilePosition(Biome.SIZE, 15), new Corridor(Map.WIDTH_CENTRAL_BIOME, 2),
                TileType.CORRIDOR);
        buildSpace(tiles, new TilePosition(Biome.SIZE, Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 3),
                new Corridor(Map.WIDTH_CENTRAL_BIOME, 2), TileType.CORRIDOR);
        buildSpace(tiles, new TilePosition(Biome.SIZE, Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 15),
                new Corridor(Map.WIDTH_CENTRAL_BIOME, 2), TileType.CORRIDOR);

        buildSpace(tiles, new TilePosition(3, Biome.SIZE), new Corridor(2, Map.WIDTH_CENTRAL_BIOME), TileType.CORRIDOR);
        buildSpace(tiles, new TilePosition(15, Biome.SIZE), new Corridor(2, Map.WIDTH_CENTRAL_BIOME),
                TileType.CORRIDOR);
        buildSpace(tiles, new TilePosition(Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 3, Biome.SIZE),
                new Corridor(2, Map.WIDTH_CENTRAL_BIOME), TileType.CORRIDOR);
        buildSpace(tiles, new TilePosition(Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 15, Biome.SIZE),
                new Corridor(2, Map.WIDTH_CENTRAL_BIOME), TileType.CORRIDOR);

        buildSpace(tiles, new TilePosition(Biome.SIZE + 2, Biome.SIZE + 2),
                new Room(Map.WIDTH_CENTRAL_BIOME - 2 * 2, Map.WIDTH_CENTRAL_BIOME - 2 * 2), TileType.CENTRAL_ROOM);
        buildSpace(tiles, new TilePosition(Biome.SIZE + Map.WIDTH_CENTRAL_BIOME / 2 - 1, Biome.SIZE - 3),
                new Room(2, 5), TileType.CENTRAL_ROOM);
        buildSpace(tiles, new TilePosition(Biome.SIZE + Map.WIDTH_CENTRAL_BIOME / 2 - 1, Biome.SIZE + 8),
                new Room(2, 5), TileType.CENTRAL_ROOM);
        buildSpace(tiles, new TilePosition(Biome.SIZE - 3, Biome.SIZE + Map.WIDTH_CENTRAL_BIOME / 2 - 1),
                new Room(5, 2), TileType.CENTRAL_ROOM);
        buildSpace(tiles, new TilePosition(Biome.SIZE + 8, Biome.SIZE + Map.WIDTH_CENTRAL_BIOME / 2 - 1),
                new Room(5, 2), TileType.CENTRAL_ROOM);
        // CHECKSTYLE: MagicNumber ON
    }

    /**
     * For 'finisher tiles' it means these tiles which player can end game, getting
     * on it.
     * This method build their in the center of the map, inside Central room.
     * If Size of map is odd, finisher tile is only one, else was 2x2 tile square.
     * 
     * @param tiles HashMap-tiles where build finisher tiles.
     */
    @SuppressWarnings("unused")
    @SuppressFBWarnings(
        value = {
            "H", "D", "DLS"
        },
        justification = "'biggerFinisher' and 'unaryFinisher' can be change if Biome.SIZE is changed."
        )
    private static void buildFinisherTiles(final java.util.Map<TilePosition, TileType> tiles) {
        final int offset = 1;
        final Room biggerFinisher = new Room(2, 2);
        final Room unaryFinisher = new Room(1, 1);
        if (Map.MAP_SIZE % 2 == 0) {
            buildSpace(tiles, new TilePosition(Map.MAP_SIZE / 2 - offset, Map.MAP_SIZE / 2 - offset), biggerFinisher,
                    TileType.PORTAL);
        } else {
            buildSpace(tiles, new TilePosition(Map.MAP_SIZE / 2, Map.MAP_SIZE / 2), unaryFinisher, TileType.PORTAL);
        }
    }
}
