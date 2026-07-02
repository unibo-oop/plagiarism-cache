package controller.score;

import java.io.IOException;
import java.util.List;

/**
 * Manager of list of Scores.
 */
public interface ScoreManager {
    /**
     * Add the score once in the list.
     * @param score to be added
     * @throws IOException if file is missing
     */
    void writeScore(Score score) throws IOException;
    /**
     * Get the sorted list of scores.
     * @return the list 
     * @throws IOException if file is missing
     */
    List<Score> getScoreboard() throws IOException;

    /**
     * Erase the scoreboard file.
     * @throws IOException if file's missing
     */
    void eraseScoreBoard() throws IOException;

}
