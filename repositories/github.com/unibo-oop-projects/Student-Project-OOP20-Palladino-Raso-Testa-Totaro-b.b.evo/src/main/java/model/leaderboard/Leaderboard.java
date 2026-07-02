package model.leaderboard;

import java.util.Map;
/**
 * This interface represent the leaderboard of the game.
 * */
public interface Leaderboard {

    /**
     *  Method that allows to sort by score, the ranking.
     *  @param ls - use to understand the sorting strategy.
     */
    void sortByScore(LeaderboardSortingStrategy ls);

    /**
     *  Method that allows to , add or overwrite, player in the ranking.
     *  @param alias - Represent the canonical name of the player.
     *  @param score - Represent the score name of the player.
     */
    void addPlayer(String alias, int score);

    /**
     *  Method that allows to remove player in the ranking.
     *  @param alias - Represent the canonical name of the player.
     *  @param score - Represent the score name of the player.
     *  @exception IllegalArgumentException - If the player is not present in the ranking
     */
    void removePlayer(String alias, int score);

    /**
     *  Return a Map representing the Couple Alias - Score not sorted.
     *  @return a Map representing the Couple Alias - Score not sorted.
     *
     */
    Map<String, Integer> getLeaderBoard();

}
