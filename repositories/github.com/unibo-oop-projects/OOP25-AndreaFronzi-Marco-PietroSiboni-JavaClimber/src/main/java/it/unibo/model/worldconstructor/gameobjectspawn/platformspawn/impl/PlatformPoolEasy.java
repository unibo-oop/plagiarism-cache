package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl;

import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldconstructor.gameobjectspawn.api.SpawnPoolCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.AbstractPlatformPool;

/**
 * Implementation of {@link AbstractPlatformPool} for the easy level.
 */
public class PlatformPoolEasy extends AbstractPlatformPool {

    private static final double STATIC_PLATFORM_CHANCE = 1.0;

    /**
     * Constructor for PlatformPoolEasy.
     *
     * @param spawnPoolCreator the SpawnPoolCreator to create platforms
     * @param width            the width of the game world
     * @param height           the height of the game world
     */
    public PlatformPoolEasy(final SpawnPoolCreator spawnPoolCreator, final double width, final double height) {
        super(spawnPoolCreator, width, height);
        this.createPlatform();
    }

    private void createPlatform() {
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(STATIC_PLATFORM_CHANCE,
                        this.spawnPoolCreator::createStaticPlatform));
    }

}
