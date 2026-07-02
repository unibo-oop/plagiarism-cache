package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Pair;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.PlatformCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.PlatformPool;

/**
 * Implementation of the {@link PlatformCreator} interface.
 */
public class PlatformCreatorImpl implements PlatformCreator {

    /**
     * The platform pool is a list of pairs, where the first element is a double
     * representing the chance of spawning a platform, and the second element is a
     * BiConsumer.
     */
    private List<Pair<Double, BiConsumer<Double, Vector2d>>> platformPool;

    /**
     * Random number generator to determine which platform to spawn based on the
     * given chance.
     */
    private final Random random;

    /**
     * Constructs a PlatformCreatorImpl with the given platform pool.
     *
     * @param platformPool the platform pool to use for creating platforms
     */
    public PlatformCreatorImpl(final PlatformPool platformPool) {
        this.platformPool = platformPool.getPlatformPool();
        this.random = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPlatform(final double chance, final Vector2d pos) {
        for (final var platform : platformPool) {
            if (chance <= platform.getX()) {
                platform.getY().accept(random.nextDouble(1.0), new Vector2dImpl(pos.getX(), pos.getY()));
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlatformPool(final PlatformPool platformPool) {
        this.platformPool = platformPool.getPlatformPool();
    }

}
