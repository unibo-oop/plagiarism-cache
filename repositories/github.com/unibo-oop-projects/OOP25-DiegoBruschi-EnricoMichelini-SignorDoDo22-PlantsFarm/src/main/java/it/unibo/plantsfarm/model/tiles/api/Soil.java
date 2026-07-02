package it.unibo.plantsfarm.model.tiles.api;

import java.awt.Rectangle;

import it.unibo.plantsfarm.model.plant.PlantImpl;

/**
 * Represents a soil tile in the game, which can be planted with a plant.
 */
public interface Soil {

    /**
     * Gets the coordinates of the soil tile.
     * 
     * @return The coordinates of the soil tile as a Rectangle.
     */
    Rectangle getCoordinate();

    /**
     * Plants a plant in the soil tile.
     * 
     * @param selectedPlant The plant to be planted in the soil tile.
     */
    void setPlanted(PlantImpl selectedPlant);

    /**
     * Checks if the soil tile is planted with a plant.
     * 
     * @return true if the soil tile is planted, false otherwise.
     */
    boolean isPlanted();

    /**
     * Gets the plant planted in the soil tile.
     * 
     * @return The plant planted in the soil tile, or null if none.
     */
    PlantImpl getPlant();

    /**
     * Gets the tile ID of the soil tile.
     * 
     * @return The tile ID of the soil tile.
     */
    int getTileId();

    /**
     * Removes the plant from the soil tile, making it unplanted.
     */
    void removePlant();
}
