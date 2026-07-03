package view.interfaces;

import java.awt.event.ActionListener;

/**
 * The class that will implement this interface will have to
 * return the user name and password
 */
public interface LoginView {

	/**
	 * Return the user name inserted.
	 * 
	 * @return user name
	 */
    String getUsername();

    /**
     * Return the password inserted.
     * 
     * @return password
     */
    String getPassword();

    /**
     * The listener used for the login button.
     * 
     * @param loginListener
     */
    void addLoginListener(ActionListener loginListener);

    /**
     * The listener used for the exit button.
     * 
     * @param exitListener
     */
    void addExitListener(ActionListener exitListener);
    
    /**
     * Displays an error when confirm button is clicked and not all fields are compiled.
     * 
     * @param error
     */
    void displayErrorMessage(String error);
    
    /**
     * Displays a confirm message when exit button
     * is clicked to ask if you are sure to exit or not.
     * 
     * @param confirm
     */
    void displayConfirmMessage(String confirm);

    /**
     * This method is used to dispose of the current JFrame and pass onto the next one.
     */
    void close();
}
