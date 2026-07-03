package com.jlearn.model.statistics;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.model.checker.CheckLog;
import com.jlearn.model.checker.Checker.Result;
import com.jlearn.model.utilities.Levels;

/**
 * All the statistics related to a user, for each unit.
 */
public class UserStatisticsImpl implements UserStatistics {

    private static final long      serialVersionUID = 6525975195158473509L;
    private final String           userNickname;
    private final List<Statistics> unitsStatistics;
    private static final Logger    LOG              = Logger.getLogger(UserStatisticsImpl.class);

    /**
     * @param userNickname
     *            the user the statistics are referred to
     * @param unitsStatistics
     *            a list of statistics. Each element of this list is a statistic related to a unit
     */
    public UserStatisticsImpl(final String userNickname, final List<Statistics> unitsStatistics) {
        LOG.setLevel(Level.WARN);
        this.userNickname = userNickname;
        this.unitsStatistics = unitsStatistics;
        LOG.info("Initialized user stats");
    }

    @Override
    public String getUser() {
        return this.userNickname;
    }

    @Override
    public Map<Result, Integer> getUnitRates(final int unitID, final Levels level) {
        if (!this.isUnitIDPresent(unitID)) {
            LOG.warn("Unexisting unit. Can not get rates");
            throw new IllegalArgumentException("Invalid unit ID");
        }
        return this.findStats(unitID).getLastRatesByLevel(level);

    }

    @Override
    public Map<Integer, Integer> getUnitsTopScore(final Levels level) {
        final Map<Integer, Integer> unitsTopScores = new TreeMap<>();
        this.unitsStatistics.stream().forEach(x -> unitsTopScores.put(x.getUnitID(), x.getMaxScoreByLevel(level)));
        return unitsTopScores;
    }

    @Override
    public Map<Levels, Integer> getLevelsTopScore(final int unitID) {
        if (!this.isUnitIDPresent(unitID)) {
            LOG.warn("Unexisting unit. Can not get scores");
            throw new IllegalArgumentException("Invalid unit ID");
        }
        final Statistics stats = this.findStats(unitID);
        final Map<Levels, Integer> levelsTopScore = new TreeMap<>();
        Arrays.asList(Levels.values())
                .stream()
                .forEach(x -> levelsTopScore.put(x, stats.getMaxScoreByLevel(x)));
        return levelsTopScore;
    }

    private boolean isUnitIDPresent(final int unitID) {
        return this.unitsStatistics.stream().filter(x -> x.getUnitID() == unitID).findAny().isPresent();

    }

    private Statistics findStats(final int unitID) {
        return this.unitsStatistics.stream()
                .filter(x -> x.getUnitID() == unitID)
                .findAny()
                .get();
    }

    @Override
    public void registerNewScore(final int unitID, final Levels level, final int score) {
        this.findStats(unitID).updateScores(level, score);
        LOG.info("Score registered successfully");
    }

    @Override
    public void registerNewUnitCheckLogs(final int unitID, final Levels level, final List<CheckLog> logs) {
        this.findStats(unitID).updateCheckLogs(level, logs);
        LOG.info("CheckLogs registered successfully");
    }

    @Override
    public void addStatistic(final Statistics stat) {
        if (this.isUnitIDPresent(stat.getUnitID())) {
            LOG.warn("Can not add existing statistics");
            throw new IllegalArgumentException("Statistics for this unit already exist. Update them instead.");
        }
        this.unitsStatistics.add(stat);
        LOG.info("Statistics added successfully");
    }

}
