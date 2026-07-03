package model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import model.bonus.BonusCard;
import model.player.Player;
import model.region.Region;
import model.state.State;
import utils.CircularList;
import utils.enumerations.MapType;

/**
 * Captures and externalises a partial snapshot of a Model's subclass internal state, so that the subclass can be
 * serialized or loaded from a file.
 */
public class ModelMemento implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1622861313879468722L;

    private final CircularList<Player> players;
    private final Set<State> states;
    private final Set<Region> regions;
    private final List<BonusCard> cards;
    private final MapType map;

    /**
     * 
     * @param players
     *          The list of players.
     * @param states
     *          The list of states.
     * @param regions
     *          The list of regions.
     * @param cards
     *          The list of cards.
     * @param map
     *          The map type.
     */
    public ModelMemento(final CircularList<Player> players, final Set<State> states, final Set<Region> regions, final List<BonusCard> cards, final MapType map) {
        this.players = players;
        this.states = states;
        this.regions = regions;
        this.cards = cards;
        this.map = map;
    }

    /**
     * 
     * @return
     *          The list of players.
     */
    public CircularList<Player> getPlayers() {
        return this.players;
    }

    /**
     * 
     * @return
     *          The list of states.
     */
    public Set<State> getStates() {
        return this.states;
    }

    /**
     * 
     * @return The list of regions.
     */
    public Set<Region> getRegions() {
        return this.regions;
    }

    /**
     * 
     * @return The list of cards.
     */
    public List<BonusCard> getCards() {
        return this.cards;
    }

    /**
     * 
     * @return 
     *      The MapType.
     */
    public MapType getMap() {
        return this.map;
    }

}
