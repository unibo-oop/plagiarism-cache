package controller;

import java.util.List;

import utilities.Pair;

/**
 * LeaderboardManager interface.
 */
public interface LeaderboardManager {
    /**
     * retrieve the score list.
     * @return score list unmodifiable.
     */
    List<Pair<String, Integer>> getScoreList();

    /**
     * 
     * @param score to add.
     */
    void addScore(Pair<String, Integer> score);

    /**
     * empyty the score record.
     */
    void resetAllScore();

    /**
     * write to file.
     */
    void updateFile();
}
