package controller;

import java.util.Optional;

import login.LoginTypes;
import login.Player;

/**
 * Interface of the controller of the login part of the application.
 *
 */
public interface ControllerLogin extends Controller {

    /**
     * Method that switch the view of the login part based on the parameter.
     * 
     * @param loginType : an enumeration that identifies which login view has to be
     *                  shown.
     */
    void switchView(LoginTypes loginType);

    /**
     * Method that tries to login a user based on which username and password he has
     * given.
     * 
     * @param checkUsername : string that is the username input written by the user.
     * @param checkPassword : string that is the password input written by the user.
     */
    void tryLog(String checkUsername, String checkPassword);

    /**
     * Method that save a user as a player, with all his information, in the
     * database of the application.
     * 
     * @param newName    : name of the new player.
     * @param password   : a password to login again in the future.
     * @param newCountry : the country of the user.
     * @param age        : the age of the user.
     */
    void createPlayer(String newName, String password, String newCountry, int age);

    /**
     * @return an optional that can contain the current player if he has done the
     *         login.
     */
    Optional<Player> getPlayer();
}
