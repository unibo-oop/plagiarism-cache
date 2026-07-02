package it.unibo.arkanoid.controller;

import java.util.function.Consumer;

/**
 * 
 * This class represents the state for when the player completes all the levels.
 *
 */
public final class StateGameFinished extends AbstractGameState implements State {

    /**
     * 
     * @param stateChanger
     *                  consumer that change current state.
     */
    public StateGameFinished(final Consumer<GameState> stateChanger) {
        super(stateChanger);
    }

    @Override
    public void updateState(final Controller controller) {
       this.changeState(GameState.INITIALIZE);
    }

}
