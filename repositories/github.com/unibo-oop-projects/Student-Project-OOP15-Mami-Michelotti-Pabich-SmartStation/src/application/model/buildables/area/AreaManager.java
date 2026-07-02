package application.model.buildables.area;

import java.util.List;

import application.model.buildables.pump.Pump;

/**
 * Interface containing all the logic to build and modify an area inside the station.
 * @author Alessandro Mami
 * 
 */
public interface AreaManager {
    
    /**
     * Gets the area of coordinates x and y.
     * @param x x coordinate of the area
     * @param y coordinate of the area
     * @return Area's type object.
     */
    Area getArea(int x, int y);
    
    /**
     * Gets every area of the station.
     * @return Area's type list.
     */
    List<Area> getAllAreas();
    
    /**
     * Adds an area to the station.
     * @param Area's type object.
     */
    boolean addArea(int x, int y, List<Pump> pumps);
    
    /**
     * Removes an area from the station.
     * @param Coordinate x integer.
     * @param Coordinate y integer.
     * @return True if coordinates are correct or else false.
     */
    boolean removeArea(int x, int y);
    
    /**
     * Removes every area from the station.
     */
    void removeAllAreas();
}
