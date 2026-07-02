package view;

import javax.swing.JLabel;

import controller.DepartmentController;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface ModifyDepartmentView {

	/**
	 * this method use the pattern Observer
	 * 
	 * @param controller
	 */
	public void addObserver(DepartmentController controller);

	/**
	 * this method use set the data of the gui
	 * 
	 * @param name
	 * @param maxQuantity
	 * @param code
	 * @param nameDepartment
	 */
	public void setData(String name, int maxQuantity, int code, JLabel nameDepartment);

}
