package controller;

import javax.swing.JLabel;
import model.SuperMarket;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface DepartmentController {

	/**
	 * this method insert new Department
	 * 
	 * @param name
	 * @param codeRepart
	 * @param max
	 * @return 
	 * 
	 * @return String (String name, int codeRepart, int max)
	 */
	 void insertDepartement(String name, int max, int codeRepart);

	/**
	 * this method return the supermarket
	 * 
	 * @return
	 */
	SuperMarket getMarket();

	/**
	 * this method modify the department
	 * 
	 * @param name
	 * @param max
	 * @param code
	 * @param displayNameDepartment
	 * 
	 * @return String (String name, int max, int code, JLabel
	 *         displayNameDepartment)
	 */
	String modifyDepartment(String name, int max, int code,
			JLabel displayNameDepartment);

	/**
	 * this method check the name of all Department
	 * 
	 * @param name
	 * 
	 * @return boolean(String name);
	 */
	boolean checkName(String name);

	/**
	 * this method check the code of all Department
	 * 
	 * @param code
	 * 
	 * @return boolean(int code)
	 */
	boolean checkCode(int code);

	/**
	 * exit from InsertDepartment
	 */
	void quit();

	/**
	 * exit from ModifyDepartment
	 */
	void quitModify();
}
