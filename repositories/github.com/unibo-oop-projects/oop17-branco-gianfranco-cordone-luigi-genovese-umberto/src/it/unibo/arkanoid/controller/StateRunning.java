package it.unibo.arkanoid.controller;

import java.util.function.Consumer;

/**
 * 
 * This class represent the state RUNNING.
 *
 */
public class StateRunning extends AbstractGameState implements State {

    /**
     * 
     * @param stateChanger
     *            consumer that change current state.
     */
    public StateRunning(final Consumer<GameState> stateChanger) {
        super(stateChanger);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final Controller controller) {
        this.changeState(GameState.INITIALIZE);
    }

}
