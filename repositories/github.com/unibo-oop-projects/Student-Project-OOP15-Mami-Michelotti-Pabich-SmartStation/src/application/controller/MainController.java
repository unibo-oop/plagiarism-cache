package application.controller;

import application.controller.tabs.FuelsEditorCtrl;
import application.controller.tabs.MovementsViewerCtrl;
import application.controller.tabs.OverviewCtrl;
import application.controller.tabs.PumpsEditorCtrl;
import application.controller.tabs.ReservesEditorCtrl;
import application.controller.tabs.StationEditorCtrl;
import application.model.Station;
import application.view.MainContent;

/**
 * Interface that contain all methods needed to manage the main controller.
 * @author Matteo Michelotti
 */
public interface MainController {

    /**
     * Get the fuels editor controller.
     * @return the overview controller
     */
    FuelsEditorCtrl getFuelsEditorController();
    
    /**
     * Get the movements viewer controller.
     * @return the overview controller
     */
    MovementsViewerCtrl getMovementsViewerController();
    
    /**
     * Get the overview controller.
     * @return the overview controller
     */
    OverviewCtrl getOverviewController();
    
    /**
     * Get the pumps editor controller.
     * @return the station editor controller
     */
    PumpsEditorCtrl getPumpsEditorController();
    
    /**
     * Get the reservers editor controller.
     * @return the station editor controller
     */
    ReservesEditorCtrl getReservesEditorController();
    
    /**
     * Get the station editor controller.
     * @return the station editor controller
     */
    StationEditorCtrl getStationEditorController();
    
    /**
     * Set model of the controller.
     * @param station the model for the class
     */
    void setModel(Station station);
    
    /**
     * Get the model of the controller.
     * @return main model
     */
    Station getModel();
    
    /**
     * Set the view of the controller.
     * @param mainContent the view for the class
     */
    void setView(MainContent mainContent);
    
    /**
     * Reconfiguration of all tabs.
     */
    void reconfiguration();
    
    /**
     * Save dates in the file type xml.
     */
    void saveConfiguration();
    
    /**
     * Load the file and store in the model.
     */
    void loadConfiguration();
}
