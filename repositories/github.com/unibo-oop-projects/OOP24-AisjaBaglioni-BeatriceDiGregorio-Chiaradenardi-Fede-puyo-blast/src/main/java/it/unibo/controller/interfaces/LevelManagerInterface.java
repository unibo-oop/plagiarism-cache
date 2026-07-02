package it.unibo.controller.interfaces;

import it.unibo.controller.LevelManager.LevelConfig;

/**
 * Interface for managing the levels in the game.
 * Provides methods for retrieving level configurations and managing the current level.
 */
public interface LevelManagerInterface {

    /**
     * Retrieves the configuration for a specific level.
     * 
     * @param level the level number to retrieve the configuration for
     * @return the configuration of the specified level, or a default configuration if the level is not found
     */
    LevelConfig getLevelConfig(int level);

    /**
     * Retrieves the current active level number.
     * 
     * @return the current level number
     */
    int getCurrentLevel();

    /**
     * Retrieves the configuration for the current active level.
     * 
     * @return the configuration for the current level
     */
    LevelConfig getCurrentLevelConfig();
}
