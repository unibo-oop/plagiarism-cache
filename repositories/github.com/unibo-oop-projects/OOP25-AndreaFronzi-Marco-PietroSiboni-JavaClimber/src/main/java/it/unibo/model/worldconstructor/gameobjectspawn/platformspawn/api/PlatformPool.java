package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api;

import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;

/**
 * Interface representing a pool of platforms that can be used to spawn platforms in the game.
 */
public interface PlatformPool {

    /**
     * Gets the pool of platforms, which is a list of pairs containing a probability and a platform creation function.
     * 
     * @return the list of pairs representing the platform pool
     */
    List<Pair<Double, BiConsumer<Double, Vector2d>>> getPlatformPool();

    /**
     * Gets the width of the platform pool.
     * 
     * @return the width of the platform pool
     */
    double getWidth();

    /**
     * Gets the height of the platform pool.
     * 
     * @return the height of the platform pool
     */
    double getHeight();
}
