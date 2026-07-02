package application.model.buildables.pump;

import java.util.List;

import application.model.services.Fuel;

/**
 * Interface containing all the logic to build and modify a pump inside the area.
 * @author Alessandro Mami
 * 
 */
public interface PumpManager {
    
    /**
     * Gets the pump of a specific index.
     * @param Integer index of the pump.
     * @return Object of pump's type.
     */
    Pump getPump(int i);
    
    /**
     * Gets the pump with a specific name.
     * @param String name of the pump.
     * @return Object of pump's type.
     */
    Pump getPumpByName(String name);
    
    /**
     * Gets every pump in the list.
     * @return List of pump's type.
     */
    List<Pump> getAllPumps();
    
    /**
     * Adds a pump of an area with certain caracteristics.
     * @param Main attributes: name, type and speed.
     * @param Extended attributes from BuildableImpl.
     */
    void addPump(int maxDurability, int actualDurability, int cost, int repairCost, String name, Fuel type, int speed);
    
    /**
     * Removes a pump from the area.
     * @param Object of pump's type.
     */
    void removePump(Pump pump);
    
    /**
     * Removes every pump from the area.
     */
    void removeAllPumps();	
}
