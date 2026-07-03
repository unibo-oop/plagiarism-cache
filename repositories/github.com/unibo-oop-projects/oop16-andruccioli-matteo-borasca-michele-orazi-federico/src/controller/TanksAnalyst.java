package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Model;
import model.exceptions.GameOverException;
import model.state.StateInfo;
import view.View;

/**
 * Checks that the choices made by the user during the deployment of his tanks are correct.
 * A user can deploy his tanks only in his States and the number of tanks deployed must be
 * the right number.
 */
public class TanksAnalyst extends Thread {

    private final Model model;
    private final View view;
    private final Map<StateInfo, Integer> choice;
    private static final Logger LOGGER = Logger.getLogger(TanksAnalyst.class.getName());

    /**
     * Creates a new TanksAnalyst.
     * @param m
     *         the reference to the Model.
     * @param v
     *         the reference to the View.
     * @param choice2
     *         the choice made by the user. It's represented by a list of States where each one 
     *         is associated with the number of tanks deployed there.
     */
    public TanksAnalyst(final Model m, final View v, final Map<StateInfo, Integer> choice2) {
        this.model = m;
        this.view = v;
        this.choice = new HashMap<>(choice2);
        this.setName("TanksAnalyst");
        LOGGER.setLevel(Level.INFO);
    }

    @Override
    public void run() {
        LOGGER.info(this.getName() + " is started.");
        try {
            this.model.getDeployManager().assignmentCheck(this.choice);
            LOGGER.info(this.getName() + " is over without exceptions. It wakes up the first thread waiting.");
            this.awake();
        } catch (IllegalArgumentException e) {
            this.view.revertAction();
            this.view.printError(e.getMessage());
            LOGGER.info(this.getName() + " is over without IllegalArgumentException.");
        } catch (GameOverException e) {
            this.view.showVictory();
            LOGGER.info(this.getName() + " is over without GameOverException.");
        }
    }

    private void awake() {
        ControllerFactory.getController().getSemaphore().release();
    }

}
