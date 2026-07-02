package login;

import java.util.Map;

/**
 * Interface of the global rank of the players in the application.
 */
public interface GlobalRank {

    /**
     * Method that updates the rank after a log in of a new player or after that an
     * existing player has changed his bestScore.
     * 
     * @param updatePlayer : the player to put/update in the rank.
     */
    void updateRank(Player updatePlayer);

    /**
     * @return the global rank as a map of <String, Integer> that are name and score
     *         of the player.
     */
    Map<String, Integer> getRank();

    /**
     * @param rank : the map to set as global rank.
     */
    void setRank(Map<String, Integer> rank);

}
