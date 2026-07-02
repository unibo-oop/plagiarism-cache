package view;

import java.util.List;

import model.interfaces.ILesson;

/**
 * 
 * Interface responsible for the principal JFrame of the program.
 *
 */

public interface IView {
    
    /**
     * Method which pass the list of ILesson to the table in the view.
     * @param type Indicates the type of the list passed.
     * @param list List of ILesson.
     */
    
    void addData(final int type, List<ILesson> list);
    
    /**
     * Method which change the view in edit mode, unlocking the buttons of "Keep", "Delete" and "Done",
     * disabling the menu and making the cell of the table selectable.
     * @param set Says if the view change in edit mode or not.
     */
    
    void editMode(final boolean set);
    
    /**
     * Method which update the table's types of view.
     */
    
    void refreshSearchList();
    
    /**
     * Method which shows a JOptionPane indicating a message understandable for the user for every error which may occur.
     * @param message The message of error.
     */
    
    void errorDialog(final String message);
    
    /**
     * Method which converts the element present in the table into a HSSFWorkbook
     * (an object containing the information for an excel file) and pass this workbook to the controller.
     */
    
    void exportData();

}
