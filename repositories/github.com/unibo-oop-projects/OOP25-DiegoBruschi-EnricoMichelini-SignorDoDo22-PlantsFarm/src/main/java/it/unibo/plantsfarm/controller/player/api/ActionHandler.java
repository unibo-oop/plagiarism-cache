package it.unibo.plantsfarm.controller.player.api;

import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.controller.garden.GardenController;
import it.unibo.plantsfarm.controller.garden.SpawningBuffsController;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.tiles.SoilImpl;

/**
 * this class is used for take input from {@param ImplControllerGamePanel } .
 *
 */
public interface ActionHandler {

    /**
     * The player can use the Hoe if the {@link Soil} is empty or the
     * plant is mature. in both case the hoe  experience will increase.
     *
     * @param controllerGarden the garden controller used to verify state soil and player position.
     * @param selectedPlant the plant selected in the inventory.
     */
    void handleActionHoe(GardenController controllerGarden, PlantType selectedPlant);

    /**
     * The player can use the Water if the {@link SoilImpl} have a plant and the
     * plant is ready to be watered. I
     *
     * @param controllerGarden for check the soil state and presence of a plant.
     * @param now used for updating plant watering state.
     * @param selectedPlant the plant selected in the inventory.
     */
    void handleWater(GardenController controllerGarden, Long now, PlantType selectedPlant);

    /**
     * The player can remove a plant if the {@link SoilImpl} have a plant.
     * The axe dose't has a level.
     *
     * @param controllerGarden for check the soil state and presence of a plant.
     */
    void handleAxe(GardenController controllerGarden);

    /**
     * Updates the player's movement direction.
     * The direction is determined by the given {@link UserInput}.
     *
     * @param input the user input representing the next direction
     */
    void updateDirection(UserInput input);

    /**
     * The function is called everytime in the loopGame.
     * If the player is near the upgrade, it will be applaied with {@link ModelInventory}
     *
     * @param controllerbuff the controller buff verify if the player is near a upgrade.
     *
     * @return true if the player collect a buff, false otherwise.
     */
    boolean playerActionBuff(SpawningBuffsController controllerbuff);
}
