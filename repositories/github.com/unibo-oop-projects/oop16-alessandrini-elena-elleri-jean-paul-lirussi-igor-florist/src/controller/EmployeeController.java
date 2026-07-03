package controller;

import java.util.List;
import java.util.Map;

import model.Employee;

/**
 * This interface is used for add, remove and modify a {@link Employee}
 * to the application and view all current {@link Employee} 
 * that works in the {@link Greenhouse}.
 *
 */
public interface EmployeeController {

    /**
     * This method add a {@link Employee}.
     * 
     * @param empl
     *      Set {@link Employee} to add
     */
    void addEmployee(Map<EmployeeInfo, String> empl);

    /**
     * This method is used for remove a selected {@link Employee}.
     *
     */
    void removeEmployee();

    /**
     * This method is used for select a {@link Employee}.
     * 
     * @param pos
     *      position of the {@link Employee} in the table
     */
    void selectEmployee(int pos);

    /**
     * This method is used for confirm the add of a new {@link Employee}.
     * 
     * @param empl
     *      {@link Employee} to confirm.
     */
    void confirm(Map<EmployeeInfo, String> empl);

    /**
     * This method is used for get all the {@link Employee}.
     * 
     * @return List<{@link Employee}>
     */
    List<Employee> getAllEmployee();

    /**
     * This method get all {@link Employee} as a table.
     * 
     * @return Object[][] table
     */
    Object[][] getEmployeeTable();
}
