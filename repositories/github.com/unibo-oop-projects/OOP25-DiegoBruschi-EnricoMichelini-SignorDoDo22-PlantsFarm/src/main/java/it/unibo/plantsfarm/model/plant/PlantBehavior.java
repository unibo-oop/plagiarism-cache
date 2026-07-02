package it.unibo.plantsfarm.model.plant;

import java.io.Serializable;

/**
 * Defines the behavior strategy for a specific plant type.
 */
public interface PlantBehavior extends Serializable {

    /**
     * Checks if the plant is edible or ornamental.
     *
     * @return true if edible, false if ornamental.
     */
    boolean isEdible();

    /**
     * Gets the selling price.
     *
     * @return price in coins or 0 if ornamental.
     */
    int getSellPrice();

    /**
     * Generates the harvest amount.
     *
     * @return quantity of items or 0 if ornamental.
     */
    int generateHarvest();
}
