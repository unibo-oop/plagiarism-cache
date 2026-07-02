package model.interfaces;

import java.util.Calendar;

/**
 * This interface models a generic purchase of one or more tickets for an exhibit.
 * 
 * @author Sofia Rosetti
 *
 */
public interface IPurchase {

	/**
	 * This method returns the date of the purchase.
	 * 
	 * @return the purchase date
	 */
	Calendar getDate();
	
	/**
	 * This method returns the exhibit for which the tickets have been sold.
	 * 
	 * @return the purchase exhibit
	 */
	IExhibit getExhibit();
	
	/**
	 * This method returns the number of tickets sold in this specific purchase.
	 * 
	 * @return the purchased tickets
	 */
	int getNumber();
	
	/**
	 * This method returns the type of the purchased tickets.
	 * 
	 * @return the tickets type
	 */
	ITicket getTicket();
	
	/**
	 * This method returns the total import of the purchase.
	 * 
	 * @return the total import
	 */
	double getTotal();
	
	/**
	 * This method sets the total import of the purchase,
	 * multiplying the number of tickets for their specific price.
	 */
	void setTotal();
	
}
