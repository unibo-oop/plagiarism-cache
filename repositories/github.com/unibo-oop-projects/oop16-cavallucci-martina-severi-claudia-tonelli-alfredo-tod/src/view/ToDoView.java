package view;
/**
 * Interface for the to do view.
 *
 */
public interface ToDoView extends View {

    /**
     * .
     * @param data
     *          the table of the to do.
     */
    void rebuildTable(Object[][] data);

}
