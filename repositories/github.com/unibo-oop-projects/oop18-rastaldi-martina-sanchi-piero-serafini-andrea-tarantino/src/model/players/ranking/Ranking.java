package model.players.ranking;

import java.util.List;

import model.players.Player;

/**
 * Interface to manipulate a ranking.
 *
 * Andrea Serafini.
 *
 */
public interface Ranking {

    /**
     * Try to add a player and his score in the ranking.
     *
     * @param player
     *            the player to be added.
     */
    void addScore(Player player);

    /**
     * Delete the current ranking file.
     */
    void clear();

    /**
     * Getter.
     *
     * @return the ranking like a string.
     */
    String getHighscoreString();

    /**
     * Getter of the name of the player in a fixed position in the ranking.
     *
     * @param i
     *            position.
     * @return the name of the player.
     */
    String getNameAtPosition(int i);

    /**
     * Getter of the score of a fixed position in the ranking.
     *
     * @param i
     *            position.
     * @return the score.
     */
    int getScoreAtPosition(int i);

    /**
     * Getter.
     *
     * @return the list containing the ranking like HighScores object.
     */
    List<Player> getScores();

    /**
     * Check.
     *
     * @return if the file is Present.
     */
    boolean isPresent();

}
