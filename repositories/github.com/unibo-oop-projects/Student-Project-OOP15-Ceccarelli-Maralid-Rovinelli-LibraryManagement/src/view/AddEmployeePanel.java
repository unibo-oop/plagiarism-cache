/**
 *@author Ceccarelli 
 */
package view;

import view.observer.AddEmployeeObserver;

public interface AddEmployeePanel {
	/**
	 * cleans all textField in the panel 
	 * 
	 **/
	void cleanInterface();

	/**
	 * set the AddEmployeeObserver in the view
	 * 
	 * @param observer
	 **/
	void attachObserver(AddEmployeeObserver observer);
	/**
	 * the method used to display a message
	 * 
	 * @param message
	 */
	void displayMessage(String message);
}
