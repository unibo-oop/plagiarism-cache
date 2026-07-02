package it.unibo.javajump.model.level.spawn;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.collectiblespawn.CollectiblesSpawner;
import it.unibo.javajump.model.level.spawn.collectiblespawn.CollectiblesSpawnerImpl;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;
import it.unibo.javajump.model.level.spawn.platformspawn.PlatformSpawner;
import it.unibo.javajump.model.level.spawn.platformspawn.PlatformSpawnerImpl;
import it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.GAP_EASY_ADDENDUM;
import static it.unibo.javajump.utility.Constants.GAP_HARD_ADDENDUM;
import static it.unibo.javajump.utility.Constants.GAP_INIT;
import static it.unibo.javajump.utility.Constants.MAX_PLATFORM_WIDTH;
import static it.unibo.javajump.utility.Constants.SPAWN_Y_INIT;

/**
 * The type Random spawn strategy.
 */
public final class RandomSpawnStrategy implements SpawnStrategy {

    private final GameObjectFactory factory;
    private final Random rand;
    private final float minPlatformYSpacing;
    private final float maxPlatformYSpacing;
    private float currentY;
    private final CollectiblesSpawner collectiblesSpawner;
    private final PlatformSpawner platformSpawner;

    /**
     * Instantiates a new Random spawn strategy.
     *
     * @param factory    the factory
     * @param minSpacing the min spacing
     * @param maxSpacing the max spacing
     * @param coinChance the coin chance
     */
    public RandomSpawnStrategy(final GameObjectFactory factory,
                               final float minSpacing,
                               final float maxSpacing,
                               final float coinChance
                               ) {
        this.factory = factory;
        this.rand = new Random();
        this.minPlatformYSpacing = minSpacing;
        this.maxPlatformYSpacing = maxSpacing;
        this.currentY = SPAWN_Y_INIT;
        this.collectiblesSpawner = new CollectiblesSpawnerImpl(factory, coinChance);
        this.platformSpawner = new PlatformSpawnerImpl(factory);
    }

    @Override
    public void spawnBatch(final GameModel model, final float startY, final int numberOfPlatforms) {
        final DifficultyState diff = model.getDifficultyManager().getCurrentDifficulty();
        currentY = startY;

        for (int i = 0; i < numberOfPlatforms; i++) {
            final float gap = updateSpawnGap(diff);
            currentY -= gap;

            final float x = rand.nextFloat() * (model.getScreenWidth() - (float) MAX_PLATFORM_WIDTH);

            final Platform p = platformSpawner.spawnPlatform(x, currentY, model.getScreenWidth(), diff);
            model.getGameObjects().add(p);

            final float platformWidth = p.getWidth();
            collectiblesSpawner.spawnCollectible(model, x, currentY, platformWidth, p);
        }
    }

    private float updateSpawnGap(final DifficultyState diff) {
        float gap = GAP_INIT;
        if (null != diff) {
            // CHECKSTYLE: MissingSwitchDefault OFF
            // switch does not need a default case
            switch (diff) {
                case EASY, MEDIUM ->
                        gap = SpawnUtilsImpl.randomInRange(rand,
                                minPlatformYSpacing - GAP_EASY_ADDENDUM,
                                maxPlatformYSpacing - GAP_HARD_ADDENDUM);
                case HARD, VERY_HARD ->
                        gap = SpawnUtilsImpl.randomInRange(rand,
                                minPlatformYSpacing,
                                maxPlatformYSpacing - GAP_EASY_ADDENDUM);
                case HELL ->
                        gap = SpawnUtilsImpl.randomInRange(rand,
                                minPlatformYSpacing + GAP_EASY_ADDENDUM,
                                maxPlatformYSpacing);
            }
            // CHECKSTYLE: MissingSwitchDefault ON
        }
        return gap;
    }

    @Override
    public float returnCurrentY() {
        return this.currentY;
    }

    @Override
    public GameObjectFactory getFactory() {
        return this.factory;
    }
}
