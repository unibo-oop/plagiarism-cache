package it.unibo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.controller.LevelManager;

/**
 * Unit tests for the {@link LevelManager} class.
 * These tests check the functionality of retrieving level configurations 
 * and ensuring the correct behavior of the LevelManager class.
 */
class LevelManagerTest {

    /**
     * Instance of LevelManager to be tested.
     */
    private LevelManager levelManager;

    /**
     * Initializes the LevelManager instance before each test.
     * This ensures that a fresh instance is used for each test method.
     */
    @BeforeEach
    void setUp() {
        levelManager = new LevelManager(); 
    }

    /**
     * Test the retrieval of the current level configuration.
     * Verifies that the initial level configuration is not null 
     * and that the default values for delay and Puyo count are as expected.
     */
    @Test
    void testGetCurrentLevelConfig() {
        LevelManager.LevelConfig config = levelManager.getCurrentLevelConfig();
        assertNotNull(config, "The level configuration should not be null");
        assertEquals(25, config.getDelay(), "The delay for the initial level should be 25");
        assertEquals(1, config.getPuyoCount(), "The number of Puyo for the initial level should be 1");
    }

    /**
     * Test the retrieval of level configurations for levels 1, 2, and 3,
     * as well as the default configuration for a non-existent level.
     * Verifies that the correct delay and Puyo count are returned for each level,
     * and that a non-existent level returns default values.
     */
    @Test
    void testGetLevelConfig() {
        LevelManager.LevelConfig level1 = levelManager.getLevelConfig(1);
        LevelManager.LevelConfig level2 = levelManager.getLevelConfig(2);
        LevelManager.LevelConfig level3 = levelManager.getLevelConfig(3);
        LevelManager.LevelConfig defaultLevel = levelManager.getLevelConfig(99); 

        assertEquals(25, level1.getDelay(), "Level 1 should have a delay of 25");
        assertEquals(1, level1.getPuyoCount(), "Level 1 should have 1 Puyo");

        assertEquals(30, level2.getDelay(), "Level 2 should have a delay of 30");
        assertEquals(2, level2.getPuyoCount(), "Level 2 should have 2 Puyo");

        assertEquals(30, level3.getDelay(), "Level 3 should have a delay of 30");
        assertEquals(3, level3.getPuyoCount(), "Level 3 should have 3 Puyo");

        assertEquals(25, defaultLevel.getDelay(), "An undefined level should have a default delay of 30");
        assertEquals(1, defaultLevel.getPuyoCount(), "An undefined level should have 1 default Puyo");
    }

    /**
     * Test the retrieval of the current level.
     * Verifies that the initial level is correctly set to level 1.
     */
    @Test
    void testGetCurrentLevel() {
        assertEquals(1, levelManager.getCurrentLevel(), "The initial level should be 1");
    }
}
