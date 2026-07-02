package tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import controller.ControllerImpl;
import controller.levels.Level;
import controller.levels.LevelImpl;
import controller.levels.Levels;
import controller.loader.FileControllerImpl;
import model.World;

/**
 * Test class for the levels.
 */
public class TestLevels {

    /**
     * Test the setting of a level.
     */
    @Test
    public void testLevels() {
        final Level level = new LevelImpl(new FileControllerImpl(new World()), ControllerImpl.getController());
        assertNotNull(level);
        level.setCurrentLevel(Levels.LEVEL_3);
        assertEquals(level.getCurrentLevel(), Levels.LEVEL_3);
        level.setCurrentLevel(Levels.LEVEL_1);
        assertEquals(level.getCurrentLevel(), Levels.LEVEL_1);
        level.setCurrentLevel(Levels.LEVEL_2);
        assertNotEquals(level.getCurrentLevel(), Levels.LEVEL_4);
    }

}
