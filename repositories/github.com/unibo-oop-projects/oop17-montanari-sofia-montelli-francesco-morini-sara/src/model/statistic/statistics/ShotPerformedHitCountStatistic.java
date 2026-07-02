package model.statistic.statistics;

import model.statistic.StatisticMatch;
/**
 * 
 *Class representing the number of shoots that hit an opposing boat.
 */
public final class ShotPerformedHitCountStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final int value;
/**
 * 
 * @param match played by a player
 */
    public ShotPerformedHitCountStatistic(final StatisticMatch match) {
        super("Number of shots that have scored");
        if (match == null) {
            throw new IllegalArgumentException("match");
        }
        value = (int) match.getShotPerformedHitCount();
        }

    @Override
    public Integer getValue() {
        return value;
     }


}
