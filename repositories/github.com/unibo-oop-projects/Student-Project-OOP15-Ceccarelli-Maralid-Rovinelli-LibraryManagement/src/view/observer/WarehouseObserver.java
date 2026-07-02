/**
 *@author Ceccarelli 
 */
package view.observer;

import java.util.Map;

import model.BookModel;

public interface WarehouseObserver {
	/**
	 * allows to add new books in book shop
	 * 
	 * @param title
	 * @param author
	 * @param literaryGenre
	 * @param year
	 * @param price
	 * @param amount
	 */
	void addBooksInBookShopClicked(String title, String author, String literaryGenre, int year, double price,
			int amount);

	/**
	 * this method returns a list of all books in warehouse
	 * 
	 * @return Map<BookModel, Integer
	 */
	Map<BookModel, Integer> getBooksInWarehouse();

	/**
	 * allow to add few copies to warehouse
	 * 
	 * @param title
	 * @param author
	 * @param year
	 * @param amount
	 */
	void addCopyToWarehouse(String title, String author, int year, int amount);
}
