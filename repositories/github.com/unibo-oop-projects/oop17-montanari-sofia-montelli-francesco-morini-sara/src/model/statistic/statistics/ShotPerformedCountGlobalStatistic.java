package model.statistic.statistics;

import java.util.List;

import model.statistic.StatisticMatch;
/**
 * 
 * Class representing the number of shoots performed.
 */
public final class ShotPerformedCountGlobalStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final Integer value;
/**
 * 
 * @param matches all the matches played
 */
    public ShotPerformedCountGlobalStatistic(final List<StatisticMatch> matches) {
        super("Number of shots fired");
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = matches.stream().mapToInt(statistic -> new ShotPerformedCountStatistic(statistic).getValue()).sum();
    }

    @Override
    public Integer getValue() {
        return value;
    }


}
