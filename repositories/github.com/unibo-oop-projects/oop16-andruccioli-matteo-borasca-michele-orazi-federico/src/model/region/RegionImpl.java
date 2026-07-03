package model.region;

import java.util.HashSet;
import java.util.Set;

import model.player.PlayerInfo;
import model.state.State;
import model.state.StateInfo;

/**
 * 
 * Class representing the concept of region. A Region is made up of States
 *
 */
public class RegionImpl implements Region {

    private static final long serialVersionUID = -7637202951707060092L;
    private final String name;
    private final int bonus;
    private final Set<StateInfo> associatedStates;

    /**
     * constructor for a region.
     * 
     * @param name
     *            the name of the region.
     * @param bonus
     *            n° of tanks gained at the beginning of each turn by the owner
     *            of the region.
     * 
     */

    public RegionImpl(final String name, final int bonus) {
        super();
        this.name = name;
        this.bonus = bonus;
        this.associatedStates = new HashSet<>();
    }

    @Override
    public int getBonusTanks() {
        return this.bonus;
    }

    @Override
    public Set<StateInfo> getStates() {
        return new HashSet<>(this.associatedStates);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addAssociatedState(final State state) {
        if (state == null) {
            throw new IllegalArgumentException("state must be non null object");
        }
        this.associatedStates.add(state);
    }

    @Override
    public boolean checkOwner(final PlayerInfo player) {
        if (player == null) {
            throw new IllegalArgumentException("player must be non null object");
        }
        return this.associatedStates.stream().filter(s->!player.getColor().equals(s.getPlayer().getColor())).count() > 0 ? false : true;
    }

    @Override
    public String toString() {
        return "Name: " + this.name;
    }
}
