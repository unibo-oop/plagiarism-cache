package view.controllers;

import java.io.File;

/**
 * The interface for the Login scene.
 *
 */
public interface LoginFx {

    /**
     * Set the user for the current game session.
     * 
     * @param userFileName 
     *                  the file related to the user
     */
    void setUserPlayer(File userFileName);

    /**
     * Show error message when user type incorrect password or non-existent
     * username.
     * 
     */
    void showError();

    /**
     * Show an alert if a new user tries to insert an existing username.
     * 
     */
    void showAlert();

}
