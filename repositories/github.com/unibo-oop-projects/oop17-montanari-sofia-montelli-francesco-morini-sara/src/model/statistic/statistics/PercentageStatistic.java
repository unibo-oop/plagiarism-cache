package model.statistic.statistics;

import model.statistic.Statistic;

/**
 * 
 * Class representing the precision of shoots performed by a player.
 *
 */
public final class PercentageStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final int value;
/**
 * 
 * @param denominator of the percentage.
 * @param numerator of the percentage.
 * @param name of statistic.
 */
    public PercentageStatistic(final Statistic<Integer> numerator, final Statistic<Integer> denominator, final String name) {
        super(name); 
        value = (int) ((double) numerator.getValue() / denominator.getValue() * 100);
        }

    @Override
    public Integer getValue() {
        return value;
     }

}
