/**
 *@author Ceccarelli 
 */
package view.observer;

import java.util.Map;

import model.BookModel;

public interface BookshopObserver {
	/**
	 * go to purhcase recap view
	 * 
	 * @param purchaseList
	 */
	void shopPurchaseItClicked(Map<BookModel, Integer> purchaseList);

	/**
	 * It allows you to view all the books in jtable tbl Books
	 * 
	 */
	Map<BookModel, Integer> getBookInShop(String type, String value);
	/**
	 *It allows you to switch to the controller data on eg a search for specific books
	 * 
	 *@return map of the required fields
	 */
	Map< BookModel,Integer>searchType (String type, String value);
}
