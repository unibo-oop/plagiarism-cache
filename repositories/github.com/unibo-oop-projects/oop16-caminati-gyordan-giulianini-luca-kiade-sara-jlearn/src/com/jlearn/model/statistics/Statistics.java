package com.jlearn.model.statistics;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jlearn.model.checker.CheckLog;
import com.jlearn.model.checker.Checker;
import com.jlearn.model.utilities.Levels;

/**
 * The statistics interface.
 */
public interface Statistics extends Serializable {

    /**
     * A method to register a new score.
     *
     * @param level
     *            the played level
     *
     * @param score
     *            the score to register
     */
    void updateScores(Levels level, int score);

    /**
     * A method to register a new list of check logs.
     *
     * @param level
     *            the played level
     *
     * @param logs
     *            the logs to register
     */
    void updateCheckLogs(Levels level, List<CheckLog> logs);

    /**
     *
     * @param level
     *            the played level
     * @return a map storing the results, and their related number of answers
     */
    Map<Checker.Result, Integer> getLastRatesByLevel(Levels level);

    /**
     *
     * @param level
     *            the played level
     *
     * @return the top score reached in the given level.
     */
    int getMaxScoreByLevel(Levels level);

    /**
     * @return the unit ID.
     */
    int getUnitID();
}
