package controller;

import java.util.List;

import utilities.Pair;

/**
 * public interface for controller.
 */
public interface Controller {
    /**
     * start the game loop.
     */
    void startGame();

    /**
     * stop the game loop.
     */
    void stopGame();

    /**
     * pause game loop.
     */
    void pauseGame();

    /**
     * uses leaderboard manager object.
     * @return list of the scores.
     */
    List<Pair<String, Integer>> getScoreList();
    /**
     * set the difficult.
     * @param diff 
     */
    void setDifficult(GameDifficult diff);

}
