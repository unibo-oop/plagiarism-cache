package controller.leaderboard;

import java.util.Map;

import model.leaderboard.LeaderboardSortingStrategy;
import model.leaderboard.Player;
/**
 * This interface define the correct controller for the leaderboard of the game.*/
public interface LeaderboardController {

    /**
     *  Method that allows to , add or overwrite, player in the ranking.
     *  @param player - Represent the player instance.
     */
    void addPlayerInLeaderBoard(Player player);

    /**
     *  Return a Map representing the Couple Alias - Score sorted by score.
     *  @return a Map representing the Couple Alias - Score sorted by score.
     *
     */
    Map<String, Integer> viewLeaderboard();

    /**
     *  Return a Map representing the Couple Alias - Score sorted by score that contains
     *  only n-index elements.
     *  @param index - used to set podium range (ex. first second third) or (ex. first second third fourth).
     *  @param ls - use to understand the sort strategy
     *  @return a Map representing the Couple Alias - Score sorted by score that contains only n-index elements.
     *
     *
     */
    Map<String, Integer> getPodium(int index, LeaderboardSortingStrategy ls);

    /**
     *  Method that allows to print on file a sorted leaderboard.
     *  @param ls - use to understand the sort strategy.
     */
    void saveSortLeaderboard(LeaderboardSortingStrategy ls);

    /**
     *  Method that allows to delete leaderboard and print it on file.
     */
    void clearLeaderboard();
}
