package view.interfaces;

import java.awt.event.ActionListener;

public interface AddRemoveView {

	/**
	 * The ActionListener for the add button.
	 * 
	 * @param insertListener
	 */
    void addButtonListener(ActionListener insertListener);

    /**
     * The ActionListener for the remove button.
     * 
     * @param removeListener
     */
    void removeButtonListener(ActionListener removeListener);

    /**
     * The ActionListener for the back button.
     * 
     * @param backListener
     */
    void backButtonListener(ActionListener backListener);

    /**
     * Displays an error when confirm button is clicked and not all fields are compiled.
     * 
     * @param error
     */
    void displayErrorMessage(String error);

    /**
     * This method adds items to the initially empty JList.
     * 
     * @param element
     */
    void addItemsToList(Object element);

    /**
     * Return the object of the selected item from the JList.
     * 
     * @return object
     */
    Object getSelectedObjectOfList();

    /**
     * Return the index of the selected item from the JList.
     * 
     * @return index
     */
    int getSelectedIndexOfList();

    /**
     * This method is used to dispose of the current JFrame and pass onto the next one.
     */
    void close();
}