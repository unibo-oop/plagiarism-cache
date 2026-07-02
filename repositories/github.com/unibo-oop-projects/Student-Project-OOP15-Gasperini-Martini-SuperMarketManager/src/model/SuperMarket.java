package model;

import java.util.ArrayList;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface SuperMarket {

	/**
	 * this method add a new Department in the Supermarket
	 * 
	 * @param department
	 */
	public void addDepartment(Department department);

	/**
	 * this method return the number of Department in Supermarket
	 * 
	 * @return
	 */
	public int getNumberDepartment();

	/**
	 * this method return the list of Department in Supermarket
	 * 
	 * @return
	 */
	public ArrayList<Department> getListDepartment();

	/**
	 * this method delete a Department in Supermarket
	 * 
	 * @param department
	 */
	public void deleteDepartment(Department department);

	/**
	 * this method return the file with Department in Supermarket
	 * 
	 * @return
	 */
	ArrayList<Department> getListDepartmentFile();

	/**
	 * this method works with the file of Department
	 */
	public void insertDepartmentFile();

	/**
	 * this method works with the file of Log-In
	 * 
	 * @param username
	 * @param password
	 * 
	 * @return boolean(String username, String password)
	 */
	public boolean logIn(String username, String password);

}
