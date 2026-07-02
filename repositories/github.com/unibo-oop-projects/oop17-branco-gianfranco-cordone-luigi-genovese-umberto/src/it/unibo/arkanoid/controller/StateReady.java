package it.unibo.arkanoid.controller;

import java.util.function.Consumer;

/**
 * 
 * This class represent the state READY.
 *
 */
public class StateReady extends AbstractGameState implements State {

    /**
     * 
     * @param stateChanger
     *            consumer that change current state.
     */
    public StateReady(final Consumer<GameState> stateChanger) {
        super(stateChanger);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final Controller controller) {
        controller.getGameLoop().startGameLoop();
        this.changeState(GameState.RUNNING);
    }

}
