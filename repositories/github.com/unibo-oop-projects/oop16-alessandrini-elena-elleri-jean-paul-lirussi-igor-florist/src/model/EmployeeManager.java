package model;

import java.util.Set;

/**
 * manager interface for the employees.
 */
public interface EmployeeManager {

    /**
     * adds employee.
     * @param emp employee to add
     * @return if the action is OK
     */
    boolean add(Employee emp);

    /**
     * removes employee.
     * @param emp employee to remove
     * @return if the action is ok
     */
    boolean remove(Employee emp);

    /**
     * gets all employee.
     * @return the set of employee.
     */
    Set<Employee> getAll();

    /**
     * sets all employee.
     * @param set the set of employee.
     * @return if the action is ok
     */
    boolean setAll(Set<Employee> set);

    /**
     * gets employee.
     * @param emp the employee.
     * @return the employee.
     */
    Employee getEmployee(Employee emp);

    /**
     * number employees.
     * @return the number of employees
     */
    int numEmp();

}
