package model.leaderboard;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StandardScoreSortingStrategy implements LeaderboardSortingStrategy {

    /**
     * Method that allows to sort by score, the ranking.
     * @param map - map that must be ordered.
     * @return a sort Map by score.
     */
    @Override
    public Map<String, Integer> sortMap(final Map<String, Integer> map) {
        return map.entrySet()
                  .stream()
                  .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                  .collect(Collectors.toMap(Map.Entry::getKey,
                           Map.Entry::getValue, (e1, e2) -> e1,
                           LinkedHashMap::new));
    }

}
