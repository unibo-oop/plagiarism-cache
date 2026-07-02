package vg.controller.leaderboard;

import vg.model.score.Score;
import java.util.List;

public interface ScoreManager {

    /**
     * Initialize ScoreManager loading data form a file if present.
     */
    void init();

    /**
     * Save passed score.
     * @param score score to be saved
     */
    void saveScore(Score score);

    /**
     * Get top 10 score in decreasing order.
     * @return List of top 10 scores.
     */
    List<Score> getTop10Score();

    /**
     * Get top score ordered. If you want all score with no limit pass as paameter {@link ScoreManagerImpl#NO_LIMIT}.
     * @param limit max amount of scores
     * @return List with "limit" amount of scores
     */
    List<Score> getTopScore(int limit);

    /**
     * Save current list of scores on file.
     */
    void saveOnFile();
}
