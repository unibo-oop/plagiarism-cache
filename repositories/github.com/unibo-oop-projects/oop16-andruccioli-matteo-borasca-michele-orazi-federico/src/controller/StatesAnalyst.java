package controller;

import java.util.logging.Logger;

import model.Model;
import model.exceptions.GameOverException;
import model.state.StateInfo;
import utils.enumerations.Status;
import view.View;

/**
 * Checks that the choices, made by the user during the movement of his tanks, are correct.
 * If in Attack {@link Status}, it controls the correctness of the movement of tanks after the 
 * conquer of a territory, from one player State to one of the enemy States.
 * If in Movement {@link Status}, it controls the correctness of the movement of tanks at the end 
 * of the turn, from a State to another both controlled by the current player. 
 */
public class StatesAnalyst extends Thread {

    private final Model model;
    private final View view;
    private final StateInfo source;
    private final StateInfo destination;
    private final int tanksNumber;
    private static final Logger LOGGER = Logger.getLogger(TanksAnalyst.class.getName());

    /**
     * Creates a new StatesAnalyst.
     * @param m
     *         the reference to the Model.
     * @param v
     *         the reference to the View.
     * @param source
     *         the State whence the movement starts.
     * @param destination
     *         the State where the movement ends.
     * @param tanksNumber
     *         the number of tanks moved.
     */
    public StatesAnalyst(final Model m, final View v, final StateInfo source, final StateInfo destination, final int tanksNumber) {
        this.model = m;
        this.view = v;
        this.source = source;
        this.destination = destination;
        this.tanksNumber = tanksNumber;
        this.setName("StatesAnalyst");
    }

    @Override
    public void run() {
        LOGGER.info(this.getName() + " is started.");
        try {
            switch (StatusManager.getStatusManager().getGameStatus()) {
                case ATTACK: //Control of the movement after a single attack.
                    this.model.getAttackManager().attackStateCheck(this.source.forwarder(), this.destination.forwarder(), this.tanksNumber);
                    ControllerFactory.getController().startBattlePhase();
                    break;
                case MOVEMENT: //Control of the movement at the end of the turn of a single player.
                    this.model.getDeployManager().movementStateCheck(this.source.forwarder(), this.destination.forwarder(), this.tanksNumber);
                    this.view.updateStates();
                    break;
                default:
                    break;
            }
            LOGGER.info(this.getName() + " is over without exceptions.");
        } catch (IllegalArgumentException e) {
            this.view.revertAction();
            this.view.printError(e.getMessage());
            LOGGER.info(this.getName() + " is over without IllegalArgumentException.");
        } catch (GameOverException e) {
            this.view.showVictory();
            LOGGER.info(this.getName() + " is over without GameOverException.");
        }
    }

}
