package controller.player;

import java.awt.Point;
import java.util.Set;

/**
 * PlayerPathImpl interface.
 *
 */
public interface PlayerPath {

    /**
     * Saves player's path.
     * 
     * @param position
     *            of the player
     */
    void savePath(Point position);

    /**
     * Checks if the player is on a tile that has already been touched. Besides, if
     * the player is on the final tile, it calls the checkVictory method.
     * 
     * @param position
     *            of the player
     */
    void checkPath(Point position);

    /**
     * Checks if the player wants to move towards an obstacle.
     * 
     * @param position
     *            point of player's coordinates where he wants to move
     * @return true if there will be a collision, false otherwise
     */
    boolean checkObstacle(Point position);

    /**
     * Checks if the player wants to move towards a map bound.
     * 
     * @param x
     *            player's x coordinate plus how much he moves on x axis
     * @param y
     *            player's y coordinate plus how much he moves on y axis
     * @return true if there will be a collision, false otherwise
     */
    boolean checkBound(int x, int y);

    /**
     * Clears playerPositions List.
     */
    void clearPath();

    /**
     * Getter of player's path.
     * 
     * @return playerPositions
     */
    Set<Point> getPlayerPath();
}
