package it.unibo.model.score.api;

import it.unibo.model.persistence.api.SaveState;

/**
 * Interface for managing the game score.
 */
public interface ScoreManager {

    /**
     * Update the score based on the current Y position and the high score.
     * 
     * @param playerY the current player Y position
     */
    void updateScore(double playerY);

    /**
     * Add coins to the score.
     * 
     * @param coins the number of coins to add
     */
    void addCoins(int coins);

    /**
     * Get the current score.
     * 
     * @return the current score
     */
    int getCurrentScore();

    /**
     * Get the number of collected coins.
     * 
     * @return the number of coins
     */
    int getCoins();

    /**
     * Get the highest score achieved.
     * 
     * @return the high score
     */
    int getHighScore();

    /**
     * Set the data from the file.
     * 
     * @param state data from the file.
     */
    void loadState(SaveState state);

    /**
     * Sets the initial vertical position of the player for the current session.
     * 
     * @param y the starting Y coordinate.
     */
    void setStartY(double y);

    /**
     * Resets the current score, coins, total camera delta, and starting Y position
     * to their initial values. Also resets the new high score flag.
     */
    void resetScore();

    /**
     * Returns whether the current score is a new high score.
     * 
     * @return true if the current score is a new high score, false otherwise
     */
    boolean isNewHighScore();
}
