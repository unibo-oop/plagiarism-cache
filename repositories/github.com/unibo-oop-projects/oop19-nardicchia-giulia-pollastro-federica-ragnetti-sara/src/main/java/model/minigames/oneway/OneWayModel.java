package model.minigames.oneway;

import java.util.List;
import model.DifficultyLevel;
import model.minigames.MiniGameModel;
import utility.Pair;

/**
 * The model interface of One Way minigame.
 * 
 */
public interface OneWayModel extends MiniGameModel {

    /**
     * Check if the player guess the answer.
     * @param row
     *          x coordinate 
     * @param col
     *          y coordinate 
     * @return true if player hit the final position, false otherwise.
     */
    boolean hitFinalPosition(int row, int col);

    /**
     * 
     * @return the selected {@link DifficultyLevel}
     */
    DifficultyLevel getDifficulty();

    /**
     * 
     * @return the list of generated {@link Direction} 
     */
    List<Direction> getSteps();

    /**
     * 
     * @return the initial position
     */
    Pair<Integer, Integer> getInitialPosition();

    /**
     * 
     * @return the final position
     */
    Pair<Integer, Integer> getFinalPosition();

    /**
     * Initialize One Way.
     * 
     */
    void oneWayInit();

    /**
     * 
     * @return the arrows count
     */
    int getArrowsCount();

    /**
     * 
     * @return the seconds of the game
     */
    int getSeconds();

    /**
     * 
     * @return the number of rows and columns 
     */
    int getGridSize();

}
