package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.model.api.GameMatchContext;
import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.api.LevelManager;

/**
 * Implementation of the {@link LevelManager} interface that manages level configurations and game match contexts.
 */
public class LevelManagerImpl implements LevelManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelConfig getLevelSetup(final int level) {
        return LevelFactory.createLevel(level);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMatchContext startMatch(final int level) {
        return LevelFactory.createGameMatchContext(level);
    }
}
