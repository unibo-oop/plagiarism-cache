package controller;

import java.util.List;

import controller.utility.Score;

/**
 * Interface that manages all game situations.
 */
public interface GameEngine {

    /**
     * Initialize the view for the kts application.
     */
    void initView();

    /**
     * Create a new game.
     * @param name of the player.
     */
    void newGame(String name);

    /**
     * Stop the game.
     */
    void stopGame();

    /**
     * Resume the execution of the game.
     */
    void resumeGameLoop();

    /**
     * Method called when player dead.
     * @param points points obtained.
     */
    void gameOver(int points);

    /**
     * Method called when player win the game.
     * @param points points obtained.
     */
    void victory(int points);

    /**
     * Get the game loop.
     * @return the gameLoop object.
     */
    GameLoop getGameLoop();

    /**
     * Get the leaderboard.
     * @return the leaderboard.
     */
    List<Score> getLeaderboard();

    /**
     * Set the leaderboard.
     * @param list of new leaderboard.
     */
    void setLeaderboard(List<Score> list);
}
