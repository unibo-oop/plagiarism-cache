package view;

import controller.DepartmentController;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface InsertDepartment {

	/**
	 * this method use the pattern Observer
	 * 
	 * @param controller
	 */
	public void addObserver(DepartmentController controller);

}
