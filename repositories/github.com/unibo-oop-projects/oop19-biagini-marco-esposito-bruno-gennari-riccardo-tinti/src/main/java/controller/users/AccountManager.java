package controller.users;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface that manages all player operations related to each account.
 *
 */
public interface AccountManager {

    /**
     * Method that creates a new user account and saves it to disk.
     * 
     * @param userName
     *          the name of the user.
     * @param password
     *          the password of the user.
     */
    void createAccount(String userName, String password);

    /**
     * Method that makes the user login.
     * 
     * @param userName
     *          the name of the user.
     * @param password
     *          the password of the user.
     * @return
     *      return true or false.
     */
    boolean logInAccount(String userName, String password);

    /**
     * Method that makes the user logout.
     * 
     * @param userName
     *          the name of the user.
     */
    void logOutAccount(String userName);

    /**
     * Method that returns all the names of registered users.
     * 
     * @return
     *      An optional string list.
     */
    Optional<List<String>> getAllUsername();

    /**
     * Method that sets a user as match winner.
     * 
     * @param userName
     *          the name of the winner user.
     * @param scoreValue
     *          the value of the score reached by the winner at the end of the match.
     */
    void setWinner(String userName, Double scoreValue);

    /**
     * Method that sets a user as match loser.
     * 
     * @param userName
     *          the name of the loser user.
     * @param scoreValue
     *          the value of the score reached by the loser at the end of the match.
     */
    void setLoser(String userName, Double scoreValue);

    /**
     * Method that removes an existing user account, also from disk.
     * 
     * @param userName
     *          the name of the user.
     * @param password
     *          the password of the user.
     */
    void removeAccount(String userName, String password);

    /**
     * Method that returns the statistics of a specific existing user.
     * 
     * @param userName
     *          the name of the user.
     * @return
     *      An optional map data structure.
     */
    Optional<Map<String, Double>> getAccountStats(String userName);

}
