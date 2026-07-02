/**
 *@author Ceccarelli 
 */
package view.observer;

import java.util.Date;

public interface AddEmployeeObserver {
	/**
	 * save employee when click the button
	 * 
	 * @param name
	 * @param username
	 * @param address
	 * @param username
	 * @param password
	 * @param email
	 * @param telephoneNumber
	 * @param taxCode
	 * @param birthDate
	 * @param hireDate
	 **/
	void saveEmployee(String name, String surname, String address, String username, char[] password, String email,
			int telephoneNumber, String taxCode, Date birthDate, Date hireDate);

}
