package it.dpg.maingame.controller.gamecycle.turnmanagement;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

public class GameStateImpl implements GameState {

    private boolean gameStarted = false; //true if new turn has been called at least once
    private volatile boolean diceThrown; //booleans are volatile to make parallel thread access easier
    private volatile boolean isChoosing;
    private Pair<Integer, Integer> lastDirectionChosen;
    private boolean hasChosenDirection = false;
    private boolean turnPaused = false;

    public GameStateImpl() {
    }

    @Override
    public void newTurn() {
        gameStarted = true;
        diceThrown = false;
        isChoosing = false;
        turnPaused = false;
        hasChosenDirection = false;
    }

    @Override
    public void setDiceThrown(final boolean wasThrown) {
        checkGameStarted();

        this.diceThrown = wasThrown;
    }

    @Override
    public boolean wasDiceThrown() {
        checkGameStarted();

        return this.diceThrown;
    }

    @Override
    public void setChoice(final boolean isChoosing) {
        checkGameStarted();

        this.isChoosing = isChoosing;
    }

    @Override
    public boolean isChoosing() {
        checkGameStarted();

        return this.isChoosing;
    }

    @Override
    public Optional<Pair<Integer, Integer>> getLastDirectionChoice() {
        checkGameStarted();
        synchronized (this) {
            if (hasChosenDirection) {
                return Optional.of(this.lastDirectionChosen);
            }
            return Optional.empty();
        }
    }

    @Override
    public void setLastDirectionChoice(final Pair<Integer, Integer> direction) {
        checkGameStarted();
        synchronized (this) {
            this.hasChosenDirection = true;
            this.lastDirectionChosen = direction;
        }
    }

    @Override
    public void setTurnPause(final boolean isPaused) {
        this.turnPaused = isPaused;
    }

    @Override
    public boolean isPaused() {
        return this.turnPaused;
    }

    private void checkGameStarted() {
        if (!gameStarted) {
            throw new IllegalStateException("new turn was never called");
        }
    }
}
