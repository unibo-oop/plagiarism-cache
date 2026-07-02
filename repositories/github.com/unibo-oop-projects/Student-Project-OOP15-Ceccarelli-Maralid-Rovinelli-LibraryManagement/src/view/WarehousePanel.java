/**
 *@author Ceccarelli 
 */
package view;

import view.observer.WarehouseObserver;

public interface WarehousePanel {
	/**
	 * set for the WarehousePnaleImpl observer
	 * 
	 * @param observer
	 */
	void attachObserver(WarehouseObserver observer);

	/**
	 * cleans all textField in the panel
	 */
	void clearPanel();

	/**
	 * the method used to display a message
	 * 
	 * @param message
	 */
	void displayMessage(String message);
	/**
	 *load the list of books
	 *
	 */
	void setAllBooks();	
	
}
