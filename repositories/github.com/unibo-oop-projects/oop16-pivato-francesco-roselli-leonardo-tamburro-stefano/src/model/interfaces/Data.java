package model.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import model.BalanceImpl;

public interface Data {

	/**
	 *
	 * @return a list of User
	 */

	public List<User> getUsersList();

	/**
	 * Add new User to the User's List
	 * @param user
	 */

	public void addToUserList(User user);

	/**
	 *
	 * @return a map that contains login data
	 */

	public Map<String, String> getLoginData();


	/**
	 * Add the login Data to the relative map
	 * @param map
	 */
	public void addToLoginData(Map<String, String> map);

	/**
	 *
	 * @return List of product in warehouse
	 */

	public List<Product> getProductList();

	/**
	 *
	 * @return the list of employees
	 */

	public List<Employee> getEmployees();

	/**
	 *
	 * @return A Map in which there is a list of product sold for every date
	 */

	public Map<LocalDate, List<Product>> getProductSold();
	
	/**
	 * 
	 * @return the instance of the class BalanceImpl
	 */
	
	public BalanceImpl getBalance();
	
	/**
	 * 
	 * @return A map with Product and  its quantity remained in whareouse
	 */
	
	public Map<Product, Integer> getProductInWharehouse();

}
