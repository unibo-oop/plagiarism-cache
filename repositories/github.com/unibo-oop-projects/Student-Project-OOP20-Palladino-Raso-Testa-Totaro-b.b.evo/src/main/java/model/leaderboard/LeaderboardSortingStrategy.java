package model.leaderboard;

import java.util.Map;
/**
 * Interface that define a generic operation to sort the leaderboard.
 */
@FunctionalInterface
public interface LeaderboardSortingStrategy {

    Map<String, Integer> sortMap(Map<String, Integer> map);
}
