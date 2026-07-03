package model;

import java.util.Map;
import model.exceptions.GameOverException;
import model.player.Player;
import model.state.StateInfo;
import model.state.State;
import utils.CircularList;
import utils.HistoryLog;

/**
 * 
 * Class used to manage the tanks deployment and movement.
 *
 */
public final class DeployManager implements DeployManagerInterface {

    private static final String NOT_INITIALIZED_ERROR_MESSAGE = "DeployManager not initialized.";
    private CircularList<Player> players;
    private boolean initialized;
    private boolean phaseFinished;
    private static final DeployManager SINGLETON = new DeployManager();

    private DeployManager() {
        this.initialized = false;
    }

    /**
     * @return The instance of the DeployManager.
     */
    public static DeployManager getInstance() {
        return SINGLETON;
    }

    /**
     * Method to initialize the DeployManager.
     * 
     * @param players
     *            The list of the players.
     */
    public void init(final CircularList<Player> players) {
        if (!this.initialized) {
            this.players = players;
            this.initialized = true;
        }
    }

    @Override
    public void assignmentCheck(final Map<StateInfo, Integer> choice)
            throws IllegalArgumentException, GameOverException {
        if (!this.initialized) {
            throw new IllegalStateException(NOT_INITIALIZED_ERROR_MESSAGE);
        }
        choice.entrySet().stream().filter(e -> !e.getKey().getPlayer().equals(this.players.getHead())).findFirst().ifPresent(o -> {
            throw new IllegalArgumentException("Cannot deploy tanks in " + o.getKey().getName() + ".");
        }); 
        String temp = this.players.getHead().getName() + " added: ";
        for (final Map.Entry<StateInfo, Integer> e : choice.entrySet()) {
            e.getKey().forwarder().addTanks(e.getValue());
            temp += e.getValue() + " tanks in " + e.getKey().getName() + ", ";
        }
        HistoryLog.getLog().send(temp);
        this.players.getHead().resetTanksToDeploy();
        if (AimManager.getInstance().checkWin()) {
            throw new GameOverException(this.players.getHead().getName());
        }
    }

    @Override
    public boolean isDeploymentPhaseFinished() {
        if (!this.initialized) {
            throw new IllegalStateException(NOT_INITIALIZED_ERROR_MESSAGE);
        }
        if (this.phaseFinished) {
            return true;
        }
        if (this.players.getHead().getTotalTanksToDeploy() == 0 && this.players.getHead().getTanksToDeploy() == 0) {
            if (!this.phaseFinished) {
                this.phaseFinished = true;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void movementStateCheck(final State from, final State to, final int nTanks)
            throws IllegalArgumentException, GameOverException {
        if (!this.initialized) {
            throw new IllegalStateException(NOT_INITIALIZED_ERROR_MESSAGE);
        }
        if (from.getPlayer().equals(this.players.getHead()) 
            && to.getPlayer().equals(this.players.getHead())
            && from.availableTanks() >= nTanks
            && from.getNeighbouringStates().contains(to)) {
            from.moveTanks(to, nTanks);
            if (AimManager.getInstance().checkWin()) {
                throw new GameOverException(this.players.getHead().getName());
            }
        } else {
            throw new IllegalArgumentException("Cannot move from " + from + " to " + to + ".");
        }
        HistoryLog.getLog().send(this.players.getHead().getName() + " has moved " + nTanks 
                          + " tanks from " + from.getName() + " to " + to.getName() + ".");
    }

    @Override
    public void setTanksToDeploy() {
        if (!this.initialized) {
            throw new IllegalStateException(NOT_INITIALIZED_ERROR_MESSAGE);
        }
        if (this.players.getHead().getTanksToDeploy() != 0) {
            this.players.getHead().resetTanksToDeploy();
        }
        if (!isDeploymentPhaseFinished()) {
            if (this.players.getHead().getTotalTanksToDeploy() >= 3) {
                this.players.getHead().addTanksToDeploy(3);
                this.players.getHead().decreaseTotalTanksToDeploy(3);
            } else {
                this.players.getHead().addTanksToDeploy(this.players.getHead().getTotalTanksToDeploy());
                this.players.getHead().decreaseTotalTanksToDeploy(this.players.getHead().getTotalTanksToDeploy());
            }
        } else {
            evaluateTanks();
        }
        HistoryLog.getLog().send(this.players.getHead().getName() + " has " + this.players.getHead().getTanksToDeploy() 
                          + " tanks to deploy this turn.");
    }

    /**
     * Method used to reset the Singleton when exit a game.
     */
    public void clear() {
        this.players = null;
        this.initialized = false;
        this.phaseFinished = false;
    }

    private void evaluateTanks() {
        if (this.players.getHead().getStates().size() <= 3) {
            this.players.getHead().addTanksToDeploy(1);
        } else {
            this.players.getHead().addTanksToDeploy(this.players.getHead().getStates().size() / 3);
        }
        this.players.getHead().getRegions().stream().forEach(r -> this.players.getHead().addTanksToDeploy(r.getBonusTanks()));
    }

}
