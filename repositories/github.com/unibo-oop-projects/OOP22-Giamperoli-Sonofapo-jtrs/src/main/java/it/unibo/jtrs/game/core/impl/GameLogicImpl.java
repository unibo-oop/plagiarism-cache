package it.unibo.jtrs.game.core.impl;

import it.unibo.jtrs.controller.api.Application;
import it.unibo.jtrs.controller.api.GameController;
import it.unibo.jtrs.controller.api.PreviewController;
import it.unibo.jtrs.controller.api.ScoreController;
import it.unibo.jtrs.game.core.api.GameLogic;
import it.unibo.jtrs.model.api.GameModel.GameState;
import it.unibo.jtrs.model.api.GameModel.Interaction;
import it.unibo.jtrs.utils.AudioEngine;
import it.unibo.jtrs.utils.Chronometer;

/**
 * GameLogic implementation.
 */
public class GameLogicImpl implements GameLogic {

    private static final int IDLE_RATE = 800;
    private static final int MIN_IDLE = 150;
    private static final int RATE_FACTOR = 50;

    private final Chronometer chrono;
    private GameState gameState;

    // used for code readability purposes
    private final GameController gC;
    private final PreviewController pC;
    private final ScoreController sC;

    /**
     * Constructor.
     *
     * @param application the application this logic should operate on
     */
    public GameLogicImpl(final Application application) {
        this.gC = application.getGameController();
        this.pC = application.getPreviewController();
        this.sC = application.getScoreController();

        this.gC.changePiece(this.pC.getCurrentTetromino());
        this.pC.nextTetromino();
        this.gameState = GameState.START;
        this.chrono = new Chronometer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timeUpdate() {
        final var idleTime = IDLE_RATE - (MIN_IDLE + this.sC.getLevel() * RATE_FACTOR);

        if (this.gameState == GameState.RUNNING
            && this.chrono.elapsed() > Math.max(MIN_IDLE, idleTime)) {

            this.chrono.reset();
            if (!this.gC.advance(Interaction.DOWN)) {
                this.sC.evaluate(this.gC.deleteRows());
                if (this.gC.changePiece(this.pC.getCurrentTetromino())) {
                    this.pC.nextTetromino();
                } else {
                    this.gameState = GameState.OVER;
                    AudioEngine.pause();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestInterrupt() {
        switch (this.gameState) {
            case START:
            case PAUSE:
                this.gameState = GameState.RUNNING;
                AudioEngine.play();
                break;
            case RUNNING:
                this.gameState = GameState.PAUSE;
                AudioEngine.pause();
                break;
            default:
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getState() {
        return this.gameState;
    }
}

