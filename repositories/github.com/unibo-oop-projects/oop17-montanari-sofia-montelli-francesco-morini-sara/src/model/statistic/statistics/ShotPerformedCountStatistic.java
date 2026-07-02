package model.statistic.statistics;


import model.statistic.StatisticMatch;
/**
 * 
 *Class representing the number of shoots performed by a player.
 */
public final class ShotPerformedCountStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final int value;
/**
 * 
 * @param match played by a player
 */
    public ShotPerformedCountStatistic(final StatisticMatch match) {
        super("Number of shots fired");
        if (match == null) {
            throw new IllegalArgumentException("match");
        }
        value = (int) match.getShotPerformedCount();
        }

    @Override
    public Integer getValue() {
        return value;
     }

}
