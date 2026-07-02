package it.dpg.maingame.controller.gamecycle.turnmanagement;

import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.model.character.Dice;
import it.dpg.minigames.MinigameType;

import java.util.*;
import java.util.stream.Collectors;

public class TurnManagerImpl implements TurnManager {

    private final List<PlayerController> players;
    private final List<Dice> rewardDices;
    private final GameState state;
    private int remainingTurns;
    private Iterator<PlayerController> iterator;
    private int arrayVal = -1;

    /**
     * @param defaultDice the dice everyone gets in the first turn
     * @param rewardDices the list of dices set as reward, assigned in order of score (following the list order),
     *                    if nPlayers < rewardDices.size() use only the nPlayers highest dices,
     *                    if nPlayers > rewardDices.size() use the last dice for all the remaining players
     */
    public TurnManagerImpl(final Dice defaultDice, final List<Dice> rewardDices, final int nTurns, final Set<PlayerController> playerSet, GameState state) {
        this.state = state;
        this.rewardDices = new ArrayList<>(rewardDices);//in case the list is immutable
        this.remainingTurns = nTurns - 1;
        this.players = new ArrayList<>(playerSet);
        Collections.shuffle(players);
        //set the turns in the characters
        for (int i = 0; i < players.size(); i++) {
            players.get(i).getCharacter().setTurn(i);
            players.get(i).getCharacter().setDice(defaultDice);
        }
        if (rewardDices.size() < playerSet.size()) {//if the condition is true, extend the list of dice with the lowest one
            Dice lowestDice = rewardDices.isEmpty() ? defaultDice : rewardDices.get(rewardDices.size() - 1);
            while (this.rewardDices.size() < playerSet.size()) {
                this.rewardDices.add(lowestDice);
            }
        }
        iterator = players.iterator();
        state.newTurn();
    }

    @Override
    public Iterator<PlayerController> getPlayersIterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public PlayerController next() {
                state.newTurn();
                return iterator.next();
            }
        };
    }

    @Override
    public void nextTurn() {
        if (remainingTurns <= 0) {
            throw new IllegalStateException();
        }
        MinigameType playedMinigame = getMinigameEnum();
        for (PlayerController player : players) {
            player.playMinigame(playedMinigame);
        }
        List<PlayerController> ranking = players.stream()
                .sorted(Comparator.comparingInt(p -> ((PlayerController) p).getCharacter().getMinigameScore()).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < players.size(); i++) {
            ranking.get(i).getCharacter().setDice(rewardDices.get(i));
        }
        remainingTurns--;
        this.iterator = players.iterator();
    }

    private MinigameType getMinigameEnum() {
        var types = MinigameType.values();
        if(arrayVal == -1) {
            arrayVal = new Random().nextInt(types.length);
        } else {
            arrayVal = (arrayVal + 1) % types.length;
        }
        return types[arrayVal];
    }

    @Override
    public boolean hasNextTurn() {
        return remainingTurns > 0;
    }

    @Override
    public List<PlayerController> getPlayers() {
        return List.copyOf(players);
    }
}
