/**
 *@author Ceccarelli 
 */
package view;

import view.observer.AddBookObserver;

public interface AddBookPanel {
	/**
	 * the method used to display a message
	 * 
	 * @param message
	 */
	void displayMessage(String message);
	
	/**
	 * set the AddBookObserver in the view
	 * 
	 */
	void attachObserver(AddBookObserver observer);
	
	/**
	 * cleans all textField in the panel 
	 */
	void clearView();
	
}
