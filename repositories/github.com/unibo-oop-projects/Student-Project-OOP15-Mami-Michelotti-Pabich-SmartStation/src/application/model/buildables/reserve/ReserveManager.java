package application.model.buildables.reserve;

import java.util.List;

import application.model.services.Fuel;

/**
 * Interface containing all the logic to build and modify a reserve of fuel.
 * @author Alessandro Mami
 * 
 */
public interface ReserveManager{
    
    /**
     * Gets the reserve of a specific fuel.
     * @param Object of fuel's type.
     * @return Object of reserve's type.
     */
    Reserve getReserve(Fuel type);
    
    /**
     * Gets every reserve in the list.
     * @return List of reserve's type.
     */
    List<Reserve> getAllReserves();
    
    /**
     * Adds a reserve of fuel with certain caracteristics.
     * @param Main attributes: type and capacity.
     * @param Extended attributes from BuildableImpl.
     */
    void addReserve(int maxDurability, int actualDurability, int cost, int repairCost, Fuel type, int capacity, int actualCapacity);
    
    /**
     * Removes a reserve of fuel.
     * @param Object of reserve's type.
     */
    void removeReserve(Reserve reserve);
    
    /**
     * Removes every reserve of fuel.
     */
    void removeAllReserves();
}
