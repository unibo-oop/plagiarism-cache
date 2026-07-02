package it.unibo.model.worldconstructor.gameobjectspawn.api;

import java.util.List;
import java.util.function.Function;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Pair;

/**
 * Interface representing a pool of game entities available for spawning.
 * Defines different pools for platforms, enemies, gadgets, and money,
 * associating them with their spawn probabilities.
 */
public interface SpawnPool {

    /**
     * Retrieves the pool of available static platforms.
     * 
     * @return a list of pairs containing static platform factories and their spawn
     *         chances
     */
    List<Pair<Double, Function<Vector2d, Platform>>> getStaticPlatformPool();

    /**
     * Retrieves the pool of available moving platforms.
     * 
     * @return a list of pairs containing moving platform factories and their spawn
     *         chances
     */
    List<Pair<Double, Function<Vector2d, Platform>>> getMovingPlatformPool();

    /**
     * Retrieves the pool of available on-touch platforms.
     * 
     * @return a list of pairs containing on-touch platform factories and their
     *         spawn chances
     */
    List<Pair<Double, Function<Vector2d, Platform>>> getOnTouchPlatformPool();

    /**
     * Retrieves the pool of available enemies (monsters).
     * 
     * @return a list of pairs containing enemy factories and their spawn chances
     */
    List<Pair<Double, Function<Vector2d, Enemy>>> getMonsterPool();

    /**
     * Retrieves the pool of available gadgets.
     * 
     * @return a list of pairs containing gadget factories and their spawn chances
     */
    List<Pair<Double, Function<Vector2d, Gadget>>> getGadgetPool();

    /**
     * Retrieves the pool of available coins (money).
     * 
     * @return a list of pairs containing coin factories and their spawn chances
     */
    List<Pair<Double, Function<Vector2d, Coin>>> getMoneyPool();

}
