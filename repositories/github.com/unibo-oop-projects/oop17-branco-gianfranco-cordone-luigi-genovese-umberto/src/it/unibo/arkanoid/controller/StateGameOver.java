package it.unibo.arkanoid.controller;

import java.util.function.Consumer;

/**
 * 
 * This class represent the state GAMEOVER.
 *
 */
public final class StateGameOver extends AbstractGameState implements State {

    /**
     * 
     * @param stateChanger
     *            consumer that change current state.
     */
    public StateGameOver(final Consumer<GameState> stateChanger) {
        super(stateChanger);
    }

    @Override
    public void updateState(final Controller controller) {
        this.changeState(GameState.INITIALIZE);
    }

}
