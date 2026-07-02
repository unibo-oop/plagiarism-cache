package view.employee.subpanel;

import model.Employee;

public interface EmployeeListConsumer {

	/**
	 * Aggiorna la lista dei dipendenti
	 * @param employeeList Lista dipendenti
	 */
	void updateEmployeeList(Employee[] employeeList);
}
