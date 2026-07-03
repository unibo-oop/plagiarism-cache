package model;

import java.util.Map;

import model.exceptions.GameOverException;
import model.state.State;
import model.state.StateInfo;

/**
 * Interface for {@link DeployManager} class.
 */
public interface DeployManagerInterface {

    /**
     * Method that checks if a combination of State and nTanks is valid for that
     * player, if it's valid the assignment is committed.
     * 
     * @param choice
     *            The combinations of State Integer the player made.
     * @throws IllegalArgumentException
     *          if 1 or more States don't belong to the actualPlayer.
     * @throws GameOverException
     *          if the player has won the game.
     */
    void assignmentCheck(Map<StateInfo, Integer> choice) throws IllegalArgumentException, GameOverException;

    /**
     * Method that checks if the initial deployment phase is finished.
     * 
     * @return true if this phase is finished.
     */
    boolean isDeploymentPhaseFinished();

    /**
     * Method that checks if a movement from a state to another is valid, in
     * case it is the movement is committed.
     * 
     * @param from
     *            The state from where the tanks are moved.
     * @param to
     *            The state to where the tanks need to be moved.
     * @param nTanks
     *            The amount of tanks to move.
     * @throws IllegalArgumentException
     *          If some of the arguments are wrong.
     *          @throws GameOverException
     *          if the player has won the game.
     */
    void movementStateCheck(State from, State to, int nTanks) throws IllegalArgumentException, GameOverException;

    /**
     * Method used to set the number of tanks to deploy for each deployment
     * phase.
     */
    void setTanksToDeploy();

}