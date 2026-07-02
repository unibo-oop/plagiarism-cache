package it.unibo.model.worldconstructor.data;

import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.AddOnPool;
import it.unibo.model.worldconstructor.gameobjectspawn.api.SpawnPool;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.PlatformPool;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.Distance;

/**
 * Record representing the configuration for a specific difficulty level.
 * 
 * @param height the height of the world for this difficulty level.
 * @param distance the distance configuration for platform spawning.
 * @param spawnPool the pool of game objects to spawn for this difficulty level.
 * @param addOnPool the pool of add-ons to spawn for this difficulty level.
 * @param platformPool the pool of platforms to spawn for this difficulty level.
 */
public record Difficult(double height,
        Distance distance,
        SpawnPool spawnPool,
        AddOnPool addOnPool,
        PlatformPool platformPool) {
}
