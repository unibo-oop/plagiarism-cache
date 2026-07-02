package model.statistic.statistics;

import java.util.List;

import model.statistic.StatisticMatch;
/**
 * 
 * class representing the number of games won by a player.
 *
 */
public final class WonMatchesCountStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final int value;
/**
 * 
 * @param matches list of all matches played by a player
 */
    public WonMatchesCountStatistic(final List<StatisticMatch> matches) {
        super("Number of matches won");
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = (int) matches.stream()
                .filter(statistic -> statistic.isWinner())
                .count();
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
