/**
 *@author Ceccarelli 
 */
package view.observer;

public interface ViewObserver {
	/**
	 * close the application
	 */
	void exitCommand();

	/**
	 * save data into files when you close the application
	 */
	void saveData();

	/**
	 * load data when you open the application
	 */
	void dataLoad();

	/**
	 * go to warehouse view
	 */
	void warehouseClicked();

	/**
	 * go to add book view
	 */
	void addBooksClicked();

	/**
	 * go to book shop view
	 */
	void bookShopClicked();

	/**
	 * go to add new employee view
	 */
	void addEmployeeClicked();

	/**
	 * go to add subscription view
	 */
	void addSubscriptionClicked();

}
