package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.SpawnStrategy;

/**
 * The interface that describes a Spawn manager, that handles level generation.
 */
public interface SpawnManager {
    /**
     * Generates the initial base level.
     *
     * @param model the GameModel to add objects to
     */
    void generateInitialLevel(GameModel model);

    /**
     * Generates the level procedurally, on the fly.
     *
     * @param model the GameModel to add objects to
     */
    void generateOnTheFly(GameModel model);

    /**
     * Resets the SpawnManager.
     */
    void reset();

    /**
     * Gets the currently used spawn strategy.
     *
     * @return the current spawn strategy
     */
    SpawnStrategy getSpawnStrategy();

    /**
     * Gets the factory used to create the objects.
     *
     * @return the factory
     */
    GameObjectFactory getFactory();
}
