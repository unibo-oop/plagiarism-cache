package com.jlearn.model.statistics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.model.checker.CheckLog;
import com.jlearn.model.checker.Checker;
import com.jlearn.model.checker.Checker.Result;
import com.jlearn.model.utilities.Levels;

/**
 *
 * The statistics class. Statistics are referred to a single unit
 */
public class StatisticsImpl implements Statistics {

    private static final long                 serialVersionUID = 6267518254046669453L;
    private final int                         unitID;
    private final Map<Levels, List<CheckLog>> lastLogs;
    private final Map<Levels, Integer>        maxScores;
    private static final Logger               LOG              = Logger.getLogger(StatisticsImpl.class);

    /**
     *
     * The statistics constructor.
     *
     * @param unitID
     *            The ID of the unit the statistics are referred to.
     * @param maxScores
     *            a map which keys are are the levels, and the values are the top reached score of each level. If it's
     *            the first time the player plays, this will be an empty map.
     * @param lastLogs
     *            map containing the last check log list, for each level. The value is a list of check logs because
     *            every single check log is referred to a single exercise.
     */
    public StatisticsImpl(final int unitID, final Map<Levels, Integer> maxScores,
            final Map<Levels, List<CheckLog>> lastLogs) {
        LOG.setLevel(Level.WARN);
        this.unitID = unitID;
        this.maxScores = maxScores;
        this.lastLogs = lastLogs;
        LOG.info("Initialized Stats");
    }

    @Override
    public void updateScores(final Levels level, final int score) {
        if (this.maxScores.get(level) < score) {
            LOG.info("This now is the max score registered for this level");
            this.maxScores.put(level, score);
        } else {
            LOG.info("This score didn't affect max scores");
        }

        LOG.info("Score updated successfully");
    }

    @Override
    public void updateCheckLogs(final Levels level, final List<CheckLog> logs) {
        this.lastLogs.put(level, logs);
        LOG.info("CheckLogs updated successfully");
    }

    @Override
    public int getMaxScoreByLevel(final Levels level) {
        return this.maxScores.get(level);
    }

    @Override
    public Map<Checker.Result, Integer> getLastRatesByLevel(final Levels level) {
        final Map<Result, Integer> rates = new HashMap<>();
        final List<CheckLog> logs = this.lastLogs.get(level);

        final List<Checker.Result> results = Arrays.asList(Checker.Result.values());

        results.stream()
                .forEach(x -> rates.put(x, logs.stream()
                        .mapToInt(y -> y.getAnswersNumberByResult(x))
                        .sum()));
        return rates;
    }

    @Override
    public int getUnitID() {

        return this.unitID;
    }

}
