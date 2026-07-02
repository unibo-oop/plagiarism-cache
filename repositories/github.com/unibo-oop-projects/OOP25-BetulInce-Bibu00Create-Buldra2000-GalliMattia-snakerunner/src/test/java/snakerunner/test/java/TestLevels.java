package snakerunner.test.java;

import java.util.List;
import java.util.Set;

import snakerunner.model.LevelData;
import snakerunner.model.VictoryCondition;
import snakerunner.model.impl.LevelDataImpl;

/**
 * The TestLevels class provides static methods to create test level data for unit testing.
 */
public final class TestLevels {

    private TestLevels() { }

    /**
     * Creates a simple level.
     *
     * @return a LevelData object representing the simple level.
     */
    public static LevelData simpleLevel() {
        return new LevelDataImpl(
            Set.of(), // nessun ostacolo
            List.of(),
            null,
            VictoryCondition.COLLECT_ALL_FOOD);
    }
}
