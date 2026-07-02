package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.RandomSpawnStrategy;
import it.unibo.javajump.model.level.spawn.SpawnStrategy;

import static it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl.spawnPlatformBelowPlayer;
import static it.unibo.javajump.utility.Constants.INITIAL_PLATFORMS_NUMBER;
import static it.unibo.javajump.utility.Constants.INITIAL_Y_SPAWN_OFFSET;
import static it.unibo.javajump.utility.Constants.PROCEDURAL_PLATFORMS_NUMBER;
import static it.unibo.javajump.utility.Constants.SPAWN_THRESHOLD;
import static it.unibo.javajump.utility.Constants.TOP_PLATFORM_Y_INIT;

/**
 * The implementation of SpawnManager interface.
 */
public final class SpawnManagerImpl implements SpawnManager {

    private final SpawnStrategy spawnStrategy;
    private float topPlatformY;

    /**
     * Instantiates a new Spawn manager.
     *
     * @param spawnStrategy the spawn strategy currently used by the level generation
     */
    public SpawnManagerImpl(final SpawnStrategy spawnStrategy) {
        this.spawnStrategy = spawnStrategy;
        this.topPlatformY = TOP_PLATFORM_Y_INIT;
    }

    /**
     * {@inheritDoc} The implemented method first generates a platform below the player,
     * then calls the spawnBatch method in spawn strategy to generate the initial level objects.
     * TopPlatformY is then updated.
     */
    @Override
    public void generateInitialLevel(final GameModel model) {
        spawnPlatformBelowPlayer(model, getFactory());
        final float startY = model.getScreenHeight() - INITIAL_Y_SPAWN_OFFSET;
        spawnStrategy.spawnBatch(model, startY, INITIAL_PLATFORMS_NUMBER);
        this.topPlatformY = spawnStrategy.returnCurrentY();
    }

    /**
     * {@inheritDoc} The generation is done by calling the spawnBatch method in spawn strategy, checking the current
     * distance between the Player and the TopPlatformY. If the threshold is reached, a new batch is generated.
     */
    @Override
    public void generateOnTheFly(final GameModel model) {
        final float playerY = model.getPlayer().getY();
        final float gap = playerY - topPlatformY;

        if (gap < SPAWN_THRESHOLD) {
            spawnStrategy.spawnBatch(model, topPlatformY, PROCEDURAL_PLATFORMS_NUMBER);
            final float newTop = spawnStrategy.returnCurrentY();

            if (newTop < topPlatformY) {
                topPlatformY = newTop;
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.topPlatformY = TOP_PLATFORM_Y_INIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpawnStrategy getSpawnStrategy() {
        return this.spawnStrategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectFactory getFactory() {
        if (spawnStrategy instanceof RandomSpawnStrategy) {
            return spawnStrategy.getFactory();
        }
        return null;
    }
}
