package controller;

import java.io.File;

import model.pkglevels.LogInModel;
import model.pkglevels.LogInModelImpl;
import model.pkgplayer.Player;
import view.LogInWindow;

/**
 * Basic interface for the login controller class.
 * 
 * 
 *
 */
public interface LogInController {

    /**
     * Method that checks the input parameter for login .
     * 
     * @param p
     *            current player
     * @return value that specify if the input is correct
     */
    int check(Player p);

    /**
     * Search the player in the file.
     * 
     * 
     * @param p
     *            player to search
     * 
     * @param status
     *            indicates if it is login or register phase
     * 
     * @param f
     *            File that contains the users
     * @return operation result
     * 
     */
    int readData(Player p, boolean status, File f);

    

    /**
     * Method for adding a model.
     * 
     * 
     * @param l
     *            Model to add
     */
    void addModel(LogInModel l);

    /**
     * Method used in register phase to save a new player.
     * 
     * 
     * @param currentPlayer
     *            player to register
     * @param f
     *            file used for register operations
     * @return an integer that state the operation success or failure
     */
    int registerPlayerData(Player currentPlayer, File f);

}
