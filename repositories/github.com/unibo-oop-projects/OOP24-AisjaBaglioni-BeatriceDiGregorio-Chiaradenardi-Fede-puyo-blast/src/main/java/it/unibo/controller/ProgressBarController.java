package it.unibo.controller;

import it.unibo.controller.interfaces.ProgressBarControllerInterface;
import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.ProgressBarModel;

/**
 * The ProgressBarController class manages the progress bar mechanics.
 * It updates the progress bar over time and handles reset operations.
 */
public class ProgressBarController implements TickListenerInterface, ProgressBarControllerInterface {
    /**
     * The model representing the progress bar.
     */
    private final ProgressBarModel progressBarModel;

    /**
     * Constructor for initializing the ProgressBarController.
     * 
     * @param progressBarModel the model managing the progress bar.
     */
    public ProgressBarController(ProgressBarModel progressBarModel) {
        this.progressBarModel = progressBarModel;
    }

    /**
     * Called on each game tick to increment the progress bar.
     */
    @Override
    public void onTick() {
        this.progressBarModel.incrementProgress();
    }

    /**
     * Resets the progress bar if it is fully charged.
     * 
     * @return true if the bar was fully charged and reset, false otherwise.
     */
    @Override
    public boolean resetBar() {
        if (this.progressBarModel.getChargeLevel() == 1) {
            this.progressBarModel.resetCharge();
            return true;
        }
        return false;
    }

    /**
     * Resets the progress bar to its initial state.
     */
    @Override
    public void reset() {
        this.progressBarModel.resetCharge();
    }
}
