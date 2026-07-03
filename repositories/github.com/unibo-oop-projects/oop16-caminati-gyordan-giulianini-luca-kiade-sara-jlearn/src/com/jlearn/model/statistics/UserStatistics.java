package com.jlearn.model.statistics;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jlearn.model.checker.CheckLog;
import com.jlearn.model.checker.Checker;
import com.jlearn.model.utilities.Levels;

/**
 * Contains all the statistics related to a user.
 */
public interface UserStatistics extends Serializable {

    /**
     * @return the user this statistics are referred to
     */
    String getUser();

    /**
     * @param unitID
     *            the unit you want to know the statistics of
     * @param level
     *            the level you want to filter by
     * @return the number of answers matching each result
     */
    Map<Checker.Result, Integer> getUnitRates(int unitID, Levels level);

    /**
     * @param level
     *            the level you want to filter by
     * @return units IDs and the related top score
     */
    Map<Integer, Integer> getUnitsTopScore(Levels level);

    /**
     * @param unitID
     *            the unit you want to know the statistics of
     * @return the max reached score for each level, in the given unit
     */
    Map<Levels, Integer> getLevelsTopScore(int unitID);

    /**
     * @param unitID
     *            the unit you want to register the score of
     * @param level
     *            the played level
     * @param score
     *            the new score
     */
    void registerNewScore(int unitID, Levels level, int score);

    /**
     * @param unitID
     *            the unit you want to register the score of
     * @param level
     *            the played level
     * @param logs
     *            the new logs
     */
    void registerNewUnitCheckLogs(int unitID, Levels level, List<CheckLog> logs);

    /**
     * @param stat
     *            the statistics you want to add
     */
    void addStatistic(Statistics stat);

}
