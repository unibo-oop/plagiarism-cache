package com.jlearn.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.jlearn.model.checker.CheckLog;
import com.jlearn.model.checker.CheckLogImpl;
import com.jlearn.model.checker.Checker;
import com.jlearn.model.statistics.Statistics;
import com.jlearn.model.statistics.StatisticsImpl;
import com.jlearn.model.utilities.Levels;

/**
 * Statistics methods tester.
 */
public class TestStats {
    /**
     *
     * test.
     *
     *
     */
    @Test
    public void basicTest() {

        /* Creating results in order to create a log */
        final Map<Integer, Checker.Result> results = new TreeMap<>();
        results.put(0, Checker.Result.RIGHT_ANSWER);
        results.put(1, Checker.Result.WRONG_ANSWER);
        results.put(2, Checker.Result.RIGHT_ANSWER);
        results.put(3, Checker.Result.NULL_ANSWER);

        /* creating a log, in order to create last logs map */
        final CheckLog log = new CheckLogImpl(results);

        /* Creating results in order to create a second log */
        results.clear();
        results.put(0, Checker.Result.NULL_ANSWER);
        results.put(1, Checker.Result.RIGHT_ANSWER);
        results.put(2, Checker.Result.RIGHT_ANSWER);
        results.put(3, Checker.Result.WRONG_ANSWER);

        /* creating a second log, in order to create last logs map */
        final CheckLog log2 = new CheckLogImpl(results);

        /* creating last logs map in order to create statistics */
        final Map<Levels, List<CheckLog>> lastLogs = new TreeMap<>();
        lastLogs.put(Levels.EASY, Arrays.asList(log, log2));
        lastLogs.put(Levels.MEDIUM, Arrays.asList(log2, log));

        /* creating max scores map in order to create statistics */
        final Map<Levels, Integer> maxScores = new TreeMap<>();
        maxScores.put(Levels.EASY, 10);
        maxScores.put(Levels.MEDIUM, 3);
        maxScores.put(Levels.HARD, 2);

        /* We now can create statistics */
        final Statistics stats = new StatisticsImpl(0, maxScores, lastLogs);

        /* verify unit matching */
        assertEquals(0, stats.getUnitID());

        /* verify max score for a level */
        assertEquals(3, stats.getMaxScoreByLevel(Levels.MEDIUM));
        /* update scores */
        stats.updateScores(Levels.MEDIUM, 10);
        /* verify max score for a level, after updating scores in a way that affects max score by level */
        assertEquals(10, stats.getMaxScoreByLevel(Levels.MEDIUM));
        /* update scores again */
        stats.updateScores(Levels.MEDIUM, 4);
        /* verify max score for a level, after updating scores in a way that DOESN'T affect max score by level */
        assertEquals(10, stats.getMaxScoreByLevel(Levels.MEDIUM));

        /* create expected last rates map, in order to compare it with the one returned by the method */
        final Map<Checker.Result, Integer> lastRates = new TreeMap<>();
        lastRates.put(Checker.Result.RIGHT_ANSWER, 4);
        lastRates.put(Checker.Result.WRONG_ANSWER, 2);
        lastRates.put(Checker.Result.NULL_ANSWER, 2);

        /* compare last rates */
        assertEquals(lastRates, stats.getLastRatesByLevel(Levels.EASY));
        /* update check logs */
        stats.updateCheckLogs(Levels.EASY, Arrays.asList(new CheckLogImpl(results)));

        /* create the new expected last rates map */
        lastRates.clear();
        lastRates.put(Checker.Result.RIGHT_ANSWER, 2);
        lastRates.put(Checker.Result.WRONG_ANSWER, 1);
        lastRates.put(Checker.Result.NULL_ANSWER, 1);

        /* compare last rates */
        assertEquals(lastRates, stats.getLastRatesByLevel(Levels.EASY));

    }
}
