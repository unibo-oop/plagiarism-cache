package it.unibo.jtrs.game.core.impl;

import java.io.IOException;
import java.util.logging.Logger;

import it.unibo.jtrs.controller.api.Application;
import it.unibo.jtrs.game.core.api.GameEngine;
import it.unibo.jtrs.utils.AudioEngine;

/**
 * GameEngine implementation.
 */
public class GameEngineImpl implements GameEngine {

    private static final int FRAMES = 60;
    private static final long PERIOD = (long) (1.0 / GameEngineImpl.FRAMES * 1000);

    private final Application application;

    /**
     * Constructor.
     *
     * @param application the application to manage
     */
    public GameEngineImpl(final Application application) {
        this.application = application;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mainLoop() {
        try {
            AudioEngine.load("track-a.wav");
            while (this.application.isRunning()) {
                this.application.update();
                this.application.redraw();
                Thread.sleep(GameEngineImpl.PERIOD);
            }
            AudioEngine.stop();
        } catch (IOException | InterruptedException e) {
            final Logger logger = Logger.getLogger(this.getClass().getName());
            logger.severe(e.getMessage());
        } finally {
            System.exit(0);
        }
    }

}
