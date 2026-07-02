package application.model.services;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * Interface containing all the logic to create a fuel.
 * @author Alessandro Mami
 * 
 */
public interface FuelManager {
    
    /**
     * Gets a fuel by name.
     * @param fuel name of the fuel.
     * @return Object of fuel's type.
     */
    Fuel getFuel(String fuel);
    
    /**
     * Gets every fuel.
     * @return List of fuel's type.
     */
    List<Fuel> getAllFuels();
    
    /**
     * Adds a new fuel.
     * @param name fuel's name.
     * @param price fuel's price.
     * @param wholesalePrice fuel's whole sale price.
     * @param color color's type.
     */
    void addFuel(String name, int price, int wholesalePrice, Color color);
    
    /**
     * Removes a fuel by name.
     * @param name fuel's name.
     */
    void removeFuel(String name);
    
    /**
     * Removes every fuel.
     */
    void removeFuels();
}
