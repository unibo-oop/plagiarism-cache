package model.interfaces;

/**
 * This interface models the exhibits data related to the management of the ticket office,
 * such as the number of daily tickets and the total income resulting from 
 * the sale of the tickets.
 * 
 * @author Sofia Rosetti
 *
 */
public interface IExhibitData {
	
	/**
	 * This method returns the number of available tickets for an exhibit at the current day.
	 * 
	 * @return the available tickets
	 */
	int getAvailability();
	
	/**
	 * This method returns the income related to an exhibit at the current date.
	 * 
	 * @return the current income
	 */
	double getIncome();
	
	/**
	 * This method sets the number of available tickets to the given number.
	 * 
	 * @param av
	 * 			the new number of tickets 
	 */
	void setAvailability(int av);
	
	/**
	 * This method sets the income to the given one.
	 * 
	 * @param inc
	 * 			the new income
	 */
	void setIncome(double inc);

}
