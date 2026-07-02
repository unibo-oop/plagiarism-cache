package javawulf.model.map;

import java.util.Optional;
import java.util.List;
import java.util.Set;

import javawulf.model.BoundingBox;
import javawulf.model.Coordinate;
import javawulf.model.GameElement;
import javawulf.model.player.Player;

/**
 * Map is the complete bidimensional game Enviroment. It takes four biomes and
 * it uses their to build the enviroment.
 * Enviroment will be:
 * <img src="../../../../resources/javadoc/biome-map-suddivision.png" />
 * 
 * So Map will behave like a builder of the final Enviroment of the game.
 */
public interface Map {

    /**
     * Width-tile dimension of the central biome.
     */
    int WIDTH_CENTRAL_BIOME = 10;

    /**
     * Tile-dimension of the whole map.
     */
    int MAP_SIZE = Biome.SIZE * 2 + WIDTH_CENTRAL_BIOME;

    /**
     * 
     * @param position (absolute)
     * @return tile position (empty if the given position is Out of map)
     */
    Optional<TilePosition> getTilePosition(Coordinate position);

    /**
     * 
     * @param position (absolute)
     * @return tile type relative to the given position (empty if the position is
     *         Out of map)
     */
    Optional<TileType> getTileType(Coordinate position);

    /**
     * 
     * @param boundBoxEntity to calculate
     * @return a Set of tiletypes that the given boundingbox is intersecting (an
     *         empty Set if it is
     *         Out of map)
     *         <img src="../../../../resources/javadoc/intersections.gif" />
     * In this GIF, in a first moment the entity (red square) is completely inside a room, so will be return Set = { ROOM }.
     * In a second time, the entity is between room tiles and wall tiles: will be return Set = { ROOM, WALL }.
     */
    Set<TileType> getTileTypes(BoundingBox boundBoxEntity);

    /**
     * 
     * @return a defensive copy of all tilepositions in the map (where hashmap doesn't contain a position (key value), it means
     * there is a WALL by default).
     */
    java.util.Map<TilePosition, TileType> getTilesMap();

    /**
     * 
     * @return the player held by the map.
     */
    Player getPlayer();

    /**
     * 
     * @return an ordered Arraylist of biomes of the map (0 UPPER-LEFT, 1 UPPER-RIGHT, 2 DOWNER-RIGHT, 3 DOWNER-LEFT).
     * Central biome isn't included.
     */
    List<Biome> getBiomes();

    /**
     * 
     * @param room
     * @return all elements of a specific room.
     */
    List<GameElement> getRoomElements(Space room);

    /**
     * 
     * @return room where the Player is in (Optional.Empty if isn't in any room).
     */
    Optional<Space> getPlayerRoom();

    /**
     * 
     * @return a list of all elements inside map.
     */
    List<GameElement> getAllElements();
}
