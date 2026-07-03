package controller;

import java.io.IOException;
import java.util.List;

import utilities.Pair;

public interface HighScoreManager {
    /**
     * Adds a new Pair "player-score" to the high scores. removes the score in
     * excess
     *
     * @param p
     *            The new high score to add
     */
    void addScore(final Pair<String, Integer> p);

    /**
     * Returns the list of high scores, even if it's empty
     *
     * @return The list of high scores
     */
    List<Pair<String, Integer>> getScores();

    /**
     * Saves scores.
     * 
     * @throws IOException
     *             If unable to save the scores
     */
    void writeScores() throws IOException;

}
