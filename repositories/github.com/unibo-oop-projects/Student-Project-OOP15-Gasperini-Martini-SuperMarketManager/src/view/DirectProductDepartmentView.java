package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import controller.DirectProductDepartmentController;
import model.Department;


/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface DirectProductDepartmentView {

	/**
	 * this method works with the component of JFrame
	 * 
	 * @param newButton
	 * @param departments
	 * @param modifyDepartment
	 * @param nameDepartment
	 * @param directProduct
	 */
	public void actionEvent(JButton deleteDepartment, Department departments, JButton modifyDepartment,
			JLabel nameDepartment, JButton directProduct,DirectProductDepartmentViewImpl view);

	/**
	 * this method give the list of Departments
	 * 
	 * @param departments
	 */
	public void listDepartment(ArrayList<Department> departments);

	/**
	 * this method use the pattern Observer
	 * 
	 * @param controller
	 */
	public void addObserver(DirectProductDepartmentController controller);
	
	/**
	 * This method set the display of the view
	 * 
	 * @param department
	 */
	public void setPanel(ArrayList<Department> department);

}
