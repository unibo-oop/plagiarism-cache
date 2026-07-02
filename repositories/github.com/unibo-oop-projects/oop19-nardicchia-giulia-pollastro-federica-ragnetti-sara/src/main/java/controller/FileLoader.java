package controller;

import java.io.File;

import model.UserPlayer;

/**
 * 
 * This interface represent a loader for user's file.
 *
 */
public interface FileLoader {

    /**
     * 
     * Save the user's data.
     *
     * @param player
     *          the current player
     */
    void saveData(UserPlayer player);

    /**
     * Load the user's data.
     * 
     * @param userFileName
     *         the name of the file where data are storage
     * @return
     *         the current user
     */
    UserPlayer loadData(File userFileName);

}
