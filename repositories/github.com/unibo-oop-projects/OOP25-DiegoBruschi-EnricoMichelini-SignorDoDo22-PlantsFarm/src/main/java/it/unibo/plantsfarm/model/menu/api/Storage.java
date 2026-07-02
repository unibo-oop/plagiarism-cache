package it.unibo.plantsfarm.model.menu.api;

import java.util.Map;

import it.unibo.plantsfarm.model.plant.PlantType;

/**
 * Interface representing the storage model of the game.
 */
public interface Storage {

    /**
     * Adds a quantity of a specific plant to storage.
     *
     * @param type   The plant type.
     * @param amount The amount to add.
     */
    void addItem(PlantType type, int amount);

    /**
     * Removes a quantity of a specific plant from storage.
     *
     * @param type   The plant type.
     * @param amount The amount to remove.
     * @return True if successful, false otherwise.
     */
    boolean removeItem(PlantType type, int amount);

    /**
     * Resets the storage to zero and updates the save file.
     */
    void reset();

    /**
     * Gets the quantity of a specific plant.
     *
     * @param type The plant type.
     * @return The quantity stored.
     */
    int getQuantity(PlantType type);

    /**
     * Returns all stored items as an unmodifiable map.
     *
     * @return The items map.
     */
    Map<PlantType, Integer> getAllItems();

}
