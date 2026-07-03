package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.Model;
import model.exceptions.GameOverException;
import utils.enumerations.Status;
import view.View;

/**
 * Manages the battle during the turn of a single player.
 * Allows the player to attack someone, specifying the State where the attack takes from, the enemy State and 
 * the number of tanks used. It controls the correctness of this choice making use of {@link StatesAnalyst}.
 */
public class BattleManager extends Thread {

    private final Model model;
    private final View view;
    private static final int NORMAL_WAITING_TIME = 2000;
    private static final int ONLY_AI_WAITING_TIME = 50;
    private static final Logger LOGGER = Logger.getLogger(BattleManager.class.getName());

    /**
     * Creates a new BattleManager.
     * @param m
     *         the reference to the Model.
     * @param v
     *         the reference to the View.
     */
    public BattleManager(final Model m, final View v) {
        this.model = m;
        this.view = v;
        this.setName("BattleManager");
        LOGGER.setLevel(Level.INFO);
    }

    @Override
    public void run() {
        LOGGER.info(this.getName() + " is started.");
        try {
            switch (this.model.getActualPlayer().playerType()) {
                case AI:
                    while (!this.model.getAIManager().createBattleMap()) {
                        this.model.getAIManager().attack();
                        this.view.updateDice(this.model.getAttackManager().getDice());
                        this.view.updateStates();
                        if (this.model.allAIPlayers()) {
                            sleep(ONLY_AI_WAITING_TIME);
                        } else {
                            sleep(NORMAL_WAITING_TIME);
                        }
                    }
                    StatusManager.getStatusManager().setGameStatus(Status.MOVEMENT);
                    if (this.model.getAIManager().moveTanks()) {
                        this.view.updateStates();
                    }
                    this.view.aIAttackPhaseFinished();
                    break;
                case HUMAN:
                    this.model.getAttackManager().evaluateResults();
                    this.view.updateDice(this.model.getAttackManager().getDice());
                    this.view.updateStates();
                    this.view.updateInfoPlayer();
                    if (this.model.getAttackManager().hasConquered()) {
                        this.view.showMovementDialog();
                    }
                    break;
                default:
            }
            LOGGER.info(this.getName() + " is over. Without exceptions");
        } catch (GameOverException e) {
            this.view.showVictory();
            LOGGER.info(this.getName() + " is over catching a GameOverException.");
        } catch (InterruptedException e) { }
    }

}
