package it.unibo.plantsfarm.controller.action;

import it.unibo.plantsfarm.controller.action.api.SeedSelectionListener;
import it.unibo.plantsfarm.model.plant.PlantRegistry;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.view.map.api.SeedView;
import it.unibo.plantsfarm.view.map.impl.SeedViewImpl;

import java.awt.event.ActionListener;

/**
 * Controller responsible for managing the seed selection interface.
 */
public final class SeedController {

    private SeedView view;
    private final SeedSelectionListener selectionListener;

    /**
     * Creates a new controller for the Seed Selection window.
     *
     * @param selectionListener When a plant is chosen.
     * @param switchIndex   True to open on Edible window, false for Ornamental.
     */
    public SeedController(final SeedSelectionListener selectionListener, final boolean switchIndex) {
        this.selectionListener = selectionListener;
        createView(switchIndex);
    }

    /**
     * Initializes the view with the specific category of plants.
     *
     * @param isEdible True for edible plants, false for ornamental.
     */
    private void createView(final boolean isEdible) {
        this.view = new SeedViewImpl(isEdible);

        if (isEdible) {
            loadEdiblePlants();
        } else {
            loadOrnamentalPlants();
        }

        this.view.addSwitchModeListener(e -> {
            createView(!isEdible);
            this.start();
        });
    }

    private void loadEdiblePlants() {
        for (final PlantType plant : PlantRegistry.getAll()) {
            if (plant.isEdible()) {
                addToView(plant);
            }
        }
    }

    private void loadOrnamentalPlants() {
        for (final PlantType plant : PlantRegistry.getAll()) {
            if (!plant.isEdible()) {
                addToView(plant);
            }
        }
    }

    private void addToView(final PlantType plant) {
        final ActionListener action = createListener(plant);
        this.view.addPlantButton(plant.getName(), plant.isDiscovered(), action);
    }

    private ActionListener createListener(final PlantType plant) {
        return e -> {
            if (plant.isDiscovered()) {
                selectionListener.onPlantSelected(plant);
                view.close();
            }
        };
    }

    /**
     * Makes the seed selection view visible.
     */
    public void start() {
        this.view.show();
    }
}
