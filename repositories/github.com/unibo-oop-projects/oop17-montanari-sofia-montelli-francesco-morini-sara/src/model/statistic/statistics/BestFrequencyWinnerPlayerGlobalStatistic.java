package model.statistic.statistics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.statistic.StatisticMatch;
/**
 * 
 * Class representing the player that have the best win frequency.
 *
 */
public final class BestFrequencyWinnerPlayerGlobalStatistic extends AbstractStatistic<String> {
    /**
     * the return value.
     */
    private String value;
    /**
     * 
     * @param matches all the matches played
     */
    public BestFrequencyWinnerPlayerGlobalStatistic(final List<StatisticMatch> matches) {
        super("Player with more frequency of victory");
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = "---";

        initialize(matches);
    }

    private void initialize(final List<StatisticMatch> matches) {
        final Map<String, Integer> playerMatchCount = new HashMap<>();
        List<StatisticMatch> list;
        matches.stream().filter(statistic -> !statistic.getPlayerName().equalsIgnoreCase("Anonymous")).forEach(statistic -> {
            if (!playerMatchCount.containsKey(statistic.getPlayerName())) {
                playerMatchCount.put(statistic.getPlayerName(), 0);
            }
        });


        for (final String name : playerMatchCount.keySet()) {
            list = matches.stream().filter(statistic -> statistic.getPlayerName().equals(name)).collect(Collectors.toList());
            playerMatchCount.put(name, new PercentageStatistic(new  WonMatchesCountStatistic(list), new MatchesCountStatistic(list), "Victory frequency (PERCENTAGE)").getValue());

        }
        playerMatchCount.keySet().stream().filter(namePlayer -> !playerMatchCount.get(namePlayer).equals(0))
                            .max(Comparator.comparingInt(namePlayer -> playerMatchCount.get(namePlayer))).ifPresent(maxValue -> value = maxValue);
    }



    @Override
    public String getValue() {
        return value;
    }


}
