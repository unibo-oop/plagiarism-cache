package controller.leaderboardhandler;

import java.util.List;
import model.ship.PlayerShip.PlayerScore;

/**
 * Handles the saving of the score of the player. 
 */
public interface LeaderboardHandler {

    /**
     * Try to save the score of the player in an external file.
     * If the score isn't in the top ten scores ever it will not be saved.
     * @param score the score obtained by the player
     */
    void save(PlayerScore score);

    /**
     * Return a list containing all elements of the leaderboard.
     * @return a list containing all elements of the leaderboard.
     */
    List<PlayerScore> getLeaderboard();

}
