/**
 *@author Ceccarelli 
 */
package view.observer;

import java.util.Date;
import java.util.Map;

import model.BookModel;

public interface RecepitObserver {
	/**
	 * save receipt when click the button
	 * 
	 * @param today
	 * @param payment
	 * @param subscriptionId
	 */
	void saveAccountingClicked(Date today, int payment, String subscriptionId);

	/**
	 * get total of sale
	 * 
	 * @return double 
	 */
	public double getTotal();

	/**
	 * get purchase recap into map
	 * 
	 * @return Map<BookModel, Integer>
	 */
	public Map<BookModel, Integer> getPurchaseRecap();

}
