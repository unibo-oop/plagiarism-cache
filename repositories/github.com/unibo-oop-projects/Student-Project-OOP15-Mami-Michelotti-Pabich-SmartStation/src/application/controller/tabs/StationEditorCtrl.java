package application.controller.tabs;

import java.util.List;
import application.model.buildables.area.Area;
import application.model.buildables.pump.Pump;
import application.view.tabs.stationEditor.StationEditor;

/**
 * Interface that contains all methods needed to manage the station editor controller.
 * @author Matteo Michelotti
 */
public interface StationEditorCtrl {

    /**
     * Set the view of the controller.
     * @param stationEditor the view for the class
     */
    void setView(StationEditor stationEditor);
    
    /**
     * Load configuration for station editor tab.
     * @param x coordinates
     * @param y coordinates
     * @param pumps list pump for combo box
     * @param areas list area for grid
     */
    void loadData(int x, int y, List<Pump> pumps, List<Area> areas);
    
    /**
     * Select the area and load data.
     */
    void selectionConfirm();
    
    /**
     * Change the position of area.
     */
    void changePosition();
    
    /**
     * Confirm the change of pumps of the area.
     */
    void confirmPumps();
    
    /**
     * Create new area.
     */
    void insertArea();
    
    /**
     * Delete the area selected.
     */
    void removeArea();
    
    /**
     * Switch between add and modify panel.
     */
    void switchPanel();
}
