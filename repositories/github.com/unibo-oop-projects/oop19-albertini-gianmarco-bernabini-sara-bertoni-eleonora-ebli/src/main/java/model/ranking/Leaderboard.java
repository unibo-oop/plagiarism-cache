package model.ranking;

import java.util.List;

/**
 * 
 * Provides useful functionalities to save the rankings of the players.
 *
 */
public interface Leaderboard {

    /**
     *Adds a player to the leaderboard by saving it in a json file.
     *
     * @param player
     *      the new player to add to the leaderboard
     */
    void addPlayer(Player player);

    /**
     * Returns a sorted list of the players, based on the final score and the time og the game.
     * 
     * @return the sorted list of players
     */
    List<Player> getSortedPlayersList();

    /**
     * Deletes the file.
     * 
     * @return true if the file is deleted
     */
    boolean deleteLeaderboard();
}
