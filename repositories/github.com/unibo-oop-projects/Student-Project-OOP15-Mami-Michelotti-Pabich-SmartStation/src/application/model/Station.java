package application.model;

import application.model.buildables.area.AreaManager;
import application.model.buildables.pump.PumpManager;
import application.model.buildables.reserve.ReserveManager;
import application.model.moneyManager.MoneyManager;
import application.model.services.FuelManager;

/**
 * Interface containing all the logic of the Station.
 * @author Alessandro Mami
 *
 */
public interface Station {	
    
    /**
     * Gets the name of the station.
     * @return String of station's name.
     */
    String getStationName();
    
    /**
     * Gives the name to the station.
     * @param String of station's name.
     */
    void setStationName(final String name);
    
    /**
     * Gets the address of the station.
     * @return String of address's name.
     */
    String getAddress();
    
    /**
     * Sets the address of the station.
     * @param String of address name.
     */
    void setAddress(final String address);
    
    /**
     * Gets the maximum number of areas in the station.
     * @return Integer of area's number.
     */
    int getMaxAreas();
    
    /**
     * Sets the maximum number of areas in the station.
     * @param Integer of area's number.
     */
    void setMaxAreas(int maxAreas);
    
    /**
     * Gets the maximum number of pumps in to an area.
     * @return Integer of pump's number.
     */
    int getMaxPumps();
    
    /**
     * Sets the maximum number of pumps in the area.
     * @param Integer of pump's number.
     */
    void setMaxPumps(int maxPumps);
    
    /**
     * Opens the area, sets isOpen to true.
     */
    void open();
    
    /**
     * Closes the area, sets isOpen to false.
     */
    void close();
    
    /**
     * Managers of the logic of the station.
     */
    AreaManager getAreaManager();
    PumpManager getPumpManager();
    ReserveManager getReserveManager();
    FuelManager getFuelManager();
    MoneyManager getMoneyManager();
}
