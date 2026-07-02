package application.model.buildables.area;

import java.util.List;

import application.model.buildables.Buildable;
import application.model.buildables.pump.Pump;
import application.model.consumers.Vehicle;

/**
 * Interface containing all the logic of an Area.
 * @author Alessandro Mami
 *
 */
public interface Area {	
    
    /**
     * Gets the vehicle inside the area if there is one.
     * @return Vehicle's type object, or else null.
     */
    Vehicle getVehicle();
    
    /**
     * Vehicle remains at the pump if the space is available.
     * @param vehicle vehicle to put inside area
     * @return True if the area is available. or else false
     */
    boolean setVehicle(Vehicle vehicle);
    
    /**
     * Returns a list of every pump of an area.
     * @return Pumps list.
     */
    List<Pump> getAllPumps();
    
    /**
     * Returns the number of pumps of an area.
     * @return Count of pumps.
     */
    int getPumpsCount();
	
    /**
     * Returns the coordinate x of an Area.
     * @return Coordinate x integer.
     */
    int getXPosition();
    
    /**
     * Returns the coordinate y of an Area.
     * @return Coordinate y integer.
     */
    int getYPosition();
    
    /**
     * Builds the area in a certain position.
     * @param Coordinate x integer.
     * @param Coordinate y integer.
     * @return True if coordinates are acceptable or else false.
     */
    boolean setPosition(int x, int y);
    
    /**
     * Builds a pump inside an area.
     * @param Pump's type object.
     * @return True if there is space in the station or else false.
     */
    boolean addPump(Pump pump);
    
    /**
     * Builds a list of pumps inside an area.
     * @param Pump's type list of objects.
     * @return True if there is space in the area or else false.
     */
    boolean addPumps(List<Pump> pumps);
    
    /**
     * Removes a specific pump from the area.
     * @param Pump's type object.
     * @return True if there is at least a pump or else false.
     */
    boolean removePump(Pump pump);
    
    /**
     * Removes all the pumps from the area
     * @return TRUE if operation is ok
     */
    boolean removeAllPumps();
    
    /**
     * Removes the vehicle from the area.
     * @param Vehicle's type object.
     */
    boolean removeVehicle(Vehicle vehicle);
    
    /**
     * Checks if the pump is occupied by a vehicle.
     * @return True if the space is occupied or else false.
     */
    boolean isOccupied();
}
