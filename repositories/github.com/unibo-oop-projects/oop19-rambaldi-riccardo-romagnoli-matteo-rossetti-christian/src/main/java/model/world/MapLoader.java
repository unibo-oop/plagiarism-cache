package model.world;

import java.util.List;

import model.obstacle.Obstacle;

/**
 * MapLoader allows to load a file from a map and puts all the obstacles into a list.
 */
public interface MapLoader {

    /**
     * Loads all the obstacles from a file.
     * @param mapName
     *      The name of the map that needs to be loaded.
     * @return
     *      A list containing all the obstacles.
     */
    List<Obstacle> loadMap(String mapName);
}
