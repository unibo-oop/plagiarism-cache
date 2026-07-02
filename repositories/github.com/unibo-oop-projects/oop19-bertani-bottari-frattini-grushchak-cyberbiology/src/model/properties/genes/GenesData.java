package model.properties.genes;

import model.world.World;

/**
 * 
 * The interface with methods that giving access to genes data.
 *
 */
public interface GenesData {
    /**
     * @return current simulation world.
     */
    World getWorld();

    /**
     * @return the amount of energy required for reproduction.
     */
    int getReproductionCost();

    /**
     * @return At what depth cells can perform photosynthesis.
     */
    float getSunPenetration();

    /**
     * @return How effective is photosynthesis.
     */
    int getSunEnergy();

    /**
     * @return At what depth cells can absorb minerals.
     */
    float getMineralsDepth();

    /**
     * @return How effective is absorbing of minerals.
     */
    int getMineralsAbsorption();

    /**
     * @return A mutation rate during reproduction.
     */
    float getMutationRate();

    /**
     * @return How much energy a cell receives if it ate a dead cell.
     */
    int getNutritionOfDeadCell();
}
