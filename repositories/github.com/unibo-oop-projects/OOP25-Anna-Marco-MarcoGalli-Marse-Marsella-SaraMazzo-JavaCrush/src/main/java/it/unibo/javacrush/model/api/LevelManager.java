package it.unibo.javacrush.model.api;

/**
 * Manages the configuration for different game levels.
 */
public interface LevelManager {

    /**
     * @param level the level number to get the configuration for
     * @return the configuration for the specified level
     */
    LevelConfig getLevelSetup(int level);

    /**
     * Starts a new match for the specified level.
     * 
     * @param level the level number
     * @return the game match context for the started match
     */
    GameMatchContext startMatch(int level);

}
