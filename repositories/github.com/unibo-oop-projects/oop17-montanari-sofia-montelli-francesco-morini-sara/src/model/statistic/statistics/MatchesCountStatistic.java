package model.statistic.statistics;

import java.util.List;

import model.statistic.StatisticMatch;
/**
 * class representing the number of games played by a player.
 *
 */
public class MatchesCountStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final int value;
/**
 * 
 * @param matches list of all matches played by a player
 */
    public MatchesCountStatistic(final List<StatisticMatch> matches) {
        super("Number of matches played");
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = matches.size();
        }
/**
 * return the number of matches played.
 */
    @Override
    public Integer getValue() {
        return value;
     }

}
