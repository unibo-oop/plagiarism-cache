/**
 *@author Ceccarelli 
 */
package view.observer;

import java.util.Map;

import model.SubscriptionModel;

public interface AddSubscriptionObserver {

	/**
	 * this method saves a new subscription when click the button
	 * 
	 * @param name
	 * @param surname
	 */
	void addNewSubcriptionClicked(String name, String surname);

	/**
	 * this method return map of subscriptions
	 * 
	 * @return Map <integer,SubscriptionModel>
	 */
	Map<Integer, SubscriptionModel> getAllSubscriptions();
}
