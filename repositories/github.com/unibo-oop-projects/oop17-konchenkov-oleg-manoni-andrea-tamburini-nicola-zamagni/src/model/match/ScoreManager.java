package model.match;

/**
 * Describes the score of a player.
 *
 * @author Andrea Manoni
 *
 */
public interface ScoreManager {

    /**
     * The numbers of kills of the player.
     *
     * @return kill
     */
    int getKill();

    /**
     * Score of the player.
     *
     * @return score
     */
    int getScore();

    /**
     * Ranking of the player.
     *
     * @return ranking
     */
    int getRanking();

    /**
     * Sets the kills of a plyaer.
     *
     * @param kills
     *            kills
     */
    void setKill(int kills);

    /**
     * Set the ranking of a player.
     *
     * @param rank
     *            rank
     */
    void setRanking(int rank);

    /**
     * Adds score point for a turn survived.
     */
    void survivedNewTurn();

    /**
     * Add score point for winning the game.
     */
    void wonMatch();
}
