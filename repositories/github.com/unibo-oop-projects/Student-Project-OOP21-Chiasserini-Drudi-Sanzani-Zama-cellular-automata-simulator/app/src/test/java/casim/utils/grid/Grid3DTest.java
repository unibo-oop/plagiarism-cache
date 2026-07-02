package casim.utils.grid;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.range.Ranges;

/**
 * Test class for {@link Grid3D}.
 */
class Grid3DTest {
    private static final int DEFAULT_VALUE = 1;
    private static final int NEW_VALUE = 2;
    private static final int ROWS = 3;
    private static final int COLS = 2;
    private static final int DEPTH = 2;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 1;

    private Grid3D<Integer> getGrid() {
        return new Grid3DImpl<>(ROWS, COLS, DEPTH, () -> DEFAULT_VALUE);
    }

    private Grid3D<Integer> getGridWithValues() {
        final var grid = getGrid();
        for (final var x : Ranges.of(0, ROWS)) {
            for (final var y : Ranges.of(0, COLS)) {
                for (final var z : Ranges.of(0, DEPTH)) {
                    grid.set(x, y, z, x + y + z);
                }
            }
        }
        return grid;
    }

    /**
     * Test for {@link Grid#get(int, int, int)} method.
     */
    @Test
    void testGetWithIntegers() {
        final var grid = getGrid();
        assertDoesNotThrow(() -> grid.set(X, Y, Z, NEW_VALUE));
        assertEquals(NEW_VALUE, grid.get(X, Y, Z));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(X, COLS, Z));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(ROWS, Y, Z));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(X, Y, DEPTH));
    }

    /**
     * Test for {@link Grid#get(casim.utils.coordinate.Coordinates)} method.
     */
    @Test
    void testGetWithCoordinates() {
        final var grid = getGrid();
        final var coord = CoordinatesUtil.of(X, Y, Z);
        assertDoesNotThrow(() -> grid.set(coord, NEW_VALUE));
        assertEquals(NEW_VALUE, grid.get(coord));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(CoordinatesUtil.of(X, COLS, Z)));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(CoordinatesUtil.of(ROWS, Y, Z)));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(CoordinatesUtil.of(X, Y, DEPTH)));
    }

    /**
     * Test for {@link Grid#getHeight()} method.
     */
    @Test
    void testGetHeight() {
        assertEquals(ROWS, getGrid().getHeight());
    }

    /**
     * Test for {@link Grid#getWidth()} method.
     */
    @Test
    void testGetWidth() {
        assertEquals(COLS, getGrid().getWidth());
    }

    /**
     * Test for {@link Grid#getDepth()} method.
     */
    @Test
    void testGetDepth() {
        assertEquals(DEPTH, getGrid().getDepth());
    }

    /**
     * Test for {@link Grid#isCoordValid(casim.utils.coordinate.Coordinates)} method.
     */
    @Test
    void testIsCoordValid() {
        final var grid = getGrid();
        for (final var x : Ranges.of(0, ROWS)) {
            for (final var y : Ranges.of(0, COLS)) {
                for (final var z : Ranges.of(0, DEPTH)) {
                    assertTrue(grid.isCoordValid(CoordinatesUtil.of(x, y, z)));
                }
            }
        }
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(-1, 0, 0)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(0, -1, 0)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(0, 0, -1)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(-1, -1, 0)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(-1, 0, -1)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(0, -1, -1)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(-1, -1, -1)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(ROWS, 0, 0)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(0, COLS, 0)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(0, 0, DEPTH)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(ROWS, COLS, 0)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(0, COLS, DEPTH)));
        assertFalse(grid.isCoordValid(CoordinatesUtil.of(ROWS, COLS, DEPTH)));
    }

    /**
     * Test for {@link Grid3D#set(int, int, int, Object)} method.
     */
    @Test
    void testSetWithIntegers() {
        final var grid = getGrid();
        assertDoesNotThrow(() -> grid.set(X, Y, Z, NEW_VALUE));
        assertEquals(NEW_VALUE, grid.get(X, Y, Z));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(X, COLS, Z, NEW_VALUE));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(ROWS, Y, Z, NEW_VALUE));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(X, Y, DEPTH, NEW_VALUE));
    }

    /**
     * Test for {@link Grid3D#set(casim.utils.coordinate.Coordinates, Object)} method.
     */
    @Test
    void testSetWithCoordinates() {
        final var coord = CoordinatesUtil.of(X, Y, Z);
        final var grid = getGrid();
        assertDoesNotThrow(() -> grid.set(coord, NEW_VALUE));
        assertEquals(NEW_VALUE, grid.get(coord));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(CoordinatesUtil.of(X, COLS, Z), NEW_VALUE));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(CoordinatesUtil.of(ROWS, Y, Z), NEW_VALUE));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(CoordinatesUtil.of(X, Y, DEPTH), NEW_VALUE));
    }

    /**
     * Test for {@link Grid3D#stream()} method.
     */
    @Test
    void testStream() {
        final var grid = getGridWithValues();
        final var elements = new HashSet<>();
        for (final var x : Ranges.of(0, ROWS)) {
            for (final var y : Ranges.of(0, COLS)) {
                for (final var z : Ranges.of(0, DEPTH)) {
                    elements.add(grid.get(x, y, z));
                }
            }
        }
        assertEquals(elements, grid.stream().collect(Collectors.toUnmodifiableSet()));
    }
}
