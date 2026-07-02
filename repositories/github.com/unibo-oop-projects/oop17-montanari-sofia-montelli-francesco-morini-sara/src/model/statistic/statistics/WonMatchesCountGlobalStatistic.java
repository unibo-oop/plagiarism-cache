package model.statistic.statistics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.statistic.StatisticMatch;
/**
 * Class representing the player who has won more matches.
 *
 */
public final class WonMatchesCountGlobalStatistic extends AbstractStatistic<String> {
/**
 * the return value.
 */
    private String value;
/**
 * 
 * @param matches all the matches played
 */
    public WonMatchesCountGlobalStatistic(final List<StatisticMatch> matches) {
        super("Player who has won more games");
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = "---";
       initialize(matches);
    }

    private void initialize(final List<StatisticMatch> matches) {
        final Map<String, Integer> playerMatchCount = new HashMap<>();
        matches.stream().filter(statistic -> !statistic.getPlayerName().equalsIgnoreCase("Anonymous")).forEach(statistic -> {
            if (!playerMatchCount.containsKey(statistic.getPlayerName())) {
                playerMatchCount.put(statistic.getPlayerName(), 0);
            }
        });
        for (final String name : playerMatchCount.keySet()) {
            final List<StatisticMatch> list = matches.stream().filter(statistic -> statistic.getPlayerName().equals(name)).collect(Collectors.toList());
            playerMatchCount.put(name, new WonMatchesCountStatistic(list).getValue());
        }
        playerMatchCount.keySet().stream().filter(platerName -> !playerMatchCount.get(platerName).equals(0))
                         .max(Comparator.comparingInt(playerName -> playerMatchCount.get(playerName))).ifPresent(maxValue -> value = maxValue);
    }

    @Override
    public String getValue() {
        return value;
    }

}
