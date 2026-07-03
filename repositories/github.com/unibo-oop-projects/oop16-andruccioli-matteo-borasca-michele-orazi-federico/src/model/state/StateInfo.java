package model.state;

import java.io.Serializable;
import java.util.Set;

import model.player.Player;
import model.region.Region;

/**
 * 
 * An interface that makes accessible the informations of a State.
 *
 */
public interface StateInfo extends Serializable {

    /**
     * @return The name of this State.
     */
    String getName();

    /**
     * @return The number of tanks on this State.
     */
    int getTanks();

    /**
     * @return The player owner of this State.
     */
    Player getPlayer();

    /**
     * @return the Region which contains the state.
     */
    Region getRegion();

    /**
     * @return A copy of the set of the Neighbouring States.
     */
    Set<State> getNeighbouringStates();

    /**
     * @return An instance of the extended interface.
     */
    State forwarder();

    /**
     *@return n° of available tanks.
     */
    int availableTanks();
}
