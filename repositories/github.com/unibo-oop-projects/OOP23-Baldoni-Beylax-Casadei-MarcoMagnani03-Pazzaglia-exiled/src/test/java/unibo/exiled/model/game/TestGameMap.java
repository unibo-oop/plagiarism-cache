package unibo.exiled.model.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class TestGameMap {

    private static int defaultMapSize;

    @BeforeAll
    static void initialize() {
        defaultMapSize = ConstantsAndResourceLoader.MAP_SIZE;
    }

    @Test
    void testFailedInitialization() {
        assertThrows(IllegalArgumentException.class, () -> {
            final GameMap map = new GameMapImpl(defaultMapSize + 1);
            assertNotNull(map);
        });
    }

    @Test
    void testFailedInitializationMessage() {
        try {
            final GameMap map = new GameMapImpl(defaultMapSize + 1);
            assertNotNull(map);
        } catch (final IllegalArgumentException exception) {
            final String oddMapMessage = "The size of the map should be an even number.";
            assertEquals(oddMapMessage, exception.getMessage());
        }
    }

    @Test
    void testCellTypeIndex() {
        assertEquals(0, CellType.PLAINS.getValue());
    }

    @Test
    void testGetCellStates() {
        final GameMap map = new GameMapImpl(defaultMapSize);
        final Position cornerPosition = new Position(defaultMapSize - 1, defaultMapSize - 1);
        assertTrue(map.getCellStates().containsKey(cornerPosition));
        assertTrue(map.getCellStates().containsKey(new Position(0, 0)));
        final CellType cornerCellType = map.getCellStates().get(cornerPosition);
        assertEquals(cornerPosition, map.getCornerPositionOfElement(cornerCellType));
    }
}
