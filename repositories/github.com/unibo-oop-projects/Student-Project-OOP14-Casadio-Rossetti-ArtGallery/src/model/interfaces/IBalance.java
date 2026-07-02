package model.interfaces;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * This interface models the summary of the incomes related 
 * to the expositions made until the current date.
 * 
 * @author Sofia Rosetti
 *
 */
public interface IBalance {
	
	/**
	 * This method returns the map containing the specific income for every year.
	 * 
	 * @return the incomes map
	 */
	Map<Integer, Double> getIncomes();
	
	/**
	 * This method calculates and returns the total income at the current date.
	 * 
	 * @return the total income at the current date
	 * 
	 * @param inc
	 * 			the map of the expositions and the related incomes
	 */
	double profit(Map<IExhibit, Double> inc);
	
	/**
	 * Given an year, this method returns the specific income.
	 * 
	 * @return the income of the given year
	 * 
	 * @param year
	 * 			the year of the required income
	 * 
	 * @throws NoSuchElementException
	 * 			  if the given exposition isn't present
	 */
	double getPreviousIncome(int year);

}
