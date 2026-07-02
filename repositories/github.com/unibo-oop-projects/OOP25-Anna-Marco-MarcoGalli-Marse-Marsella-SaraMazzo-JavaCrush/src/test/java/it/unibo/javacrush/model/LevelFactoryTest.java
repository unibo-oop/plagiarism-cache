package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.javacrush.model.api.GameMatchContext;
import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.impl.LevelFactory;

class LevelFactoryTest {

    private static final int LEVELS = 5;

    @Test
    void testIsLevelGenerationConsistent() {
        for (int i = 1; i <= LEVELS; i++) {
            final LevelConfig config = LevelFactory.createLevel(i);

            assertNotNull(config, "LevelFactory should create a non-null config for level " + i);

            assertTrue(config.rows() > 2, "the rows of level " + i + " should be more than 2");
            assertTrue(config.cols() > 2, "the columns of level " + i + " should be more than 2");
            assertTrue(config.moves() > 0, "the moves of level " + i + " should be a positive number");
            assertNotNull(config.gravity(), "the gravity of level " + i + " should be not null");
            assertNotNull(config.goals(), "the goals of level " + i + " should be non-null");
            assertTrue(!config.goals().isEmpty(), "the goals of level " + i + " should not be empty");
        }
    }

    @Test
    void testGameMatchContextInjection() {
        for (int i = 1; i <= LEVELS; i++) {
            final GameMatchContext context = LevelFactory.createGameMatchContext(i);
            assertNotNull(context, "the game match context of level " + i + " should be non-null");
            assertNotNull(context.getBoard());
            assertNotNull(context.getPhysicsHandler());
            assertNotNull(context.getLevelConfig());
            assertNotNull(context.getMoveEngine());
            assertNotNull(context.getMatchManager());
            assertNotNull(context.getStallEngine());
            assertNotNull(context.getSession());

            assertEquals(context.getLevelConfig().cols(), context.getBoard().getCols());
            assertEquals(context.getLevelConfig().rows(), context.getBoard().getRows());
        }
    }
}
