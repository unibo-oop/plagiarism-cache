package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl;

import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldconstructor.gameobjectspawn.api.SpawnPoolCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.AbstractPlatformPool;

/**
 * Implementation of {@link AbstractPlatformPool} for the medium level.
 */
public class PlatformPoolMedium extends AbstractPlatformPool {

    private static final double STATIC_PLATFORM_CHANCE = 0.2;
    private static final double MOVING_PLATFORM_CHANCE = 0.4;
    private static final double ON_TOUCH_PLATFORM_CHANCE = 1.0;

    /**
     * Constructor for PlatformPoolMedium.
     *
     * @param spawnPoolCreator the SpawnPoolCreator to use for creating game
     *                         objects.
     * 
     * @param width            the width of the game world.
     * @param height           the height of the game world.
     */
    public PlatformPoolMedium(final SpawnPoolCreator spawnPoolCreator, final double width, final double height) {
        super(spawnPoolCreator, width, height);
        this.createPlatform();
    }

    private void createPlatform() {
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(STATIC_PLATFORM_CHANCE,
                        this.spawnPoolCreator::createStaticPlatform));
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(MOVING_PLATFORM_CHANCE,
                        this.spawnPoolCreator::createMovingPlatform));
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(ON_TOUCH_PLATFORM_CHANCE,
                        this.spawnPoolCreator::createOnTouchPlatform));
    }

}
