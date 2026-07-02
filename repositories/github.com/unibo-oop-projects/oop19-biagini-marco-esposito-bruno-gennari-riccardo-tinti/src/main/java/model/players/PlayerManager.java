package model.players;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface that manages all the players operations.
 * 
 */
public interface PlayerManager {

    /**
     * Method that adds a player in the system.
     * 
     * @param userName
     *          the name of the player.
     * @param password
     *          the password of the player.
     * @return
     *      Returns an optional value of the added player.
     */
    Optional<Player> addPlayer(String userName, String password);

    /**
     * Method that adds an artificial player in the system.
     * 
     * @param playerAI
     *          the artificial player.
     */
    void addArtificialPlayer(ArtificialPlayer playerAI);

    /**
     * Method that removes a player from the system.
     * 
     * @param userName
     *          the name of the player.
     * @param password
     *          the password of the player.
     * @return
     *      Returns true or false.
     */
    boolean removePlayer(String userName, String password);

    /**
     * Method that set the log in value of a specific player.
     * 
     * @param userName
     *          the name of the player.
     * @param password
     *          the password of the player.
     * @return
     *      Returns true or false.
     */
    boolean setLogIn(String userName, String password);

    /**
     * Method that makes the player log out.
     * 
     * @param userName
     *          the name of the player.
     * @return
     *      Returns true or false.
     */
    boolean setLogOut(String userName);

    /**
     * Method that sets the winning player and updates its statistics.
     * 
     * @param userName
     *          the name of the player.
     * @param score
     *          the value of the score obtained at the end of the match.
     * @return
     *      Returns true or false.
     */
    boolean updateWinStats(String userName, Double score);

    /**
     * Method that sets the losing player and updates its statistics.
     * 
     * @param userName
     *          the name of the player.
     * @param score
     *          the value of the score obtained at the end of the match.
     * @return
     *      Returns true or false.
     */
    boolean updateLosStats(String userName, Double score);

    /**
     * @return
     *      Returns an optional value of a list containing players.
     */
    Optional<List<Player>> getPlayers();

    /**
     * @return
     *      Returns true or false.
     */
    boolean artificialExists();

    /**
     * Method that returns the statistical values of a player.
     * 
     * @param userName
     *          the name of the player.
     * @return
     *      Returns an optional value of a map containing statistics.
     */
    Optional<Map<String, Double>> getPlayerStats(String userName);
}
