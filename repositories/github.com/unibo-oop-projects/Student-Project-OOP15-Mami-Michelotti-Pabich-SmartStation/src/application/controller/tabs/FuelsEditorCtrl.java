package application.controller.tabs;

import java.util.List;
import application.model.services.Fuel;
import application.view.tabs.fuelsEditor.FuelsEditor;

/**
 * Interface that contain all methods needed to manage the fuels editor controller.
 * @author Matteo Michelotti
 */
public interface FuelsEditorCtrl {

    /**
     * Set the view of the controller.
     * @param fuelsEditor the view for the class
     */
    void setView(FuelsEditor fuelsEditor);
    
    /**
     * Load configuration for fuels editor tab.
     * @param fuels list of fuel
     */
    void loadData(List<Fuel> fuels);
    
    /**
     * selection of the fuel and load the dates.
     */
    void select();
    
    /**
     * Change the name of fuel selected.
     */
    void changeName();
    
    /**
     * Change the price of fuel selected.
     */
    void changePrice();
    
    /**
     * Change the wholesale price of fuel selected.
     */
    void changeWPrice();
    
    /**
     * Change the color of fuel selected.
     */
    void changeColor();
    
    /**
     * Add new fuel.
     */
    void addFuel();
    
    /**
     * Delete the fuel selected and the reserve and all pump whit this fuel.
     */
    void deleteFuel();
}
