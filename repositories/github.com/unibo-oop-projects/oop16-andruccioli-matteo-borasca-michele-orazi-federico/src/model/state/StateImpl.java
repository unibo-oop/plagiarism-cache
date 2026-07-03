package model.state;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import model.player.Player;
import model.region.Region;

/**
 * class representing the concept of State.
 */
public class StateImpl implements State {

    private static final long serialVersionUID = -644430206458056334L;
    private static final int MINIMUM_NUM_OF_TANKS = 1;
    private final String name;
    private int nTanks;
    private final Region associatedRegion;
    private Player owner;
    private final Set<State> neighbouringStates;

    /**
     * constructor for a state.
     * 
     * @param name
     *            name of the state
     * @param associatedRegion
     *            reference to the region associated with this state
     */
    public StateImpl(final String name, final Region associatedRegion) {
        this.name = name;
        this.nTanks = 0;
        this.associatedRegion = associatedRegion;
        this.owner = null;
        this.neighbouringStates = new HashSet<>();
    }

    @Override
    public int getTanks() {
        return this.nTanks;
    }

    @Override
    public Player getPlayer() {
        return this.owner;
    }

    @Override
    public void addTanks(final int nTanks) {
        if (nTanks <= 0) {
            throw new IllegalArgumentException("nTank must be positive");
        }
        this.nTanks += nTanks;
    }

    @Override
    public void destroyTanks(final int nTanks) {
        if (nTanks <= 0) {
            throw new IllegalArgumentException("nTank must be positive");
        }
        if (nTanks > this.nTanks) {
            throw new IllegalArgumentException("can't destroy more tanks than num of tanks on this state");
        }
        this.nTanks -= nTanks;
    }

    @Override
    public void moveTanks(final State destination, final int nTanks) {
        if (nTanks <= 0) {
            throw new IllegalArgumentException("nTank must be positive");
        }
        if (destination == null) {
            throw new IllegalArgumentException("destination must be a non null object");
        }
        if (nTanks > (this.nTanks - MINIMUM_NUM_OF_TANKS)) {
            throw new IllegalArgumentException("Num of tanks destroyed out of bounds");
        }
        this.nTanks -= nTanks;
        destination.addTanks(nTanks);
    }

    @Override
    public Set<State> getNeighbouringStates() {
        return new HashSet<>(this.neighbouringStates);
    }

    @Override
    public Region getRegion() {
        return this.associatedRegion;
    }

    @Override
    public void addNeighbouringState(final Collection<State> states) {
        if (states == null) {
            throw new IllegalArgumentException("states must be non null");
        }
        if (states.isEmpty()) {
            throw new IllegalArgumentException("states can't be empty");
        }
        this.neighbouringStates.addAll(states);
    }

    @Override
    public void setPlayer(final Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player must be a non null object");
        }
        this.owner = player;
    }

    @Override
    public int availableTanks() {
        return (this.nTanks - MINIMUM_NUM_OF_TANKS);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public State forwarder() {
        return this;
    }

    @Override
    public String toString() {
        return "State: " + this.name + "  Owner: " + this.owner.toString() + "  nTanks: " + this.nTanks;
    }
}
