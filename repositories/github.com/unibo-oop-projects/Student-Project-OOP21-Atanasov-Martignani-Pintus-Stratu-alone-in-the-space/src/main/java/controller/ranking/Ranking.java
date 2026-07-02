package controller.ranking;

import java.io.IOException;

/**
 * Ranking interface.
 */
public interface Ranking {

    /**
     * Add a new player's score to the ranking list.
     *
     * @param playerNickname
     * @param playerScore
     * @throws IOException
     */
    void addPlayer(String playerNickname, Integer playerScore) throws IOException;

    /**
     * Return a formatted String containing a limited version of the information of
     * the ranking map.
     *
     * @param limit
     * @return a formatted String
     */
    String getFormattedRanking(int limit);
}
