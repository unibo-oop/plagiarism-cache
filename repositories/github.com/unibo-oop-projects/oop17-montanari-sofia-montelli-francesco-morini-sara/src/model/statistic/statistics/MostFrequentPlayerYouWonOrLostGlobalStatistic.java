package model.statistic.statistics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.statistic.StatisticMatch;
/**
 * 
 * Class representing the most frequent player with which a player won.
 *
 */
public final class MostFrequentPlayerYouWonOrLostGlobalStatistic extends AbstractStatistic<String> {

    private String value;
/**
 * 
 * @param matches all the matches played
 * @param winner say if the player is the winner or not
 * @param nameStatistic the name of the statistic.
 */
    public MostFrequentPlayerYouWonOrLostGlobalStatistic(final List<StatisticMatch> matches, final boolean winner, final String nameStatistic) {
        super(nameStatistic); 
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = "---";
      initialize(matches, winner);
    }

    private void initialize(final List<StatisticMatch> matches, final boolean winner) {
        final Map<String, Integer> opponentFrequency = new HashMap<>();
        matches.stream().filter(statistic -> !(statistic.getPlayerName().equalsIgnoreCase("Anonymous"))).filter(statistic -> !(statistic.getOpponentName().equalsIgnoreCase("Anonymous")))
           .forEach(statistic -> {
               if (!opponentFrequency.containsKey(statistic.getOpponentName()) && (((winner) && statistic.isWinner()) || ((!winner) && !statistic.isWinner()))) {
                           opponentFrequency.put(statistic.getOpponentName(), 0);
               }
               if (((winner) && statistic.isWinner()) || ((!winner) && !statistic.isWinner())) {
                   opponentFrequency.put(statistic.getOpponentName(), opponentFrequency.get(statistic.getOpponentName()) + 1);
               }
        });
        opponentFrequency.keySet().stream().max(Comparator.comparingInt(playerName -> opponentFrequency.get(playerName))).ifPresent(maxValue -> value = maxValue);
    }

    @Override
    public String getValue() {
        return value;
    }

}
