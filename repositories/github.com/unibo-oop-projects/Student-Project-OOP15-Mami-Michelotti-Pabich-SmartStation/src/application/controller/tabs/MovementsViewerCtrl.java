package application.controller.tabs;

import application.view.tabs.movementsViewer.MovementsViewer;

/**
 * Interface that contain all methods needed to manage the movements viewer controller.
 * @author Matteo Michelotti
 */
public interface MovementsViewerCtrl {

    /**
     * Set the view of the controller.
     * @param movementsViewer the view for the class
     */
    void setView(MovementsViewer movementsViewer);
    
    /**
     * Load the configuration of movements viewer tab.
     */
    void loadData();
    
    /**
     * Load the current balance.
     */
    void loadBalance();
    
    /**
     * Apply the filter selected.
     */
    void applyFilter();
    
    /**
     * Adding a movement.
     */
    void addMovement();
}
