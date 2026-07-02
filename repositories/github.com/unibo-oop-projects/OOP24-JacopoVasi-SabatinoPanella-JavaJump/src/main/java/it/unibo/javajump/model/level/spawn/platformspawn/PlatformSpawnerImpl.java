package it.unibo.javajump.model.level.spawn.platformspawn;

import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;

import java.util.Random;

import static it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl.randomInRange;
import static it.unibo.javajump.utility.Constants.BOUNCE_FACTOR_MAX;
import static it.unibo.javajump.utility.Constants.BOUNCE_FACTOR_MIN;

/**
 * The implementation of the PlatformSpawner interface, used to spawn platforms.
 */
public final class PlatformSpawnerImpl implements PlatformSpawner {

    private final GameObjectFactory factory;
    private final Random rand;

    /**
     * Instantiates a new Platform spawner.
     *
     * @param factory the factory used to create the platforms
     */
    public PlatformSpawnerImpl(final GameObjectFactory factory) {
        this.factory = factory;
        this.rand = new Random();
    }

    /**
     * {@inheritDoc} The platform generation is based on the cumulative probabilities of each platform spawn chances,
     * according to the difficulty level.
     */
    @Override
    public Platform spawnPlatform(final float x, final float y, final int screenWidth, final DifficultyState difficulty) {
        final float chance = rand.nextFloat();
        final float breakableChance = difficulty.getBreakableChance();
        final float movingChance = difficulty.getMovingChance();

        final float thresholdBounce = difficulty.getBounceChance();
        final float thresholdBreakable = thresholdBounce + breakableChance;
        final float thresholdMoving = thresholdBreakable + movingChance;

        if (chance < thresholdBounce) {
            return factory.createBouncePlatform(x, y, randomInRange(rand, BOUNCE_FACTOR_MIN, BOUNCE_FACTOR_MAX));
        } else if (chance < thresholdBreakable) {
            return factory.createBreakablePlatform(x, y);
        } else if (chance < thresholdMoving) {
            return factory.createMovingPlatform(x, y, screenWidth);
        } else {
            return factory.createRandomPlatform(x, y);
        }
    }
}
