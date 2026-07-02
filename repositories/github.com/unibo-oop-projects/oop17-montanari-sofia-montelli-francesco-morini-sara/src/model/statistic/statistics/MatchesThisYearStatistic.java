package model.statistic.statistics;

import java.time.LocalDateTime;
import java.util.List;

import model.statistic.StatisticMatch;

/**
 * class representing the number of games played by a player this year.
 *
 */
public final class MatchesThisYearStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final int value;
/**
 * 
 * @param matches list of all matches played by a player
 */
    public MatchesThisYearStatistic(final List<StatisticMatch> matches) {
        super("Match played in this year");
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = (int) matches.stream()
                .filter(statistic -> statistic.getMatchStartDate().getYear() == LocalDateTime.now().getYear())
                .count();
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
