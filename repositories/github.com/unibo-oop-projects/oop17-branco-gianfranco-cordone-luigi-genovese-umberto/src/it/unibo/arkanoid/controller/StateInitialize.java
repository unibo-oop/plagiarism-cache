package it.unibo.arkanoid.controller;

import java.util.function.Consumer;

/**
 * 
 * This class represent the initialization of level's sequence.
 *
 */
public final class StateInitialize extends AbstractGameState implements State {

    private final Runnable nextSession;

    /**
     * 
     * @param stateChanger
     *            consumer that change current state.
     * @param nextSession
     *            runnable that initialize the levels iterator.
     */
    public StateInitialize(final Consumer<GameState> stateChanger, final Runnable nextSession) {
        super(stateChanger);
        this.nextSession = nextSession;
    }

    @Override
    public void updateState(final Controller controller) {
        this.nextSession.run();
    }

}
