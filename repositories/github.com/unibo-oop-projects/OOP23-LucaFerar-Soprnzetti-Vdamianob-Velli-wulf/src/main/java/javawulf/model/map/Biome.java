package javawulf.model.map;

import javafx.util.Pair;
import javawulf.model.GameElement;

import java.util.List;
import java.util.Optional;

/**
 * Biome rapresent a macro-part of a Map (in the case of this game, biome is a
 * quadrant ¼ of the entire gamemap)
 * Each quadrant should have a single hamulet piece. Biomes should connected to
 * each other.
 * It can be consider a bidimensional container of Rooms and Corridors.
 */
public interface Biome {
    /**
     * Size (in tile) of biomes. All biomes have same sizes and they are square
     * (Size repesents
     * both Height and Width).
     */
    int SIZE = 20;

    /**
     * 
     * @param biomePos is the up-left angle position of the room and represents
     *                 where will be
     *                 placed inside the biome (tile x, y)
     * @param room     to add to the Biome
     * @return Biome itself
     */
    Biome addRoom(TilePosition biomePos, Space room);

    /**
     * 
     * @param biomePos is the up-left angle position of the corridor and represents
     *                 where will be
     *                 placed inside the biome (tile x, y)
     * @param corridor to add to the Biome
     * @return Biome itself
     */
    Biome addCorridor(TilePosition biomePos, Space corridor);

    /**
     * 
     * @return a defensive copy of a list of room positions and their relative rooms
     *         Pair [ (tile x, y) - ROOM ].
     */
    List<Pair<TilePosition, Space>> getRooms();

    /**
     * 
     * @return a defensive copy of a list of corridor positions and their relative
     *         Pair corridors [ (tile x, y) - CORRIDOR ].
     */
    List<Pair<TilePosition, Space>> getCorridors();

/**
 * 
 * @param tilePos of the Biome
 * @return Optional of the room corrisponding (Empty if there isn't any room)
 */
    Optional<Space> getRoom(TilePosition tilePos);

    /**
     * 
     * @return all GameElements inside biome.
     */
    List<GameElement> getElements();

}
