package model.statistic.statistics;

import java.time.LocalDateTime;
import java.util.List;

import model.statistic.StatisticMatch;

/**
 * class representing the number of games played by a player today.
 *
 */
public final class MatchesTodayStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final int value;
/**
 * 
 * @param matches list of all matches played by a player
 */
    public MatchesTodayStatistic(final List<StatisticMatch> matches) {
        super("Match played today");
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = (int) matches.stream()
                .filter(statistic -> 
                    statistic.getMatchStartDate().getYear() == LocalDateTime.now().getYear()
                    && statistic.getMatchStartDate().getMonth() == LocalDateTime.now().getMonth()
                    && statistic.getMatchStartDate().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                .count();
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
