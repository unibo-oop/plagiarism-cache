package application.view.tabs.fuelsEditor;

import java.util.List;

import application.controller.tabs.FuelsEditorCtrl;
import application.model.services.Fuel;
import application.view.resources.AlertManager;

/**
 * Interface containing all the logic for the Fuels Editor tab.
 * @author Marcin Pabich
 *
 */
public interface FuelsEditor extends AlertManager  {

    //Controller
    /**
     * Set the controller for the view.
     * @param ctrl the controller
     */
    void setController(FuelsEditorCtrl ctrl);
    
    
    
    //Loading
    /**
     * Load the fuels in the combobox, clearing previous values.
     * @param fuels List of string describing avaiable fuels
     */
    void loadFuels(List<Fuel> fuels);
    
   
    //Show/hide panel
    /**
     * Show the editing panel.
     */
    void showEditingPanel();
    
    /**
     * Hide the editing panel.
     */
    void hideEditingPanel();
    
    /**
     * Tell if the editing panel is visible.
     * @return TRUE if it's visible, FALSE otherwise
     */
    boolean isEditingPanelVisible();
    
    
    //Modifying
    /**
     * Get the actual selected fuel.
     * @return String that represent the fuel selected
     */
    String getSelectedFuel();
    
    /**
     * Get the modified fuel name.
     * @return String that represent the name
     */
    String getModifyName();
    
    /**
     * Set the content of the name textfield.
     * @param name String that will be show in the textfield
     */
    void setModifyName(String name);
    
    /**
     * Get the modified fuel price.
     * @return String that represent the price
     */
    String getModifyPrice();
    
    /**
     * Set the content of the price textfield.
     * @param price String that will be show in the textfield
     */
    void setModifyPrice(String price);
    
    /**
     * Get the modified fuel whoesale price.
     * @return String that represent the whoesale price
     */
    String getModifyWhoesalePrice();
    
    /**
     * Set the content of the whoesale price textfield.
     * @param wprice String that will be show in the textfield
     */
    void setModifyWhoesalePrice(String wprice);
    
    /**
     * Get the modified color of the fuel.
     * @return String that represent the color
     */
    String getModifyColor();
    
    /**
     * Set the content of the color textfield.
     * @param color String that will be show in the textfield
     */
    void setModifyColor(String color);
    
    /**
     * Set the modifying panel visibility.
     * @param visibility TRUE if it must be visible, FALSE otherwise
     */
    void setModifyPanelVisibility(boolean visibility);
    
    
    
    
    //Adding
    /**
     * Get the fuel name.
     * @return String that represent the name
     */
    String getFuelName();
    
    /**
     * Get the fuel price.
     * @return String that represent the price
     */
    String getFuelPrice();
    
    /**
     * Get the fuel whoesale price.
     * @return String that represent the whoesale price
     */
    String getFuelWhoesalePrice();
    
    /**
     * Get the color of the fuel.
     * @return String that represent the color
     */
    String getFuelColor();
    
}
