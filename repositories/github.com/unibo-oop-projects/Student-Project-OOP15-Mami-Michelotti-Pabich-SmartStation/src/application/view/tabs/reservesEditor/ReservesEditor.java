package application.view.tabs.reservesEditor;

import java.util.List;

import application.controller.tabs.ReservesEditorCtrl;
import application.model.buildables.reserve.Reserve;
import application.model.services.Fuel;
import application.view.resources.AlertManager;

/**
 * Interface containing all the logic for the Reserves Editor tab.
 * @author Marcin Pabich
 *
 */
public interface ReservesEditor extends AlertManager  {

    /**
     * Set the controller for the class.
     * @param ctrl the controller for the class
     */
    void setController(ReservesEditorCtrl ctrl);
    
    
    
    //Loading methods   
    /**
     * Load avaiable reserves into the comboboxes.
     * @param reserves List of strings that represents avaiable reserves
     */
    void loadReserves(List<Reserve> reserves);
    
    /**
     * Load avaiable fuels into the comboboxes.
     * @param fuels List of strings that represents avaiable fuels
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
    
    
    
    //Getters and setters for modifying
    /**
     * Get te vaule of the selected reserve.
     * @return String containing the value
     */
    String getModifyReserve();
    
    /**
     * Get te vaule of the modifyied fuel textfield.
     * @return String containing the value
     */
    String getModifyFuel();
    
    /**
     * Set the value of the modifyied fuel textfield.
     * @param fuel String containing the value
     */
    void setModifyFuel(String fuel);
    
    /**
     * Get te vaule of the modifyied capacity textfield.
     * @return String containing the value
     */
    String getModifyCapacity();
    
    /**
     * Set the value of the modifyied capaciy textfield.
     * @param capacity String containing the value
     */
    void setModifyCapacity(String capacity);
    
    /**
     * Get te vaule of the modifyied price textfield.
     * @return String containing the value
     */
    String getModifyPrice();
    
    /**
     * Set the value of the modifyied price textfield.
     * @param price String containing the value
     */
    void setModifyPrice(String price);
    
    /**
     * Get the value of the modifyied durability textfield.
     * @return String containing the value
     */
    String getModifyDurability();
    
    /**
     * Set the value of the modifyied durability textfield.
     * @param durability String containing the value
     */
    void setModifyDurability(String durability);
    
    /**
     * Get the value of the modifyied repair cost textfield.
     * @return String containing the value
     */
    String getModifyRepairCost();
    
    /**
     * Set the value of the modifyied repair cost textfield.
     * @param repairCost String containing the value
     */
    void setModifyRepairCost(String repairCost);
    
    
    
    //Refill methods
    /**
     * Gets the selected reserve to refiil.
     * @return String containing the value
     */
    String getRefillReserve();
    
    /**
     * Get the refill value of the textfield.
     * @return String that represent the value
     */
    String getRefill();
    
    /**
     * Set the text that shows the current and max values.
     * @param current Current value
     * @param max Max value
     */
    void setRefillQuantities(String current, String max);
    
    
    
    //Repair methods
    /**
     * Gets the selected reserve to repair.
     * @return String containing the value
     */
    String getRepairReserve();
    
    /**
     * Get the repair value of the slider.
     * @return Double from 0 to 100 that represents the value
     */
    Double getRepairValue();
    
    /**
     * Set the current selected value of repairing.
     * @param value String that represent the value of the slider.
     */
    void setRepairValue(String value);
    
    /**
     * Set the text that shows the current and max values.
     * @param current Current value
     * @param max Max value
     */
    void setRepairQuantities(String current, String max);
    
    
    
    //Adding methods
    /**
     * Get te vaule of the fuel textfield.
     * @return String containing the value
     */
    String getFuel();
    
    /**
     * Get te vaule of the capacity textfield.
     * @return String containing the value
     */
    String getCapacity();
    
    /**
     * Get te vaule of the price textfield.
     * @return String containing the value
     */
    String getPrice();
    
    /**
     * Get the value of the durability textfield.
     * @return String containing the value
     */
    String getDurability();
    
    /**
     * Get the value of the repair cost textfield.
     * @return String containing the value
     */
    String getRepairCost();  
}
