package model.score;

import java.util.Map;

/**
 * This interface manage the score of a minigame.
 *
 */
public interface ScoreManagement {

    /**
     * Get the minigame history map.
     * 
     * @return 
     *          map with key a date and value the average of the minigame score. 
     */
    Map<String, Integer> getMiniGameHistory();

    /**
     * Set the minigame history map.
     * 
     * @param miniGameHistory
     *      map with key a date and value the average of the minigame score. 
     */
    void setMiniGameHistory(Map<String, Integer> miniGameHistory);

    /**
     * Get the average score of minigame.
     * 
     * @return
     *          average score.
     */
    int getAverage();

    /**
     * Set the average score of minigame.
     * 
     * @param average
     *          average score.
     */
    void setAverage(int average);

    /**
     * Get the best score gained.
     * 
     * @return
     *          best score.
     */
    int getBest();

    /**
     * Set the best score gained.
     * 
     * @param best
     *          best score.
     */
    void setBest(int best);

    /**
     * Add a new score of a minigame. 
     * 
     * @param score
     *          the new score totalized.
     */
    void updateScore(int score);
}
