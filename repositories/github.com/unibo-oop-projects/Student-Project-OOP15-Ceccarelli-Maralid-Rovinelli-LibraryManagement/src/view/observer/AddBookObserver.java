/**
 *@author Ceccarelli 
 */
package view.observer;

public interface AddBookObserver {
	/**
	 * add new book into warehouse when click the button
	 * 
	 * @param title
	 * @param author
	 * @param literaryGenre
	 * @param year
	 * @param price
	 * @param ammount
	 */
	void addBookClicked(String title, String author, String literaryGenre, int year, double price, int ammount);

	/**
	 * go to warehouse view
	 */
	void backToWharehouseClicked();
}
