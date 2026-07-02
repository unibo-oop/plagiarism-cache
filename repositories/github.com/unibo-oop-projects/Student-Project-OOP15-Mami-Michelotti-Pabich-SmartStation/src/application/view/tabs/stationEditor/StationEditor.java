package application.view.tabs.stationEditor;

import java.util.List;

import application.controller.tabs.StationEditorCtrl;
import application.model.buildables.area.Area;
import application.model.buildables.pump.Pump;
import application.view.resources.AlertManager;

/**
 * Interface containing all the logic for the Station Editor tab.
 * @author Marcin Pabich
 *
 */
public interface StationEditor extends AlertManager {

    
    //Controller
    /**
     * Set the controller for the class.
     * @param ctrl the controller for the class
     */
    void setController(StationEditorCtrl ctrl);
    
    
    
    //Loading and refreshing
    /**
     * Fill the comboboxes with selectible coordinates.
     * @param x Max X coordinate (0-based)
     * @param y Max Y coordinate (0-based)
     */
    void loadCoordinates(int x, int y);
    
    /**
     * Load avaiable pumps into the view.
     * @param pumps List of avaiable pumps
     */
    void loadPumps(List<Pump> pumps);
    
    /**
     * Redraw the grid with areas.
     * @param areas List of updated areas
     */
    void refreshGrid(List<Area> areas);
    
    
    
    //Disable / Enable views
    /**
     * Show the panel that permit to modifying areas, hiding other panels.
     */
    void showModifyngPanel(); 
    
    /**
     * Hide the panel that permit to modifying areas.
     */
    void hideModifyingPanel();  
    
    /**
     * Show details panel, hiding other panels.
     */
    void showDetailsPanel();
    
    /**
     * Hide details panel.
     */
    void hideDetailsPanel();    
    
    /**
     * Show the panel that permit to add new areas, hiding other panels.
     */
    void showAddingPanel();
    
    /**
     * Hide the panel that permit to add new areas.
     */
    void hideAddingPanel();
    
    /**
     * Enable the selection of the area.
     */
    void enableSelectArea();
    
    /**
     * Disable the selection of the area.
     */
    void disableSelectArea();
    
    /**
     * Change the text of the "change view" button.
     * @param s Text to view in the button
     */
    void changeButtonText(String s);
        
    /**
     * Tell if the adding panel is visible.
     * @return TRUE if it's visible, FALSE otherwise
     */
    boolean isAddingPanelVisible();
    
    /**
     * Tell if the modifying panel is visible.
     * @return TRUE if it's visible, FALSE otherwise
     */
    boolean isModifyingPanelVisible();
    
    /**
     * Tell if the details panel is visible.
     * @return TRUE if it's visible, FALSE otherwise
     */
    boolean isDetailsPanelVisible();
    
    /**
     * Tell if the area selection panel is enabled.
     * @return TRUE if it's enabled, FALSE otherwise
     */
    boolean isSelectAreaEnabled();
    
    
   
    //Adding new AREA
    /**
     * Get the X coordinate of a new area.
     * @return X coordinate of a new area.
     */
    String getXCoords();
    
    /**
     * Get the Y coordinate of a new area.
     * @return Y coordinate of a new area.
     */
    String getYCoords();  
    
    /**
     * Get the pumps of a new area.
     * @return pumps of a new area.
     */
    List<String> getPumps();
    
    
    
    //Modifying an AREA
    /**
     * Get the X coordinate of selected area.
     * @return X coordinate of selected area
     */
    String getModifyX();
    
    /**
     * Get the Y coordinate of selected area.
     * @return Y coordinate of selected area
     */
    String getModifyY();
    
    /**
     * Get the modifyied X of selected area.
     * @return modifyied X of selected area
     */
    String getModifyChangeX();
    
    /**
     * Set the content of X coordinate textfield.
     * @param x X coorinate yo show
     */
    void setModifyXChange(String x);
    
    /**
     * Get the modifyied Y of selected area.
     * @return modifyied Y of selected area
     */
    String getModifyChangeY();
    
    /**
     * Set the content of Y coordinate textfield.
     * @param y Y coorinate yo show
     */
    void setModifyYChange(String y);
    
    /**
     * Get the modifyied pumps of selected area.
     * @return modifyied pumps of selected area
     */
    List<String> getModifyPumps();
    
    /**
     * Set the modifyied pumps of selected area.
     * @param pumps modifyied pumps of selected area
     */
    void setModifyPumps(List<String> pumps);
    
    

    //Error messages
    /**
     * Allow to write an error message.
     * @param s string with the error message
     */
    void showAddErrorMessage(String s);
    
    /**
     * Allow to hide the error message.
     */
    void hideAddErrorMessage();
    
    /**
     * Allow to write an error message.
     * @param s string with the error message
     */
    void showModifyErrorMessage(String s);
    
    /**
     * Allow to hide the error message.
     */
    void hideModifyErrorMessage();
    
    /**
     * Allow to write an error during coords change.
     * @param s string with the error message
     */
    void showModifyCoordsMessage(String s);
    
    /**
     * Allow to hide the error message.
     */
    void hideModifyCoordsMessage();
    
    
}
