package snakerunner.model;

import java.util.Set;

import snakerunner.commons.Point2D;

/**
 * Interface representing a level in the game.
 */
public interface Level {
    /**
     * Returns the grid associated with this level.
     * 
     * @return the grid of the level.
     */
    Grid getGrid();

    /**
     * Returns the set of obstacles present in the level.
     * 
     * @return a set of points representing the obstacles.
     */
    Set<Point2D<Integer, Integer>> getObstacles();

    /**
     * Checks if the given position is blocked by an obstacle.
     * 
     * @param position to check.
     * 
     * @return true if the position is blocked, false otherwise.
     */
    boolean isBlocked(Point2D<Integer, Integer> position);

    /**
     * Returns the list od doors present in the level.
     * 
     * @return list of doors.
     */
    java.util.List<Door> getDoors();
}
