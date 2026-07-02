package controller;

import login.Player;

/**
 * Interface of the controller of the player's profile.
 *
 */
public interface ControllerProfile extends Controller {

    /**
     * @return the current player logged.
     */
    Player getPlayer();

    /**
     * Method that allows the player to change his information about his country and age.
     * 
     * @param newCountry : a string of the new country input.
     * @param newAge : an int of the new age input.
     * @throws IllegalArgumentException : all parameter have to be present and age has to be major of zero.
     */
    void saveChanges(String newCountry, int newAge);

    /**
     * Method that logs out the current player that is logged in.
     */
    void logout();

}
