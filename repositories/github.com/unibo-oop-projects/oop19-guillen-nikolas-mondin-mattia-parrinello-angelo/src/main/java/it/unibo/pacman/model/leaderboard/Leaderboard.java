package it.unibo.pacman.model.leaderboard;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class used to fill and sort a map with various players' names and their
 * respective scores.
 */
public class Leaderboard {

    private Map<String, Integer> ranking;

    /**
     * Leaderboard constructor, calls two private methods to fill and sort Ranking's
     * map.
     * 
     * @param ranking ordered as it is in the .txt file
     * @throws IOException if the file is not found
     */
    public Leaderboard(final Map<String, Integer> ranking) {
        this.ranking = ranking;
        sortRanking();
    }

    private void sortRanking() {
        this.ranking = this.ranking.entrySet().stream()
                                              .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                                              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * Used to get the ranking map.
     * 
     * @return the ranking map
     */
    public Map<String, Integer> getSortedRanking() {
        return this.ranking;
    }
}
