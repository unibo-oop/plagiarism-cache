package it.unibo.jurassiko.core.impl;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.jurassiko.core.api.PlayerTurn;
import it.unibo.jurassiko.model.player.api.Player;

/**
 * Implementation ot {@link PlayerTurn} interface.
 */
public class PlayerTurnImpl implements PlayerTurn {

    private final List<Player> players;
    private int index;

    /**
     * Constructor for the Player Turn.
     * 
     * @param players List of the Players
     */
    public PlayerTurnImpl(final List<Player> players) {
        this.players = players.stream()
                .sorted((o1, o2) -> o2.getColor().getColorName().compareTo(o1.getColor().getColorName()))
                .collect(Collectors.toList());
        index = 0;
    }

    /**
     * Constructor for the Player Turn.
     * Used to create a copy given a PlayerTurn
     * 
     * @param pt is the PlayerTurn
     */
    public PlayerTurnImpl(final PlayerTurn pt) {
        this.players = pt.getPlayers();
        this.index = this.players.indexOf(pt.getCurrentPlayerTurn());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayerTurn() {
        return players.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goNext() {
        index++;
        if (index >= players.size()) {
            index = 0;
        }
    }

}
