package view;

import java.util.ArrayList;

import javax.swing.JButton;

import controller.ProductController;
import model.Department;


/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface InsertProductView {

	/**
	 * this method works with the components of JFrame
	 * 
	 * @param index
	 * @param newButton
	 * @param departments
	 */
	public void actionEvent(int index, JButton newButton,
			ArrayList<Department> departments);

	/**
	 * this method give the list of Department
	 * 
	 * @param departments
	 */
	public void listDepartment(ArrayList<Department> departments);

	/**
	 * this method use the pattern Observer
	 * 
	 * @param controller
	 */
	public void addObserver(ProductController controller);

}
