package application.model.consumers;

import application.model.services.Fuel;

/**
 * Interface containing all the logic of a Vehicle.
 * @author Alessandro Mami
 *
 */
public interface Vehicle {
    
    /**
     * Gets the fuel of a vehicle.
     * @return Object type Fuel.
     */
    Fuel getFuel();
    
    /**
     * Sets the fuel of a vehicle.
     * @param Object type Fuel.
     */
    void setFuel(Fuel fuel);
    
    /**
     * Gets the request of a vehicle.
     * @return Integer of vehicle's request.
     */
    int getRequest();
    
    /**
     * Sets the request of a vehicle.
     * @param Integer of vehicle's request.
     */
    void setRequest(int request);
    
    /**
     * Serves the vehicle's request.
     */
    void serve();
}
