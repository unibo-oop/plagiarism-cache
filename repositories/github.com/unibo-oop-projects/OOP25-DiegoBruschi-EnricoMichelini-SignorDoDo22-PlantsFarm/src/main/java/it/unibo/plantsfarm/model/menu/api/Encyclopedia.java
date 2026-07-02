package it.unibo.plantsfarm.model.menu.api;

import java.util.List;

import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantType;

/**
 * Interface representing the encyclopedia model of the game.
 */
public interface Encyclopedia {

    /**
     * Saves the current unlocked plants list to file.
     */
    void save();

    /**
     * Resets encyclopedia.
     */
    void reset();

    /**
     * Gets all plants in the encyclopedia.
     *
     * @return unmodifiable list of plants
     */
    List<PlantImpl> getPlants();

    /**
     * Adds a plant to the encyclopedia if it's not already present.
     *
     * @param plant The plant to add.
     */
    void addPlant(PlantImpl plant);

    /**
     * Returns the total number of plants in the encyclopedia.
     *
     * @return The number of plants.
     */
    int numberPlants();

    /**
     * Unlocks all plants in the encyclopedia.
     */
    void unlockAll();

    /**
     * Filters and returns only edible plants that have been discovered.
     *
     * @return A list of unlocked edible plants.
     */
    List<PlantImpl> getUnlockedEdiblePlants();

    /**
     * Counts the number of discovered edible plants.
     *
     * @return The number of discovered edible plants.
     */
    int getNumberUnlockedEdiblePlants();

    /**
     * Reads the description of a plant from a resource file.
     * 
     * @param type The type of the plant.
     * 
     * @return The description string or a default message if not found.
     */
    String getPlantDescription(PlantType type);

}
