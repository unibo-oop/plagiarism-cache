package model.state;

import java.util.Collection;

import model.player.Player;

/**
 * 
 * The interface models what a geographical State is in Risk, this interface offers 
 * different methods to access the various fields of the class and methods to add, 
 * subtract and move tanks from one State to another.
 *
 */
public interface State extends StateInfo {

    /**
     * Add a specified number of tanks on this State.
     * @param nTanks
     *           The number of tanks to add on this State.
     * 
     */
    void addTanks(int nTanks);

    /**
     * Method called after an attack has finished and some tanks of this State have been 
     * destroyed.
     * 
     * @param nTanks
     *           The number of tanks that have been destroyed.
     */
    void destroyTanks(int nTanks);

    /**
     * Method used to move a specified number of tanks from one State to another
     * of the same Player.
     * 
     * @param destination
     *            The destination state.
     * @param nTanks
     *            The number of tanks to move.
     */
    void moveTanks(State destination, int nTanks);

    /**
     * 
     * @param states
     *          states which adjoins this one.
     * 
     */
    void addNeighbouringState(Collection<State> states);

    /**
     * sets a new owner for the state.
     * @param player
     *          the new owner of the state
     */
    void setPlayer(Player player);

}
