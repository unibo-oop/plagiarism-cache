package model.interfaces;

import java.util.Calendar;
import java.util.Map;
import java.util.NoSuchElementException;

import model.classes.Exhibit;
import model.classes.ExhibitData;

/**
 * This interface models the act of selling tickets and cashing in the payments.
 * @author Sofia Rosetti
 *
 */

public interface ISalesManagement {
	
	/**
	 * This method adds a new exhibit to the map which stores the exhibits data 
	 * related to the management of the ticket office.
	 * 
	 * @param ex
	 * 			the new exhibit
	 */
	void addExhibit(IExhibit ex);
	
	/**
	 * This method resets the number of available tickets for an exhibit, 
	 * bringing it back to the default number.
	 */
	void resetTickets();
	
	/**
	 * This method returns the exhibits data related to the management of the ticket office.
	 *  
	 * @return the map containing the exhibits data
	 */
	Map<IExhibit, ExhibitData> getData();
	
	/**
	 * Given an exhibit, this method returns the related income.
	 * 
	 * @param ex
	 * 			the exhibit
	 * @return the income
	 */
	double getIncome(Exhibit ex);
	
	/**
	 * This method sets the map containing the expositions 
	 * and the related available tickets to the given one.
	 * 
	 * @param m
	 * 			the new map 
	 */
	void setData(final Map<IExhibit, ExhibitData> m);
	
	/**
	 * This method models a new purchase, calculating and returning the total.
	 * 
	 * @param ex
	 * 			  the exhibit
	 * @param percentage
	 * 			  the percentage of discount; 0 if the ticket is a full-price ticket
	 * 
	 * @param number
	 * 			  the number of tickets of this specific purchase
	 * 
	 * @param price
	 * 			  the original price of the exhibit
	 * 
	 * @return the total of the purchase
	 */
	double purchase(IExhibit ex, double percentage, int number, double price);
	
	/**
	 * Given the exhibit, this method returns the number of available tickets. 
	 *  
	 * @param ex
	 *            the exhibit
	 *            
	 * @return the available tickets
	 * 
	 * @throws NoSuchElementException
	 * 			  if the given exhibit isn't present
	 */
	int getAvailableTickets(IExhibit ex);
	
	/**
	 * Given the exhibit, this method returns if it is already present in the map.  
	 * 
	 * @return true, if the exhibit is present
	 * 
	 * @param ex
	 *            the exhibit 
	 */
	boolean isExPresent(IExhibit ex);
	
	/**
	 * This method returns the date of the last purchase.
	 * 
	 * @return the last purchase date
	 */
	Calendar getLastDate();

}
