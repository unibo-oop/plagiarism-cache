/**
 *@author Ceccarelli 
 */
package view;
import view.observer.LoginObserver;

public interface LoginPanel {

	/**
	 * cleans all textField in the panel
	 */
	void clearLogin();
	/**
	 * set for the LoginObserver
	 * 
	 * @param observer
	 */
	void attachObserver (LoginObserver observer);
	
	/**
	 * the method used to display a message
	 * 
	 * @param message
	 */
	void displayMessage(String message);
}
