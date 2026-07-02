package controller;

import model.Department;

import java.util.ArrayList;

import javax.swing.*;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface DirectProductDepartmentController {

	/**
	 * this method delete a department
	 * 
	 * @param department
	 */
	void deleteDepartment(Department department);

	/**
	 * this method modify a department
	 * 
	 * @param departments
	 * @param nameDepartment
	 */
	void setModifyController(Department departments, JLabel nameDepartment);

	/**
	 * this method set the controller of modify department
	 * 
	 * @param department
	 */
	void setModifyProductController(Department department);
	
	/** 
	 * @return
	 */
	public ArrayList<Department> getListDepartmentView();

	/**
	 * exit
	 */
	void quit();

}
