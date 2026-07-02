package login;

import java.util.List;
import java.util.Optional;

/**
 * Interface of a player.
 */
public interface Player {

    /**
     * Method that adds a new custom piece to the his current list.
     * 
     * @param customPiece : new custom piece to add.
     */
    void addCustomPiece(Custom customPiece);

    /**
     * Method to set the optional list of custom pieces of the player.
     * 
     * @param customList : the optional list to set.
     */
    void setCustomPiece(Optional<List<Custom>> customList);

    /**
     * Method to get the optional list of custom pieces of the player.
     * 
     * @return : Optional.empty if there are no pieces, and optional that contains
     *         the list otherwise.
     */
    Optional<List<Custom>> getCustomPiece();

    /**
     * Method to get the best score of the player as an integer.
     * 
     * @return the integer of the best score.
     */
    Integer getBestScore();

    /**
     * Method that set 'bestScore' parameter as the best score of the player, only
     * if the one already saved is lower of it.
     * 
     * @param bestScore : last score the player has achieved.
     */
    void setBestScore(Integer bestScore);

    /**
     * @return the name of the player as a string.
     */
    String getName();

    /**
     * @param name : the name that will be set as player's name.
     */
    void setName(String name);

    /**
     * @return the country of the player as a string.
     */
    String getCountry();

    /**
     * @param country : the country that will be set as the player's country.
     */
    void setCountry(String country);

    /**
     * Method that save, or update if he is already present, the player in a JSON
     * file in the database of the application.
     */
    void writeOnFile();

    /**
     * @param age : int that will be set as the player's age.
     */
    void setAge(int age);

    /**
     * @return the age of the player as an int
     */
    int getAge();

    /**
     * @return the encrypted password of the player.
     */
    String getEncryptedPassword();

    /**
     * @param encryptedPassword : encrypted password that will be set as player's password.
     */
    void setEncryptedPassword(String encryptedPassword);

    /**
     * @return : the salt to regenerate the encrypted password of the player.
     */
    String getSalt();

    /**
     * @param salt : a string that will be set as the player's salt.
     */
    void setSalt(String salt);

}
