package it.tbt.engine.impl;

import it.tbt.engine.api.Game;
import it.tbt.engine.api.GameLoop;

/**
 * Implementation of the GameLoop based on a fixed time slice on every render.
 */
public class FixedTimeGameLoop implements GameLoop {

    private static final long SECOND_IN_MILLISECOND = 1_000_000_000;

    private static final long TARGET_FPS = 60;
    private static final long TIME_SLICE = SECOND_IN_MILLISECOND / TARGET_FPS;
    private long lastUpdateTime;
    private long timeAccumulator;
    private Boolean updated = false;
    private final Game game;

    /**
     * @param game the game object on which this loops calls the major operations
     */
    public FixedTimeGameLoop(final Game game) {
        super();
        this.game = game;
        this.lastUpdateTime = System.nanoTime();
        this.game.initialize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loop() {
        final long elapsedTime = System.nanoTime() - lastUpdateTime;

        lastUpdateTime += elapsedTime;
        timeAccumulator += elapsedTime;

        if (this.game.handleInput()) {
            updated = true;
        }

        while (timeAccumulator > TIME_SLICE) {
            timeAccumulator -= TIME_SLICE;
        }

        if (updated) {
            this.game.render();
            updated = false;
        }
    }

}
