package model.statistic.statistics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.statistic.StatisticMatch;
/**
 * 
 * Class representing the player that have the best shots precision.
 *
 */
public final class BestPrecisionPlayerGlobalStatistic extends AbstractStatistic<String> {
    /**
     * the return value.
     */
    private String value;
    /**
     * 
     * @param matches all the matches played
     */
    public BestPrecisionPlayerGlobalStatistic(final List<StatisticMatch> matches) {
        super("More precise player"); 
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = "---";
        initialize(matches);
    }

    private void initialize(final List<StatisticMatch> matches) {
        final Map<String, Integer> playerMatchCount = new HashMap<>();
        List<StatisticMatch> list;
        matches.forEach(statistic -> {
            if (!playerMatchCount.containsKey(statistic.getPlayerName())) {
                playerMatchCount.put(statistic.getPlayerName(), 0);
            }
        });

        for (final String name : playerMatchCount.keySet()) {
            list = matches.stream().filter(statistic -> statistic.getPlayerName().equals(name)).collect(Collectors.toList());
            playerMatchCount.put(name, new PercentageStatistic(new ShotPerformedCountGlobalStatistic(list), new ShotPerformedHitCountGlobalStatistic(list), "Precision shots (PERCENTAGE)").getValue());
        }
        playerMatchCount.keySet().stream().filter(namePlayer -> !playerMatchCount.get(namePlayer).equals(0))
                            .min(Comparator.comparingInt(namePlayer -> playerMatchCount.get(namePlayer))).ifPresent(minValue -> value = minValue);
    }

    @Override
    public String getValue() {
        return value;
    }


}
