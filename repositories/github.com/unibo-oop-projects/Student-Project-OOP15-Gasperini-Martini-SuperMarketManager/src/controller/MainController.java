package controller;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface MainController {

	/**
	 * this method create insertDepartmentView
	 */
	void insertDepartmentView();

	/**
	 * this method create insertProductView
	 */
	void insertProductView();

	/**
	 * this method create the DirectProductDepartmentView
	 */
	public void directProductDepartment();

	/**
	 * this method create loginView
	 * 
	 * @param username
	 * @param password
	 * 
	 * @return boolean(String username, String password)
	 */
	boolean logIn(String username, String password);
	
	
	/**this method update the file on close of application
	 * 
	 */
	public void setFile();

}
