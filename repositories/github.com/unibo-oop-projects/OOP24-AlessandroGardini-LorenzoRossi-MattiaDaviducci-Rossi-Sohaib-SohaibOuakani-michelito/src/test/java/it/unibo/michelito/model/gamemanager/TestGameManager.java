package it.unibo.michelito.model.gamemanager;

import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.model.gamemanager.impl.GameManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for {@link GameManagerImpl}.
 */
class TestGameManager {
    private static final int INITIAL_LIVES = 5;
    private GameManagerImpl gameManager;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        this.gameManager = new GameManagerImpl(new LevelGenerator(e -> { }));
    }

    /**
     * Tests initial state.
     */
    @Test
    void testInitialGameState() {
        assertFalse(this.gameManager.isGameOver());
        assertFalse(this.gameManager.isGameWon());
        assertEquals(INITIAL_LIVES, this.gameManager.getRemainingLives());
        assertFalse(this.gameManager.getObjects().isEmpty());
    }
}
