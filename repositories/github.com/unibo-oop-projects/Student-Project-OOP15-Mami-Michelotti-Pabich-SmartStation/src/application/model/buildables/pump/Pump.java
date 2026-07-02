package application.model.buildables.pump;

import application.model.buildables.Buildable;
import application.model.services.Fuel;

/**
 * Interface containing all the logic of a Pump extended by Buildable.
 * @author Alessandro Mami
 *
 */
public interface Pump extends Buildable{
    
    /**
     * Gets the name of a pump.
     * @return Pump's name string.
     */
    String getName();
    
    /**
     * Gives the name to a pump.
     * @param Pump's name string.
     */
    void setName(String name);

    /**
     * Gets the fuel type of a pump.
     * @return Fuel's type object.
     */
    Fuel getType();
    
    /**
     * Sets the fuel's type of a pump.
     * @param Fuel's type object.
     */
    void setType(Fuel type);
    
    /**
     * Gets the speed a pump.
     * @return Pump's speed integer.
     */
    int getSpeed();
    
    /**
     * Sets the speed a pump.
     * @param Pump's speed integer.
     */
    void setSpeed(int speed);
}
