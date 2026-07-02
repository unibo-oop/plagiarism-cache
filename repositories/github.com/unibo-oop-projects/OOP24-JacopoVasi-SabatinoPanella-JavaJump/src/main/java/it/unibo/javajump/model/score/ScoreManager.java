package it.unibo.javajump.model.score;

/**
 * The interface that describes the Score manager, that handles the score system.
 */
public interface ScoreManager {
    /**
     * Method to add points.
     *
     * @param amount the amount of points to add
     */
    void addPoints(int amount);

    /**
     * Gets current score.
     *
     * @return the current score
     */
    int getCurrentScore();

    /**
     * Gets best score.
     *
     * @return the best score
     */
    int getBestScore();

    /**
     * Flag to check if the best score is reached and overwritten.
     *
     * @return the boolean
     */
    boolean isBestScoreReached();

    /**
     * Method to reset the ScoreManager.
     */
    void reset();
}
