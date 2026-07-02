package it.unibo.javajump.model.level.spawn.collectiblespawn;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.platforms.Platform;

/**
 * Interface for the CollectibleSpawner, which manages the spawn of coins.
 */
public interface CollectiblesSpawner {
    /**
     * Method to spawn a coin based on the platform position.
     *
     * @param model         the GameModel (to add the spawned coin to the game)
     * @param platformX     the X position of the Platform
     * @param platformY     the Y position of the Platform
     * @param platformWidth the real width of the Platform
     * @param platform      the Platform
     */
    void spawnCollectible(GameModel model, float platformX, float platformY, float platformWidth, Platform platform);
}
