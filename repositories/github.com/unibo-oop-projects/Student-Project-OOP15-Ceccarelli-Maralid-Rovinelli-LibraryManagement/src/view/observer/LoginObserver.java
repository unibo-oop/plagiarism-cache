/**
 *@author Ceccarelli 
 */
package view.observer;

public interface LoginObserver {
	/**
	 * go to view register employee
	 */
	void registerEmployeeClicked();

	/**
	 * allow user (employee) to login in
	 * 
	 * @param username
	 * @param password
	 */
	void loginEmployee(String username, char[] password);

}
