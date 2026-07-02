package it.unibo.model.launchedgame.impl;

import it.unibo.model.launchedgame.api.AbstractLaunchedState;
import it.unibo.model.launchedgame.api.LaunchedGame;

/**
 * Represents the state where the game is paused.
 * Suspends normal gameplay logic.
 */
public class PauseState extends AbstractLaunchedState {

    /**
     * Constructs a new PauseState.
     * 
     * @param launchedGame the game context
     */
    public PauseState(final LaunchedGame launchedGame) {
        super(launchedGame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.launchedGame.setRunning(false);
        this.launchedGame.getMenu().getMainController().openPauseView();
    }
}
