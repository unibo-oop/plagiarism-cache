package snakerunner.model;

import java.util.List;
import java.util.Set;

import snakerunner.commons.Point2D;

/**
 * The GameModel interface defines the core functionalities of the game.
 */
public interface GameModel {

    /**
     * Updates the game state.
     */
    void update();

    /**
     * Checks if the game is over.
     * 
     * @return true if the game is over, false otherwise.
     */
    boolean isGameOver();

    /**
     * Loads a level from the given data.
     *
     * @param data The LevelData object containing the level information to be loaded.
     */
    void loadLevel(LevelData data);

    /**
     * Returns the snake in the game.
     *
     * @return The Snake object representing the player's snake.
     */
    Snake getSnake();

    /**
     * Returns the list of collectibles in the game.
     *
     * @return An unmodifiable list of Collectible objects.
     */
    List<Collectible> getCollectibles();

    /**
     * Returns the current level.
     *
     * @return The current Level object.
     */
    Level getLevel();

    /**
     * Adding obstacles.
     *
     * @return position of obstacle.
     */
    Set<Point2D<Integer, Integer>> getObstacles();

    /**
     * Checks if the current level is completed.
     *
     * @return true if the level is completed, false otherwise.
     */
    boolean isLevelCompleted();

    /**
     * Completes the current level and prepares for the next level.
     * This method is called when the player meets the victory condition for the current level.
     */
    void completeLevel();

    /** 
     * Adds points to the player's score. 
     * 
     * @param points The number of points to add to the score.
     */
    void addScore(int points);

    /**
     * Returns the current score.
     *
     * @return The current score.
     */
    int getScore();

    /**
     * Returns the current lives.
     *
     * @return The current lives.
     */
    int getLives();

    /**
     * Applies the slow effect to the game after the snake consumes a clock.
     */
    void applySlowEffect();

    /**
     * Returns the current speed of the game.
     *
     * @return The current speed of the game.
     */
    int getSpeed();

    /**
     * Open doors.
     */
    void openDoor();

    /**
     * Fatal collision, setting lives to 0.
     */
    void killSnake();

    /**
     * Resets the game state to the initial conditions. This method is called when the game is over to prepare for a new game.
     */
    void resetState();

    /**
     * Add a life to the snake after consuming a mushroom(Life Boost).
     */
    void addLife();
}
