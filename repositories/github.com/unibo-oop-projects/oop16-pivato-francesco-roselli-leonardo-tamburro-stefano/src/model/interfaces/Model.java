package model.interfaces;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import model.BalanceImpl;
import model.enumerations.*;

public interface Model {
	
	/**
	 * @return Validator to validate login data
	 */
	
	public Validator getValidator();
	
	/**
	 * @param scene of the view
	 * @return image chosen by user
	 */
	
	public Image getImage(Scene scene);
	
	/**
	 * @return default image of a user
	 */
	
	public Image getDefaultImage();
	
	/**
	 * @return Data, the class contains all the data
	 */
	
	public Data retreiveData();
	
	/**
	 * Save a user 
	 * @param user to save
	 * @return Status of operation
	 */
	
	public Status saveUser(User user);

	/**
	 * @return BalanceImpl, class contains all data relative to Balance
	 */

	public BalanceImpl retreiveBalance();
	
	/**
	 * Delete one user
	 * @param user to delete
	 * @return Status of operation
	 */
	
	public Status deleteUser(User user);
	
	/**
	 * add an employee
	 * @param employee to be added
	 * @return Status of operation
	 */
	
	public Status addEmployee(Employee employee);
	
	/**
	 * Dismiss an employee
	 * @param employee to be dismissed
	 * @return Status of Operation
	 */
	
	public Status dismissEmployee(Employee employee);
	
	/**
	 * Add a product
	 * @param product to be added
	 * @return Status of operation
	 */

	public Status addProduct(Product product);
	
	/**
	 * @return List of user
	 */
	
	public List<User> getUserList();
	
	/**
	 * Remove a product from warehouse
	 * @param product
	 * @return Status of Operation
	 */

	public Status removeProduct(Product product);
	
}
