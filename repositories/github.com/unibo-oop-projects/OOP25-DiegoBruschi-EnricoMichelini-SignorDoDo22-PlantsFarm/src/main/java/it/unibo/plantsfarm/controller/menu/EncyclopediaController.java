package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.model.menu.api.Encyclopedia;
import it.unibo.plantsfarm.model.menu.impl.EncyclopediaImpl;
import it.unibo.plantsfarm.model.menu.impl.GameStateImpl;
import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.view.menu.api.EncyclopediaScreen;
import it.unibo.plantsfarm.view.menu.impl.EncyclopediaScreenImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Encyclopedia feature.
 */
public final class EncyclopediaController {

    private static final String NEXT_STAGE_COMMAND = "NEXT_STAGE";
    private final EncyclopediaScreen view;
    private final Encyclopedia encyclopedia;
    private PlantImpl selectedPlant;
    private int currentStageIndex;

    /**
     * Creates the controller.
     */
    public EncyclopediaController() {
        this.view = new EncyclopediaScreenImpl();
        this.encyclopedia = new EncyclopediaImpl();
    }

    /**
     * Starts the encyclopedia.
     *
     * @param gameState The current state of the game.
     */
    public void start(final GameStateImpl gameState) {
        final List<String> names = new ArrayList<>();
        final List<Boolean> unlockedStatus = new ArrayList<>();

        for (final PlantImpl plant : gameState.getAllPlants()) {
            names.add(plant.getName());
            unlockedStatus.add(plant.isDiscovered());
        }

        final ActionListener listener = (ActionEvent e) -> {
            final String command = e.getActionCommand();
            if (NEXT_STAGE_COMMAND.equals(command)) {
                nextStage();
            } else {
                selection(command, gameState);
            }
        };

        this.view.show(names, unlockedStatus, listener, NEXT_STAGE_COMMAND);
    }

    private void selection(final String name, final GameStateImpl gameState) {
        for (final PlantImpl plant : gameState.getAllPlants()) {
            if (plant.getName().equals(name)) {
                this.selectedPlant = plant;
                this.currentStageIndex = plant.getType().getMaxGrowthStage();
                final String description = this.encyclopedia.getPlantDescription(plant.getType());

                this.view.updateDetails(plant.getName(), description);
                this.view.setRarity(plant.getType().getRarity().name());
                this.view.updateStageImage(plant.getName(), this.currentStageIndex);
                break;
            }
        }
    }

    private void nextStage() {
        if (this.selectedPlant != null) {
            this.currentStageIndex++;
            final boolean exists = this.view.updateStageImage(this.selectedPlant.getName(), this.currentStageIndex);

            if (!exists) {
                this.currentStageIndex = 1;
                this.view.updateStageImage(this.selectedPlant.getName(), this.currentStageIndex);
            }
        }
    }
}
