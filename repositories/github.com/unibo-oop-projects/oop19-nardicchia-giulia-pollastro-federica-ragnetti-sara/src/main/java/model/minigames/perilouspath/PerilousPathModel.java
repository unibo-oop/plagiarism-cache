package model.minigames.perilouspath;

import java.util.Set;

import model.minigames.MiniGameModel;
import utility.Pair;

/**
 * Represents the game's model.
 */
public interface PerilousPathModel extends MiniGameModel {

    /**
     * Check if the user can hit this position.
     * 
     * @param row
     *          the step's position row
     * @param col
     *          the step's position col
     *
     * @return true if user can hit this coordinate, false otherwise
     */
    boolean hit(int row, int col);

    /**
     * Gets the size of this current level.
     * 
     * @return the size
     */
    int getSize();

    /**
     * Gets the start position.
     * 
     * @return the initial step
     */
    Pair<Integer, Integer> getStart();

    /**
     * Gets the finish position.
     * 
     * @return the end step
     */
    Pair<Integer, Integer> getFinish();

    /**
     * Gets the mines.
     * 
     * @return the mines
     */
    Set<Mine> getMines();

    /**
     * Check if the user finds a path correctly.
     * 
     * @return true if the user hasn't made a mistake, false otherwise
     */
    boolean isDone();

    /**
     * Check if the user fails.
     * 
     * @return true if the user has made a mistake, false otherwise
     */
    boolean isFailed();

    /**
     * Reset the game at the initial state.
     */
    void resetGame();
}
