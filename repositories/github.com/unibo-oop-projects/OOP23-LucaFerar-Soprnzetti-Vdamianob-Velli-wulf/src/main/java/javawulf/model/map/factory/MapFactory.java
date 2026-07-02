package javawulf.model.map.factory;

import javawulf.model.map.Map;
import javawulf.model.player.Player;

/**
 * A Factory used to get default Map, with four biomes full of rooms, corridors and entities.
 */
public interface MapFactory {
    /**
     * @param player
     * @return a default map. Optimized for 20 tile-size biomes
     */
    Map getDefaultMap1(Player player);

    /**
     * @param player
     * @return a test Map. Can be used for debugging or junit tests.
     */
    Map getTestMap(Player player);
}
