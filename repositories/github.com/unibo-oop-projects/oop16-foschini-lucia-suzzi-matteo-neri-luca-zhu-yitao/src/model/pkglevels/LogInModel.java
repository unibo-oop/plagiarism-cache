package model.pkglevels;

import java.io.File;

import model.pkgplayer.Player;

/**
 * Interface for the log in model.
 * 
 * 
 *
 */
public interface LogInModel {
    /**
     * Method used to state the result of the login or register operation.
     * 
     * @param status
     *            true in login phase
     * @param alreadyRegistered
     *            same player found
     * @return check result
     */
    int notifyAction(boolean status, boolean alreadyRegistered);

    /**
     * This method reads the player data from the login file.
     * 
     * @param p
     *            player to read
     * @param isChecking
     *            registering or not
     * @param f
     *            login file
     * @return control result
     */
    int readPlayerData(Player p, boolean isChecking, File f);

    /**
     * Checks the player name and password.
     * 
     * @param p
     *            player to check
     * @return check result
     */
    int checkInput(Player p);

    /**
     * Registers the given player into the logInfile and updates it.
     * 
     * @param registerPlayer
     *            player to register
     * @param f
     *            login file
     * @return register status
     */
    int registerPlayerData(Player registerPlayer, File f);

}
