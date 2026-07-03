package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.Model;
import model.exceptions.GameOverException;
import utils.enumerations.Status;
import view.View;

/**
 * Manages the deployment of the pre-game tanks.
 * Manages Model and View depending on the choices made.
 */
public class StatesArmer extends Thread {

    private final Model model;
    private final View view;
    private static final Logger LOGGER = Logger.getLogger(StatesArmer.class.getName());

    /**
     * Creates a new StatesArmer.
     * @param m
     *         the reference to the Model.
     * @param v
     *         the reference to the View.
     */
    public StatesArmer(final Model m, final View v) {
        this.model = m;
        this.view = v;
        this.setName("StatesArmer");
        LOGGER.setLevel(Level.INFO);
    }

    @Override
    public void run() {
        LOGGER.info(this.getName() + " is started.");
        do {
            this.model.getDeployManager().setTanksToDeploy();
            switch (this.model.getActualPlayer().playerType()) {
                case AI:
                    try {
                        this.view.updateStates(this.model.getAIManager().deployTanks());
                    } catch (GameOverException e) {
                        this.view.showVictory();
                    }
                    break;
                case HUMAN:
                    this.view.deployTanks();
                    LOGGER.info(this.getName() + " in pause.");
                    this.pause();
                    LOGGER.info(this.getName() + " is awakened.");
                    this.view.updateStates();
                    break;
                default:
            }
            this.model.shiftPlayer();
            this.model.autoSave();
            this.view.updateInfoPlayer();
        } while (!this.model.getDeployManager().isDeploymentPhaseFinished());
        StatusManager.getStatusManager().setGameStatus(Status.DEPLOYMENT);
        this.model.reorganize();
        this.view.deployPhaseFinished();
        LOGGER.info(this.getName() + " is over.");
    }

    private void pause() {
        try {
            ControllerFactory.getController().getSemaphore().acquire();
        } catch (InterruptedException e) { }
    }

}
