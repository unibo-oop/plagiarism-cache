package view;

/**
 * interface of the view Employee.
 *
 */
public interface EmployeesView {

    /**
     * used when the table undergoes a change.
     * 
     * @param info contains each employee's information
     */
    void rebuildTable(Object[][] info);

    /**
     * resets the jtextfields.
     */
    void resetTextFields();
}