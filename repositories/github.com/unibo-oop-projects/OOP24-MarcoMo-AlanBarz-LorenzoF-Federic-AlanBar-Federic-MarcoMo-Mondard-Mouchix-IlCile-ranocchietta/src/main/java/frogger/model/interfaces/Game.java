package frogger.model.interfaces;

import java.util.List;

/**
 * Represents the main game logic and state.
 * Provides access to the player, obstacles, level, power-ups, and game status.
 */
public interface Game {

    /**
     * Checks if the game is over.
     * If the player has no lives left, the game state is set to DEAD.
     */
    void checkGameOver();

    /**
     * Returns the current score of the player.
     *
     * @return the player's score
     */
    int getScore();

    /**
     * Returns the player object.
     *
     * @return the player
     */
    PlayerObject getPlayer();

    /**
     * Returns all the obstacles in the current level.
     *
     * @return the list of obstacles
     */
    List<MovingObject> getObstacles();

    /**
     * Returns the current level.
     *
     * @return the level
     */
    Level getLevel();

    /**
     * Checks for collisions between the player and obstacles or other objects.
     */
    void checkCollision();

    /**
     * Checks if the player has reached a new level and updates the game state accordingly.
     */
    void checkNewLevel();

    /**
     * Returns the lane where the frog is at the moment.
     *
     * @return the current lane
     */
    Lane getCurrentLane();

    /**
     * Checks if the current lane is already completed, and if not, adds points to the frog.
     */
    void checkProgress();

    /**
     * Checks if the game is currently paused.
     *
     * @return true if the game is paused, false otherwise
     */
    boolean gameIsPaused();

    /**
     * Returns the list of power-ups currently available in the game.
     *
     * @return the list of power-ups
     */
    List<PickableObject> getPickableObjects();

    /**
     * @return return the PickableObjectManager
     */
    PickableObjectManager getPickableObjectManager();
}
