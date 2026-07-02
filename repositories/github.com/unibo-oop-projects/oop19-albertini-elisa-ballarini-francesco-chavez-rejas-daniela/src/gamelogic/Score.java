package gamelogic;

import level.Levels;

/**
 * This interface models the score, calculates and trace it and calls the level up every time is needed.
 *
 */
public interface Score {

    /**
     * This method calculates and adds the points for the elimination of new lines.
     * 
     * @param combo : the number of rows eliminated with the last piece
     */
    void addPoints(int combo);

    /**
     * @return the current level
     */
    Levels getLevel();

    /**
     * @return the current score
     */
    int getScore();

}
