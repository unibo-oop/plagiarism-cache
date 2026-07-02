package model.statistic.statistics;

import model.statistic.StatisticMatch;
/**
 * 
 *Class representing the number of shoots received, that hit his boats.
 */
public final class ShotReceivedHitCountStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final int value;
/**
 * 
 * @param match played by a player
 */
    public ShotReceivedHitCountStatistic(final StatisticMatch match) {
        super("Number of shots received that hit my ships");
        if (match == null) {
            throw new IllegalArgumentException("match");
        }
        value = (int) match.getShotReceivedHitCount();
        }

    @Override
    public Integer getValue() {
        return value;
     }

}
