package application.view.controls.areaViewer;

import java.util.List;

import application.model.buildables.pump.Pump;

/**
 * Interface that contains all methods to manage the area.
 * @author Marcin Pabich
 */
public interface AreaViewer {
        
    /** 
     * Add the pumps to the area.
     * @param pumps - the List of pumps of pumps that will be added
     */
    void addPumps(List<Pump> pumps);
        
    /** 
     * Remove the pumps from the area.
     */
    void removePumps();
                        
    /**
     * Set the area to "occupied" status.
     */
    void setOccupied();
        
    /**
     * Set the area to "free" status, removing a car.
     */
    void setFree();
                
}
