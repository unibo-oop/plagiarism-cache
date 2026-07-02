package application.view.tabs.overview;

import java.util.List;

import application.controller.tabs.OverviewCtrl;
import application.model.buildables.area.Area;
import application.view.resources.AlertManager;

/**
 * Interface that contains all methods to correct use of the overview tab.
 * @author Marcin Pabich
 */
public interface Overview extends AlertManager {

    /**
     * Set the controller for the overview TAB.
     * @param controller - an OverviewCtrl object that will be the controller of this tab
     */
    void setController(OverviewCtrl controller);
	
    
       
    /**
     * Add the reserve on overview.
     * @param name name of the fuel
     * @param price price of that fuel
     * @param remain remaining value of the reserve
     * @param max max value of the reserve
     * @param progress value of progress, 0.0 is empty, 1.0 is full
     */
    void addReserveStatus(String name, String price, String remain, String max, Double progress);
    
    /**
     * Update the reserve status with values.
     * @param name name of the fuel
     * @param price price of that fuel
     * @param remain remaining value of the reserve
     * @param max max value of the reserve
     * @param progress value of progress, 0.0 is empty, 1.0 is full
     */
    void refreshReserveStatus(String name, String price, String remain, String max, Double progress);
    
    /**
     * Update the reserve status with values.
     * @param name name of the fuel
     * @param remain remaining value of the reserve
     * @param progress value of progress, 0.0 is empty, 1.0 is full
     */
    void refreshReserveStatus(String name, String remain, Double progress);
    
    /**
     * Remove all the reserves from the bar.
     */
    void removeReserves();
    
    
    
    /**
     * Add element to log list.
     * @param element String element to add
     */
    void addElementToList(String element);
    
    
    /**
     * Clear the list from the elements.
     */
    void clearList();
    
    
      
    /**
     * Redraw the grid with areas.
     * @param areas List of updated areas
     */
    void refreshGrid(List<Area> areas);
    
}
