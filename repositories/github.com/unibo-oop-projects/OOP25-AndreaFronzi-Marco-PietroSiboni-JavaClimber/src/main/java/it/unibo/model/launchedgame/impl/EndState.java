package it.unibo.model.launchedgame.impl;

import it.unibo.model.launchedgame.api.AbstractLaunchedState;
import it.unibo.model.launchedgame.api.LaunchedGame;

/**
 * Represents the final state of a game session.
 * Handles end-game logic like saving scores or returning to menu.
 */
public class EndState extends AbstractLaunchedState {

    /**
     * Constructs a new EndState.
     * 
     * @param launchedGame the game context
     */
    public EndState(final LaunchedGame launchedGame) {
        super(launchedGame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.launchedGame.setRunning(false);
        final int collectedCoins = this.launchedGame.getMenu().getScoreManager().getCoins();
        this.launchedGame.getMenu().getInventory().addCoins(collectedCoins);
        this.launchedGame.getMenu().getInventory().updateConsumables();
        this.launchedGame.getMenu().getMainController().openEndView();
        this.launchedGame.getMenu().getMainController().saveProgress();
    }
}
