/**
 *@author Ceccarelli 
 */
package view;

import view.observer.RecepitObserver;

public interface ReceiptPanel {
	/**
	 * set the BookshopObserver in the view
	 * 
	 * @param observer
	 */
	void attachObserver(RecepitObserver observer);
	/**
	 * set all books selected in BookShopPanelImpl in the Jtable
	 */
	void setRecepit();
	/**
	 * the method used to display a message
	 * 
	 * @param message
	 */
	void displayMessage(String message);
}
