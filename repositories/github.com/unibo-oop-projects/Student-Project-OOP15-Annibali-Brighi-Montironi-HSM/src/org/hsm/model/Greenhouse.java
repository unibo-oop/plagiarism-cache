package org.hsm.model;

import java.util.List;
import java.util.Map;

import org.hsm.model.plant.Plant;
import org.hsm.model.plant.PlantModel;

/**
 * interface for the communication between the model and the controller.
 */
public interface Greenhouse {

    /**
     * Add n plant in the Greenhouse.
     *
     * @param plant
     *            type of plant to add
     * @param cost
     *            cost of the single plant
     * @return id plant
     */
    int addPlant(final PlantModel plant, final int cost);

    /**
     * Remove a single plant from the Greenhouse.
     *
     * @param id
     *            id plant to remove
     */
    void delPlant(final int id);

    /**
     * Remove a kind of plant from the Greenhouse.
     *
     * @param plant
     *            type of plant to remove
     */
    void delPlants(final PlantModel plant);

    /**
     * @return Map<Integer, Plant> return a Map. The key is ID plant. The value
     *         is Plant
     */
    Map<Integer, Plant> getPlants();

    /**
     * @return return name of the greenhouse
     */
    String getName();

    /**
     * @return total size of the greenhouse in m²
     */
    double getSize();

    /**
     * @return Free space of the greenhouse
     */
    double getFreeSize();

    /**
     * @return Return the space occupied by plants in m²
     */
    double getOccSize();

    /**
     * @return return the costGreenhouse field
     */
    double getCost();

    /**
     * @return Number of plants in the greenhouse
     */
    int getNumberOfPlants();

    /**
     * @param cost
     *            cost of the greenhouse
     */
    void setCost(double cost);

    /**
     * @return total cost of the greenhouse (greenhouse cost + plants cost)
     */
    double totalCost();

    /**
     * @return return the type of the greenhouse
     */
    GreenHouseType getType();

    /**
     * @return a map: keys are botanical names, values are quantity
     */
    Map<String, Integer> getCompositionByNumber();

    /**
     * @return a map: Keys are botanical names, values are occupied space by
     *         plants
     */
    Map<String, Double> getCompositionByOccupiedSpace();

    /**
     * Increment the number of refresh.
     */
    void incrementCounter();

    /**
     * @return the number of refresh.
     */
    int getUpdateCounter();

    /**
     * Provide a list of water consumption of the greenhouse from simulator.
     *
     * @return the list of simulated water consumption
     */
    List<Double> getSimulatedWaterConsuption();

    /**
     * Provide a list of plants grown from simulator.
     *
     * @return the list of simulated plant grow
     */
    List<Double> getSimulatedPlantGrow();

    /**
     * Provide a list of water consumption of the traditional cultivation from
     * simulator.
     *
     * @return the list of water consumption
     */
    List<Double> getRealWaterConsuption();

    /**
     * Provide a list of plants grown in traditional cultivation from simulator.
     *
     * @return the list of plant grow
     */
    List<Double> getRealPlantGrow();

}
