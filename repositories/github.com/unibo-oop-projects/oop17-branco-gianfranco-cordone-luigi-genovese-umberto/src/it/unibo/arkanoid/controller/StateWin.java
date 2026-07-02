package it.unibo.arkanoid.controller;

import java.util.function.Consumer;

/**
 * 
 * This class represent the state WIN.
 *
 */
public final class StateWin extends AbstractGameState implements State {

    private final Runnable nextLevelLoader;

    /**
     * 
     * @param stateChanger
     *            consumer that change current state.
     * @param nextLevelLoader
     *            load next level.
     */
    public StateWin(final Consumer<GameState> stateChanger, final Runnable nextLevelLoader) {
        super(stateChanger);
        this.nextLevelLoader = nextLevelLoader;
    }

    @Override
    public void updateState(final Controller controller) {
        nextLevelLoader.run();
    }

}
