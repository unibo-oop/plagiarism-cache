package application.view.tabs.pumpsEditor;

import java.util.List;

import application.controller.tabs.PumpsEditorCtrl;
import application.model.buildables.pump.Pump;
import application.model.services.Fuel;
import application.view.resources.AlertManager;

/**
 * Interface containing all the logic for the Pumps Editor tab.
 * @author Marcin Pabich
 *
 */
public interface PumpsEditor extends AlertManager {

    //The controller
    
    /**
     * Set the controller for the view.
     * @param ctrl the controller
     */
    void setController(PumpsEditorCtrl ctrl);
    
    
    
    //Loading methods
    
    /**
     * Load names of pumps into the comboboxes.
     * @param pumps List of the pumps
     */
    void loadPumps(List<Pump> pumps);
    
    /**
     * Load names of fuels into the comboboxes.
     * @param fuels List of the fuels
     */
    void loadFuels(List<Fuel> fuels);
    
    
    
    //Method for modifying a pump
    
    /**
     * Get the selected pump for the editing.
     * @return String representing the value
     */
    String getModifySelectedPump();
    
    /**
     * Get the modified fuel of the pump.
     * @return String representing the value
     */
    String getModifyFuelType();
    
    /**
     * Set the value of modified fuel combobox.
     * @param fuelType String representing the value
     */
    void setModifyFuelType(String fuelType);
    
    
    /**
     * Get the modified speed of the pump.
     * @return String representing the value
     */
    String getModifySpeed();
    
    /**
     * Set the value of modified speed textfield.
     * @param speed String representing the value
     */
    void setModifySpeed(String speed);
    
    /**
     * Get the modified durability of the pump.
     * @return String representing the value
     */
    String getModifyDurability();
    
    /**
     * Set the value of modified durability textfield.
     * @param durability String representing the value
     */
    void setModifyDurability(String durability);
    
    /**
     * Get the modified price of the pump.
     * @return String representing the value
     */
    String getModifyPrice();
    
    /**
     * Set the value of modified price textfield.
     * @param price String representing the value
     */
    void setModifyPrice(String price);
    
    /**
     * Get the modified repair cost.
     * @return String representing the value
     */
    String getModifyRepairCost();
    
    /**
     * Set the value of modified repair price textfield.
     * @param repairPrice String representing the value
     */
    void setModifyRepairCost(String repairPrice);
    
    
    
    //...for repairing a pump
    /**
     * Gets the selected pump to repair.
     * @return String containing the value
     */
    String getRepairSelectedPump();
    
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
     * @param current current value
     * @param max Max value
     */
    void setRepairQuantities(String current, String max);
    
    
    
    //...for creating a new pump
    
    /**
     * Get the fuel of the pump.
     * @return String representing the value
     */
    String getFuelType();
    
    /**
     * Get the speed of the pump.
     * @return String representing the value
     */
    String getSpeed();
    
    /**
     * Get the durability of the pump.
     * @return String representing the value
     */
    String getDurability();
    
    /**
     * Get the price of the pump.
     * @return String representing the value
     */
    String getPrice();
    
    /**
     * Get the repair cost.
     * @return String representing the value
     */
    String getRepairCost();

}
