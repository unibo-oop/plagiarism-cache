package it.unibo.biscia.utils.fileIO.leaderboard;

import it.unibo.biscia.core.Player;
import it.unibo.biscia.utils.fileIO.FileIO;

import java.util.Map;

/**
 * Leaderboard of the players. The leaderboard is saved to a file via
 * {@link FileIO}.
 *
 */
public interface Leaderboard {

    /**
     * Write the new score of a {@link Player} via {@link Player#getPoints()} to the
     * leaderboard's file only if the new score is greater or equal of the old one
     * AND greater or equal of 0 OR no score was registered before.
     * 
     * @param player The player to be updated on the leaderbord's file
     */
    void update(Player player);

    /**
     * Get top 10 leaderboard's player's score in descending order.
     * 
     * @return A Map Player's name -> Player's score
     */
    Map<String, Integer> getScores();
}
