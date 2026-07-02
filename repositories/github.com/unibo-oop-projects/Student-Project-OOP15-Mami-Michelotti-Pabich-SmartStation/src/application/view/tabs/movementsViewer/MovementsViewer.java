package application.view.tabs.movementsViewer;

import java.util.List;

import application.controller.tabs.MovementsViewerCtrl;
import application.view.resources.AlertManager;

/**
 * Interface containing all the logic for the Movements Viewer tab.
 * @author Marcin Pabich
 *
 */
public interface MovementsViewer extends AlertManager  {

    /**
     * Set the controller for the view.
     * @param ctrl the controller
     */
    void setController(MovementsViewerCtrl ctrl);
    
    
    /**
     * Load avaiable filters to the combobox.
     * @param filters List of the filters
     */
    void loadFilters(List<String> filters);
    
    /**
     * Gets the filter of the combobox.
     * @return String representing the filter
     */
    String getFilter();
    
    /**
     * Add a single element to the list.
     * @param element Element to add
     */
    void addElementToList(String element);
    
    /**
     * Add an entire list of elements to the list, specifying if clear previous.
     * @param elements List of elements to add
     * @param clear TRUE if clear previous | FALSE will not clear previous values
     */
    void addAllElementsToList(List<String> elements, boolean clear);
    
    /**
     * Clear the entire list.
     */
    void clearList();
    
    
    
    /**
     * Get the description from the textbox.
     * @return String that represent that value
     */
    String getDescription();
    
    /**
     * Get the money earned/lost from the textbox.
     * @return String that represent that value
     */
    String getMoney();
    
    /**
     * Set the current balance to show.
     * @param value String that represent current balance
     */
    void setCurrentBalance(String value);

}
