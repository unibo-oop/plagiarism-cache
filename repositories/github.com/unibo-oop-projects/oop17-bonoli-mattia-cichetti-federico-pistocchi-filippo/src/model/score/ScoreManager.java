package model.score;

import controller.GameDifficult;

/**
 * This interface manages score.
 * Score increase when frog moves up or reaches den.
 * Score decrease when frog dies.
 */
public interface ScoreManager {

    /**
     * @param difficult of the game.
     */
    void setDifficult(GameDifficult difficult);

    /**
     * @return the actual score.
     */
    long getScore();

    /**
     * @param score to add.
     */
    void addScore(long score);

    /**
     * 
     * @param lane where frog is.
     */
    void frogMovedSuccesfully(int lane);

    /**
     * 
     */
    void frogDies();

}
