package it.dpg.maingame.controller.gamecycle.turnmanagement;

import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.model.character.Dice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TurnManagerBuilderImpl implements TurnManagerBuilder {

    private final int nTurns;
    private final Set<PlayerController> players = new HashSet<>();
    private final GameState state;
    private Dice defaultDice;
    private List<Dice> rewardDices = new ArrayList<>();

    public TurnManagerBuilderImpl(final int nTurns, final GameState state) {
        this.nTurns = nTurns;
        this.state = state;
    }

    @Override
    public TurnManagerBuilder setDefaultDice(final Dice defaultDice) {
        this.defaultDice = defaultDice;
        return this;
    }

    @Override
    public TurnManagerBuilder setRewardDices(final List<Dice> rewardDices) {
        this.rewardDices = rewardDices;
        return this;
    }

    @Override
    public TurnManagerBuilder addPlayer(final PlayerController player) {
        this.players.add(player);
        return this;
    }

    @Override
    public TurnManager build() {
        if (defaultDice == null || rewardDices == null || players.isEmpty()) {
            throw new IllegalStateException();
        }
        return new TurnManagerImpl(defaultDice, rewardDices, nTurns, players, state);
    }
}
