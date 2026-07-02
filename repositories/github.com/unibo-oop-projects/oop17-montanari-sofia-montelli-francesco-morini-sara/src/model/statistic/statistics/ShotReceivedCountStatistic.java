package model.statistic.statistics;

import model.statistic.StatisticMatch;
/**
 * 
 *Class representing the number of shoots received by a player.
 */
public final class ShotReceivedCountStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final int value;
/**
 * 
 * @param match played by a player
 */
    public ShotReceivedCountStatistic(final StatisticMatch match) {
        super("Number of shots received");
        if (match == null) {
            throw new IllegalArgumentException("match");
        }
        value = (int) match.getShotReceivedCount();
        }

    @Override
    public Integer getValue() {
        return value;
     }

}
