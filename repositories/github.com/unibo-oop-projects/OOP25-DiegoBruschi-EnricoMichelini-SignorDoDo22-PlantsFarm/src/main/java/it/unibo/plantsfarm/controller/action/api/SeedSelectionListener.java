package it.unibo.plantsfarm.controller.action.api;

import it.unibo.plantsfarm.model.plant.PlantType;

/**
 * Functional interface for managing plant selection events.
 */
@FunctionalInterface
public interface SeedSelectionListener {

    /**
     * When a plant is selected by the user.
     *
     * @param plant The type of the selected plant.
     */
    void onPlantSelected(PlantType plant);
}
