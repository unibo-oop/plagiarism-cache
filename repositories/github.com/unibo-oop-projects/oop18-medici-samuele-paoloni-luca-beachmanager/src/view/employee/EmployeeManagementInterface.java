package view.employee;

import model.Employee;

public interface EmployeeManagementInterface {

	/**
	 * Dopo un'operazione sulla lista dipendenti notifica alla View che c'è stata una modifica
	 * @param employeeList Lista dipendenti
	 */
	void updateEmployeeList(Employee[] employeeList);
	
}
