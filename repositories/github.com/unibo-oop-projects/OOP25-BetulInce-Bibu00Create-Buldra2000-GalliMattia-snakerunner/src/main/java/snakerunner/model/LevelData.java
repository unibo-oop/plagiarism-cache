package snakerunner.model;

import java.util.List;
import java.util.Set;

import snakerunner.commons.Point2D;

/**
 * Interface representing the data of a level in the Snake Runner game.
 */
public interface LevelData {
    /**
     * Returns the set of obstacles present in the level.
     *
     * @return a set of points representing the obstacles.
     */
    Set<Point2D<Integer, Integer>> getObstacles();

    /**
     * Returns the list of collectibles present in the level.
     *
     * @return a list of collectibles.
     */
    List<Collectible> getCollectibles();

    /**
     * Returns the list of doors present in the level.
     * 
     * @return a list of doors.
     */
    List<Door> getDoors();

    /**
     * Returns the victory condition for the level, which determines how the player can complete the level.
     * 
     * @return the victory condition for the level.
     */
    VictoryCondition getVictoryCondition();

}
