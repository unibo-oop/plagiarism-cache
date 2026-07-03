package view;

import java.util.List;

import model.interfaces.Employee;

public interface Administration {
	
	/**
	 * Fill the table with a list of employees
	 * @param data
	 */
	void setData(final List<Employee> data);

}
