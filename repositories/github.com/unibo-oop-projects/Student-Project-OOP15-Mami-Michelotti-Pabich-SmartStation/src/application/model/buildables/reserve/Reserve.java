package application.model.buildables.reserve;

import application.model.buildables.Buildable;
import application.model.services.Fuel;

/**
 * Interface containing all the logic of a Reserve extended by Buildable.
 * @author Alessandro Mami
 *
 */
public interface Reserve extends Buildable{

    /**
     * Gets the type of a pump.
     * @return Object of fuel's type.
     */
    Fuel getType();
    
    /**
     * Sets the fuel type.
     * @param Object of fuel's type.
     */
    void setType(Fuel type);
    
    /**
     * Gets the capacity of a pump.
     * @return Integer of reserve's capacity.
     */
    int getCapacity();

    /**
     * Sets the fuel capacity.
     * @param Integer of reserve's capacity.
     */
    void setCapacity(int capacity);
    
    /**
     * Gets the remainings of a pump.
     * @return Integer of reserve's remaining.
     */
    int getRemaining();
    
    /**
     * Sets the remainings of a pump.
     * @param Integer of reserve's remianing.
     */
    void setRemaining(int remaining);
    
    /**
     * Refill the reserve.
     * @param Integer of reserve's refill.
     */
    void refill(int refill);
}
