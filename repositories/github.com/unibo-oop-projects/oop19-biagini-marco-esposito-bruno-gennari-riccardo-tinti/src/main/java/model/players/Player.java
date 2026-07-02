package model.players;

import java.util.Map;

/**
 * Represents the concept of player and contains all its informations.
 * 
 */
public interface Player {

    /**
     * @return
     *      returns the name of the player.
     */
    String getUsername();

    /**
     * @return
     *      returns the password of the player.
     */
    String getPassword();

    /**
     * Method that updates the value of the specific statistical information.
     * 
     * @param desc
     *          the statistical information to be updated.
     * @param updatedValue
     *          the updated value of the specific statistical information.
     */
    void updateStats(String desc, Double updatedValue);

    /**
     * @return
     *      returns the statistics of the player.
     */
    Map<String, Double> getStatistics();

    /**
     * Method that sets the player's access value.
     * 
     * @param value
     *      true or false.
     */
    void setLogin(boolean value);

    /**
     * @return
     *      returns true or false.
     */
    boolean isPlaying();

    /**
     * @return
     *      returns the string player value.
     */
    String toString();

}
