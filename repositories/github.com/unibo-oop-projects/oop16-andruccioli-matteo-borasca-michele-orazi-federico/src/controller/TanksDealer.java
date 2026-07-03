package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.Model;
import model.exceptions.GameOverException;
import utils.enumerations.Status;
import view.View;

/**
 * Manages the deployment of tanks at the beginning of a player's turn.
 * Allows the player to deploy a number of tanks depending on the amount of States and Regions under his control 
 * and if the player desires to use a combination of cards it allows to make it. It controls the correctness of
 * this choice making use of {@link TanksAnalyst}.
 */
public class TanksDealer extends Thread {

    private final Model model;
    private final View view;
    private static final Logger LOGGER = Logger.getLogger(TanksDealer.class.getName());

    /**
     * Creates a new TanksDealer.
     * @param m
     *         the reference to the Model.
     * @param v
     *         the reference to the View.
     */
    public TanksDealer(final Model m, final View v) {
        this.model = m;
        this.view = v;
        this.setName("TanksDealer");
        LOGGER.setLevel(Level.INFO);
    }

    @Override
    public void run() {
        LOGGER.info(this.getName() + " is started.");
        this.model.getDeployManager().setTanksToDeploy();
        switch (this.model.getActualPlayer().playerType()) {
            case AI:
                try {
                    this.view.disableAllComponents(true);
                    this.view.updateStates(this.model.getAIManager().deployTanks());
                    StatusManager.getStatusManager().setGameStatus(Status.ATTACK);
                    ControllerFactory.getController().startBattlePhase();
                } catch (GameOverException e) {
                    this.view.showVictory();
                }
                break;
            case HUMAN:
                this.view.disableAllComponents(false);
                this.view.deployTanks();
                LOGGER.info(this.getName() + " in pause.");
                this.pause();
                LOGGER.info(this.getName() + " is awakened.");
                this.view.updateStates();
                if (!StatusManager.getStatusManager().getGameStatus().equals(Status.INITIALIZATION)) {
                    StatusManager.getStatusManager().setGameStatus(Status.ATTACK);
                }
                break;
            default:
        }
        LOGGER.info(this.getName() + " is over.");
    }

    private void pause() {
        try {
            ControllerFactory.getController().getSemaphore().acquire();
        } catch (InterruptedException e) { }
    }

}
