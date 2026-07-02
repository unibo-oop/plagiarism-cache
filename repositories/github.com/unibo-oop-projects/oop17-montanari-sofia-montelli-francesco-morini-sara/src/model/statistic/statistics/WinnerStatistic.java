package model.statistic.statistics;


import model.statistic.StatisticMatch;
/**
 * 
 * Class representing the winner of a match.
 *
 */
public final class WinnerStatistic extends AbstractStatistic<String> {
/**
 * the return value.
 */
    private final String value;
/**
 * 
 * @param match all the matches played
 */
    public WinnerStatistic(final StatisticMatch match) {
        super("WINNER");
        if (match == null) {
            throw new IllegalArgumentException("matches");
        }
        if (match.isWinner()) {
            value = "YES";
        } else {
            value = "NO";
        }
    }

    @Override
    public String getValue() {
        return value;
    }

}
