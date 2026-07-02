package model.core;

import java.util.Map;

import model.EmployeeModel;

public interface EmployeesModel {
	/**
	 * This method updates the map of employees. This method takes as input a
	 * map of employee.
	 * 
	 * @param employees
	 */
	public void updateEmployees(Map<Integer, EmployeeModel> employees);

	/**
	 * This method return a map of employees
	 * 
	 * @return map
	 */
	public Map<Integer, EmployeeModel> getEmployees();

	/**
	 * this method search in the list an employee and return true or false if
	 * exsist or not
	 * 
	 * @param username
	 * @param password
	 * @return true or false
	 */
	public Boolean logged(String username, char[] password);

	/**
	 * this method add a new employee in the map of employee
	 * 
	 * @param employee
	 */
	public void addNewEmployee(EmployeeModel employee);

	/**
	 * This method returns the employee tried
	 * 
	 * @param username
	 * @return employee
	 */
	public EmployeeModel searchEmployee(String username);
}
