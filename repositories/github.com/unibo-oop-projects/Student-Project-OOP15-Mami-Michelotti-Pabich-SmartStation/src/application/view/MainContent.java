package application.view;

import application.controller.MainController;
import application.view.resources.AlertManager;
import application.view.tabs.fuelsEditor.FuelsEditor;
import application.view.tabs.movementsViewer.MovementsViewer;
import application.view.tabs.overview.Overview;
import application.view.tabs.pumpsEditor.PumpsEditor;
import application.view.tabs.reservesEditor.ReservesEditor;
import application.view.tabs.stationEditor.StationEditor;

/**
 * Interface with all methods needed for maincontent management.
 * @author Marcin Pabich
 */
public interface MainContent extends AlertManager {

    /**
     * Set the controller of the MainContent and of the all sub-views.
     * @param ctrl the controller for the classes
     */
    void setController(MainController ctrl);
    
    /**
     * Get the controller of the MainContent.
     * @return controller of the class
     */
    MainController getController();
    
    

    /**
     * Get the Overview tab reference.
     * @return Overview tab reference
     */
    Overview getOverviewTab();
    
    /**
     * Get the Station Editor tab reference.
     * @return Station Editor tab reference
     */
    StationEditor getStationEditorTab();
       
    /**
     * Get the Fuels Editor tab reference.
     * @return Fuels Editor tab reference
     */
    FuelsEditor getFuelsEditorTab();
    
    /**
     * Get the Pumps Editor tab reference.
     * @return Pumps Editor tab reference
     */
    PumpsEditor getPumpsEditorTab();
    
    /**
     * Get the Reserves Editor tab reference.
     * @return Reserves Editor tab reference
     */
    ReservesEditor getReservesEditorTab();
    
    /**
     * Get the Movements Viewer tab reference.
     * @return Movements Viewer tab reference
     */
    MovementsViewer getMovementsViewerTab();
     
    
}
