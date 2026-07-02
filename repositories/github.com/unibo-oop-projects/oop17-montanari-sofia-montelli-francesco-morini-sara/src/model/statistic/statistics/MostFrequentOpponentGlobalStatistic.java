package model.statistic.statistics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.statistic.StatisticMatch;
/**
 * class representing the number of games played by a player against an opponent player.
 *
 */
public final class MostFrequentOpponentGlobalStatistic extends AbstractStatistic<String> {
    /**
     * the return value.
     */
    private String value;
    /**
     * 
     * @param matches list of all matches played by a player
     */
    public MostFrequentOpponentGlobalStatistic(final List<StatisticMatch> matches) {
        super("Opponent with whom you played more");
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = "---";
       initialize(matches);
    }

    private void initialize(final List<StatisticMatch> matches) {
        final Map<String, Integer> matchFrequency = new HashMap<>();
        matches.stream().filter(statistic -> !statistic.getPlayerName().equalsIgnoreCase("Anonymous"))
          .filter(statistic -> !statistic.getOpponentName().equalsIgnoreCase("Anonymous"))
          .forEach(statistic -> {
            if (!matchFrequency.containsKey(statistic.getOpponentName())) {
                matchFrequency.put(statistic.getOpponentName(), 0);
            }
            matchFrequency.put(statistic.getOpponentName(), matchFrequency.get(statistic.getOpponentName()) + 1);
        });
        matchFrequency.keySet().stream().max(Comparator.comparingInt(playerName -> matchFrequency.get(playerName)))
                              .ifPresent(maxValue -> value = maxValue);
    }

    @Override
    public String getValue() {
        return value;
     }
}
