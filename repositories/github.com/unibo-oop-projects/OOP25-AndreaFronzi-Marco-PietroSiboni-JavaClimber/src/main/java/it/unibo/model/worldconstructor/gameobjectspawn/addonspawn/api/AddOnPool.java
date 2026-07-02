package it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api;

import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Pair;

/**
 * This interface represents a pool of add-ons that can be spawned on platforms
 * in the game.
 * Each add-on has a certain chance to be spawned
 */
public interface AddOnPool {

    /**
     * Returns a list of pairs, where each pair consists of a double and a BiConsumer.
     *
     * @return a list of pairs of chances and corresponding BiConsumers for add-ons.
     */
    List<Pair<Double, BiConsumer<Double, Platform>>> getAddOnPool();

    /**
     * Returns the chance of spawning an add-on.
     *
     * @return the chance of spawning an add-on as a double.
     */
    double getChanceAddOn();
}
