package it.unibo.arkanoid.controller;

import java.util.function.Consumer;

/**
 * 
 * Base implementation of the {@link State} interface.
 *
 */
public abstract class AbstractGameState implements State {

    private final Consumer<GameState> stateChanger;

    /**
     * 
     * @param stateChanger
     *            function used to switch state
     */
    public AbstractGameState(final Consumer<GameState> stateChanger) {
        this.stateChanger = stateChanger;
    }

    /**
     * 
     * @param gameState
     *            the new state
     */
    protected void changeState(final GameState gameState) {
        stateChanger.accept(gameState);
    }

}
