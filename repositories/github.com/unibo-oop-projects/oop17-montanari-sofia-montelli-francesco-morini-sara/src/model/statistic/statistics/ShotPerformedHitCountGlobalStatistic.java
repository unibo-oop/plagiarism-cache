package model.statistic.statistics;

import java.util.List;
import model.statistic.StatisticMatch;
/**
 * 
 *Class representing the overall number of shoots performed.
 *
 */
public final class ShotPerformedHitCountGlobalStatistic extends AbstractStatistic<Integer> {
/**
 * the return value.
 */
    private final Integer value;
/**
 * 
 * @param matches all the matches played
 */
    public ShotPerformedHitCountGlobalStatistic(final List<StatisticMatch> matches) {
        super("Number of shots that have scored");
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = matches.stream().filter(statistic -> !statistic.getPlayerName().equalsIgnoreCase("Anonymous"))
                .mapToInt(statistic -> new ShotPerformedHitCountStatistic(statistic).getValue()).sum();
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
