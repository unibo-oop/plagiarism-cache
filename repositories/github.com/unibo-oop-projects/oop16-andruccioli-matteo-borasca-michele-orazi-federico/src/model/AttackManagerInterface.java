package model;

import java.util.List;

import javafx.util.Pair;
import model.exceptions.GameOverException;
import model.state.State;

/**
 * Interface for {@link AttackManager} class.
 */
public interface AttackManagerInterface {

    /**
     * Method used to set attacker, defender and number of attacking tanks after
     * having checked these values validity.
     * 
     * @param attacker
     *            attacking state
     * 
     * 
     * @param defender
     *            defending state
     * 
     * @param nTanks
     *            number of thanks the attacker wants to attack with
     * 
     * @throws IllegalArgumentException
     *             if arguments are received in the wrong order (the first and
     *             the second state passed are supposed to be respectively
     *             suitable as attacker and defender ) or nTanks is not
     *             appropriate
     */
    void attackStateCheck(State attacker, State defender, int nTanks) throws IllegalArgumentException;

    /**
     * Method that evalutes the result of the attack between the states checked before.
     * 
     * @throws GameOverException
     *          if the player has won the game.
     */
    void evaluateResults() throws GameOverException;

    /**
     * 
     * @return The last attack dice rolled.
     */
    List<Pair<Integer, Integer>> getDice();

    /**
     * Method used to let the current player move some of his tanks from the
     * attacking state to another state.
     * 
     * @param nTanks
     *            number of thanks the attacker wants to move
     * 
     * @throws IllegalArgumentException
     *             if nTanks is not appropriate
     */
    void moveTanks(int nTanks);

    /** 
     * @return 
     *          true if in the last attack there was a conquer.
     */
    boolean hasConquered();

    /**
     * Reset the AttackManager after a shift of the players.
     */
    void turnShifted();

}