package application.controller.tabs;

import java.util.List;
import application.model.buildables.reserve.Reserve;
import application.model.services.Fuel;
import application.view.tabs.reservesEditor.ReservesEditor;

/**
 * Interface that contain all methods needed to manage the reserves editor controller.
 * @author Matteo Michelotti
 */
public interface ReservesEditorCtrl {

    /**
     * Set the view of the controller.
     * @param reservesEditor the view for the class
     */
    void setView(ReservesEditor reservesEditor);
    
    /**
     * Load configuration for reserves editor tab.
     * @param fuels list of fuel
     * @param reserves list of reserve
     */
    void loadData(List<Fuel> fuels, List<Reserve> reserves);
    
    /**
     * Selected the reserve for modify dates.
     */
    void selectEdit();
    
    /**
     * Change the type of reserve selected.
     */
    void changeFuelType();
    
    /**
     * Change the capacity of reserve selected.
     */
    void changeCapacity();
    
    /**
     * Change the repair cost of reserve selected.
     */
    void changeRepairCost();
    
    /**
     * Change the cost of reserve selected.
     */
    void changePrice();
    
    /**
     * Change the durability of reserve selected.
     */
    void changeDurability();
    
    /**
     * Select the reserve for refill.
     */
    void selectRefill();
    
    /**
     * Refill the quantity selected for reserve select.
     */
    void refill();
    
    /**
     * Select the reserve for repair.
     */
    void selectRepair();
    
    /**
     * Repair the quantity selected for reserve select.
     */
    void repair();
    
    /**
     * Add new reserve for the station.
     */
    void addReserve();
    
    /**
     * Delete the reserve selected.
     */
    void deleteReserve();
}
