package it.unibo.javajump.model.level.spawn.platformspawn;

import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;

/**
 * The interface that describes the Platform spawner.
 */
public interface PlatformSpawner {

    /**
     * Method to Spawn a general platform.
     *
     * @param x           the x of the platform
     * @param y           the y of the platform
     * @param screenWidth the screen width
     * @param difficulty  the difficulty
     *
     * @return the platform
     */
    Platform spawnPlatform(float x, float y, int screenWidth, DifficultyState difficulty);
}
