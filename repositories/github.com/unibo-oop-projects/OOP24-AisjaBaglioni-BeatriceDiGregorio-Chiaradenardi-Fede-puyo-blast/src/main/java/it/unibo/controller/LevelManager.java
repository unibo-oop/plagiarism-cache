package it.unibo.controller;

import java.util.HashMap;
import java.util.Map;
import it.unibo.controller.interfaces.LevelManagerInterface;

/**
 * The LevelManager class is responsible for managing the levels in the game.
 * It stores the configuration for each level, including the delay and number of Puyos.
 */
public class LevelManager implements LevelManagerInterface {
    /**
     * Map to store level configurations.
     */
    private final Map<Integer, LevelConfig> levels; 
    /**
     * The current level in the game.
     */
    private int currentLevel; 
    /**
     * Constructor for initializing the level manager with default levels.
     */
    public LevelManager() {
        this.levels = new HashMap<>();
        this.currentLevel = 1;

        /**
         * Level 1: 1 Puyo per second, difficulty 0.04 (=puyoCount/delay).
         * Level 2: 2 Puyos per second, difficulty 0.067.
         * Level 3: 3 Puyos per second, difficulty 0.1.
         */
        levels.put(1, new LevelConfig(25, 1));  
        levels.put(2, new LevelConfig(30, 2)); 
        levels.put(3, new LevelConfig(30, 3)); 
    }

    /**
     * Retrieves the current level configuration.
     * If the level is not found, returns the default configuration (delay = 25, Puyo count = 1).
     * 
     * @return the configuration for the current level.
     */
    @Override
    public LevelConfig getCurrentLevelConfig() {
        return levels.getOrDefault(currentLevel, new LevelConfig(25, 1));
    }

    /**
     * A static nested class representing the configuration of a level.
     * It includes the delay between Puyo drops and the number of Puyos generated per tick.
     */
    public static class LevelConfig {
        /**
         * Delay between Puyo drops in seconds.
         */
        private final int delay; 
        /**
         * Number of Puyos generated per tick.
         */
        private final int puyoCount; 

        /**
         * Constructor to initialize the level configuration.
         * 
         * @param delay     the delay between Puyo drops.
         * @param puyoCount the number of Puyos generated per tick.
         */
        public LevelConfig(int delay, int puyoCount) {
            this.delay = delay;
            this.puyoCount = puyoCount;
        }

        /**
         * Gets the delay between Puyo drops for this level.
         * 
         * @return the delay in seconds.
         */
        public int getDelay() {
            return delay;
        }

        /**
         * Gets the number of Puyos generated per tick for this level.
         * 
         * @return the number of Puyos per tick.
         */
        public int getPuyoCount() {
            return puyoCount;
        }
    }

    /**
     * Retrieves the configuration for a specific level.
     * If the level is not found, returns the default configuration (delay = 25, Puyo count = 1).
     * 
     * @param level the level number for which the configuration is needed.
     * @return the configuration for the specified level.
     */
    @Override
    public LevelConfig getLevelConfig(int level) {
        return levels.getOrDefault(level, new LevelConfig(25, 1)); 
    }

    /**
     * Gets the current level number.
     * 
     * @return the current level.
     */
    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }
}
