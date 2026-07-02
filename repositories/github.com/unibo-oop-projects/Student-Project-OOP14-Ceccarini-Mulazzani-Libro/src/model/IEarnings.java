package model;

import java.util.List;

/**
 * 
 * @author Chiara Ceccarini
 *
 */

public interface IEarnings {

	/**
	 * 
	 * @param list of books
	 * @return the number of book sold
	 */
	int bookSold(List <Libro> list);
	
	/**
	 * 
	 * @param list of books
	 * @return number of book in store
	 */
	int bookInStore(List <Libro> list);
	
	/**
	 * 
	 * @param list of books
	 * @return tot sold
	 */
	double totSell(List <Libro> list);
	
	/**
	 * 
	 * @param list of books
	 * @return tot Spent
	 */
	double totSpent(List <Libro> list);
	
	/**
	 * 
	 * @param list of books
	 * @return tot earnings
	 */
	double totEarnings(List <Libro> list);
	
}
