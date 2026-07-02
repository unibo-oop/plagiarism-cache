package it.unibo.model.interfaces;

/**
 * Interface representing a model for managing the score in the game.
 * Provides methods to retrieve and update the current score.
 */
public interface ScoreModelInterface {

    /**
     * Gets the current score of the game.
     * 
     * @return The current score as an integer.
     */
    int getScore();

    /**
     * Sets the current score of the game.
     * This allows updating the score to a specific value.
     * 
     * @param score The new score to be set.
     */
    void setScore(int score);
}
