package it.dpg.maingame.controller.gamecycle;

import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.model.character.Difficulty;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameCycleBuilderImpl implements GameCycleBuilder {

    private final List<Dice> rewardDices = new ArrayList<>();
    private final Set<String> humanPlayers = new HashSet<>();
    private final Set<Pair<String, Difficulty>> cpuPlayers = new HashSet<>();
    private int nTurns = 0;
    private Dice defaultDice;

    @Override
    public GameCycleBuilder setNTurns(int nTurns) {
        if (nTurns < 1) {
            throw new IllegalArgumentException();
        }
        this.nTurns = nTurns;
        return this;
    }

    @Override
    public GameCycleBuilder setDefaultDice(Dice defaultDice) {
        this.defaultDice = defaultDice;
        return this;
    }

    @Override
    public GameCycleBuilder addRewardDice(Dice rewardDice) {
        rewardDices.add(rewardDice);
        return this;
    }

    @Override
    public GameCycleBuilder addHumanPlayer(String name) {
        humanPlayers.add(name);
        return this;
    }

    @Override
    public GameCycleBuilder addCpu(String name, Difficulty difficulty) {
        cpuPlayers.add(new ImmutablePair<>(name, difficulty));
        return this;
    }

    @Override
    public GameCycle build() {
        if (defaultDice == null || rewardDices.isEmpty() || humanPlayers.isEmpty()) {
            throw new IllegalStateException("gamecycle fields not set properly");
        }
        return new GameCycleImpl(nTurns, defaultDice, rewardDices, humanPlayers, cpuPlayers);
    }
}
