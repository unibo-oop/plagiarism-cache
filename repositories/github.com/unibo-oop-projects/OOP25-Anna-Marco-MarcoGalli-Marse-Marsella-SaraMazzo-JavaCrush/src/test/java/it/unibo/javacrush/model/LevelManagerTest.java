package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.model.api.GameMatchContext;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.model.impl.LevelManagerImpl;

class LevelManagerTest {

    private LevelManager levelManager;

    @BeforeEach
    void setUp() {
        this.levelManager = new LevelManagerImpl();
    }

    @Test
    void testStartMatchContext() {
        final GameMatchContext context = levelManager.startMatch(3);
        assertNotNull(context, "the game match context of level 3 should be non-null");
        assertNotNull(context.getLevelConfig(), "the level config of level 3 should be non-null");
        assertEquals(10, context.getLevelConfig().moves(), "the moves of level 3 should be 10");
    }
}
