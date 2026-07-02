package model.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import model.EmployeeImpl;
import model.EmployeeModel;

/**
 * This class represents the employees of the library list. This class is
 * composed of a list and the processing methods.
 * 
 * @author mattia.Rovinelli
 *
 */
public class EmployeesImpl implements EmployeesModel {

	private Map<Integer, EmployeeModel> employees;

	public EmployeesImpl() {
		employees = new HashMap<Integer, EmployeeModel>();
	}

	public void updateEmployees(Map<Integer, EmployeeModel> employees) {
		this.employees = employees;
	}

	public Map<Integer, EmployeeModel> getEmployees() {
		return this.employees;
	}

	public Boolean logged(String username, char[] password) {
		boolean exist = false;
		Iterator<Entry<Integer, EmployeeModel>> it = employees.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, EmployeeModel> pair = (Entry<Integer, EmployeeModel>) it.next();
			if (pair.getValue().getUsername().equals(username)
					&& String.valueOf(pair.getValue().getPassword()).equals(String.valueOf(password)))
				exist = true;
		}
		return exist;

	}

	public void addNewEmployee(EmployeeModel employee) {
		int c = this.employees.size();
		this.employees.put(c, employee);
	}

	@Override
	public EmployeeModel searchEmployee(String username) {
		Iterator<Entry<Integer, EmployeeModel>> it = employees.entrySet().iterator();
		EmployeeModel e = new EmployeeImpl();
		while (it.hasNext()) {
			Map.Entry<Integer, EmployeeModel> pair = (Entry<Integer, EmployeeModel>) it.next();
			if (pair.getValue().getUsername().equals(username)) {
				e = pair.getValue();
			}
		}
		return e;

	}
}
