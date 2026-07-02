/**
 *@author Ceccarelli 
 */
package view;

import view.observer.AddSubscriptionObserver;

public interface AddSubscriptionPanel {
	/**
	 * set for the WarehousePnaleImpl observer
	 * 
	 * @param observer
	 * */
	void attachObserver(AddSubscriptionObserver observer);
	/**
	 * cleans all textField in the panel 
	 */
	void clearPanel();
	/**
	 *this method fill the Jtable with the subscriptions list 
	 * 
	 */
	void setAllSubscriptions();
	/**
	 * the method used to display a message
	 * 
	 * @param message
	 */
	void displayMessage(String message);
	
}
