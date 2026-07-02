package model.statistic.statistics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.statistic.StatisticMatch;
/**
 * 
 * Class representing the most frequent player.
 *
 */
public final class MostFrequentPlayerGlobalStatistic extends AbstractStatistic<String> {
/**
 * the return value.
 */
    private String value;
/**
 * 
 * @param matches all the matches played
 */
    public MostFrequentPlayerGlobalStatistic(final List<StatisticMatch> matches) {
        super("Player who has played more");
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = "---";
       initialize(matches);
    }

    private void initialize(final List<StatisticMatch> matches) {
        final Map<String, Integer> playerMatchFreq = new HashMap<>();
        matches.stream().filter(statistic -> !statistic.getPlayerName().equalsIgnoreCase("Anonymous")).forEach(statistic -> {
            if (!playerMatchFreq.containsKey(statistic.getPlayerName())) {
                playerMatchFreq.put(statistic.getPlayerName(), 0);
            }
            playerMatchFreq.put(statistic.getPlayerName(), playerMatchFreq.get(statistic.getPlayerName()) + 1);
        });
        playerMatchFreq.keySet().stream().max(Comparator.comparingInt(playerName -> playerMatchFreq.get(playerName)))
                         .ifPresent(maxValue -> value = maxValue);
    }
    @Override
    public String getValue() {
        return value;
    }

}
