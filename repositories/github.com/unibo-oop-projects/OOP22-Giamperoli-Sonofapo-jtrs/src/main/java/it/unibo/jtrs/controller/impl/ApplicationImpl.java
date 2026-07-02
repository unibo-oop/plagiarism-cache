package it.unibo.jtrs.controller.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jtrs.controller.api.Application;
import it.unibo.jtrs.controller.api.GameController;
import it.unibo.jtrs.controller.api.PreviewController;
import it.unibo.jtrs.controller.api.ScoreController;
import it.unibo.jtrs.game.core.api.GameLogic;
import it.unibo.jtrs.game.core.impl.GameEngineImpl;
import it.unibo.jtrs.game.core.impl.GameLogicImpl;
import it.unibo.jtrs.view.impl.ApplicationFrame;
import it.unibo.jtrs.view.impl.ApplicationPanel;
import it.unibo.jtrs.model.api.GameModel.GameState;

/**
 * Application implementation.
 */
public class ApplicationImpl implements Application {

    private final GameLogic logic;
    private final ApplicationPanel panel;
    private final ScoreController sC;
    private final PreviewController pC;
    private final GameController gC;
    private boolean isRunning;

    /**
     * Constructor.
     */
    public ApplicationImpl() {

        this.sC = new ScoreControllerImpl();
        this.pC = new PreviewControllerImpl();
        this.gC = new GameControllerImpl();

        this.logic = new GameLogicImpl(this);
        this.panel = new ApplicationPanel(this);

        this.isRunning = true;
        new ApplicationFrame(this.panel);
        (new GameEngineImpl(this)).mainLoop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.logic.timeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interrupt() {
        this.logic.requestInterrupt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void terminate() {
        this.isRunning = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        this.panel.redraw();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScoreController getScoreController() {
        return this.sC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PreviewController getPreviewController() {
        return this.pC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(justification = "GameController assure copy of its values")
    public GameController getGameController() {
        return this.gC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getState() {
        return this.logic.getState();
    }

}
