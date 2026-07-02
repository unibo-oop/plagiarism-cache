package it.unibo.pacman.controller;

import java.util.Map;

import it.unibo.pacman.model.utilities.Difficulty;

/**
 * An interface that defines LeaderboardController methods.
 */
public interface LeaderboardController {

    /**
     * Used to get the ranking in descending order.
     * 
     * @param difficulty of the wanted ranking
     * @return a map representing the ranking
     */
    Map<String, Integer> getSortedRanking(Difficulty difficulty);

    /**
     * Adds a player and his score to the Ranking txt file.
     * 
     * @param player     name of the player
     * @param score      made by the player
     * @param difficulty used to identify the file to write on
     */
    void writeScore(String player, int score, Difficulty difficulty);

    /**
     * Used to close leaderboardView.
     */
    void close();

    /**
     * Used to create a new LeaderboardView and show it.
     * 
     * @param difficulty of the ranking that will be showed
     */
    void setView(Difficulty difficulty);
}
