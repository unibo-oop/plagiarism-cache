package model.score;

import java.util.List;

import model.obstacle.Obstacle;

/**
 * This interface manages the in game score system.
 *
 */
public interface Score {

    /**
     * Method to update the status of the score.
     * 
     * @param obstacles
     *                      a list of obstacles hit.
     */
    void updateScore(List<Obstacle> obstacles);

    /**
     * @return the current score.
     */
    int getCurrentScore();

    /**
     * @return the current multiplier.
     */
    int getMultiplier();

    /**
     * Saves the player name and final score in an external file.
     * 
     * @param playerName
     *                       the name of the player.
     */
    void saveScore(String playerName);
}
