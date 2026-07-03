package model.region;

import java.io.Serializable;
import java.util.Set;

import model.player.PlayerInfo;
import model.state.State;
import model.state.StateInfo;

/**
 * 
 * The interface models a Region that is formed by a set of States.
 *
 */
public interface Region extends Serializable {

    /**
     * 
     * @return The number of bonus tanks for this region.
     * 
     */
    int getBonusTanks();

    /**
     * 
     * @return The set of States that forms the Region.
     * 
     */
    Set<StateInfo> getStates();

    /**
     * 
     * @return The name of the region.
     * 
     */
    String getName();

    /**
     * 
     * @param state
     *            state contained in the region.
     * 
     */
    void addAssociatedState(State state);

    /**
     * 
     * @param player
     *            player who might have conquered the whole region.
     * 
     * @return a boolean defining if the player owns the whole region
     * 
     */
    boolean checkOwner(PlayerInfo player);

}
