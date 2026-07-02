package it.unibo;

import it.unibo.model.Grid;
import it.unibo.model.Puyo;
import it.unibo.model.interfaces.PuyoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Grid} class.
 * These tests check the functionality of the grid, including adding/removing Puyos,
 * checking for full rows, columns, and the entire grid, as well as clearing the grid.
 */
class GridTest {
    private Grid grid;
    private PuyoInterface mockPuyo;

    /**
     * Initializes the test environment before each test method is executed.
     * A new 5x5 grid and a mock Puyo object are created for each test.
     */
    @BeforeEach
    void setUp() {
        grid = new Grid(5, 5); 
        mockPuyo = new Puyo("red",10, 10); 
    }

    /**
     * Tests the initialization of the grid.
     * Verifies that the grid is created with the correct number of rows and columns.
     */
    @Test
    void testGridInitialization() {
        assertEquals(5, grid.getRows(), "The grid should have 5 rows.");
        assertEquals(5, grid.getCols(), "The grid should have 5 columns.");
    }

    /**
     * Tests adding a Puyo to the grid.
     * Verifies that a Puyo can be successfully added to the grid and retrieved from the correct position.
     */
    @Test
    void testAddPuyo() {
        assertTrue(grid.addPuyo(mockPuyo, 2, 3), "Puyo should be added successfully at position (2, 3).");
        assertEquals(mockPuyo, grid.getPuyo(2, 3), "The Puyo at position (2, 3) should be the same as the added Puyo.");
    }

    /**
     * Tests adding a Puyo to an invalid position on the grid.
     * Verifies that attempting to add a Puyo outside the grid boundaries returns false.
     */
    @Test
    void testAddPuyoInvalidPosition() {
        assertFalse(grid.addPuyo(mockPuyo, -1, 3), "Adding a Puyo at an invalid position (-1, 3) should return false.");
        assertFalse(grid.addPuyo(mockPuyo, 5, 5), "Adding a Puyo at an out-of-bounds position (5, 5) should return false.");
    }

    /**
     * Tests removing a Puyo from the grid.
     * Verifies that a Puyo can be successfully removed from the grid and that the position becomes null.
     */
    @Test
    void testRemovePuyo() {
        grid.addPuyo(mockPuyo, 1, 1);
        grid.removePuyo(1, 1);
        assertNull(grid.getPuyo(1, 1), "The Puyo at position (1, 1) should be removed.");
    }

    /**
     * Tests if a position is valid on the grid.
     * Verifies that positions within the grid boundaries are valid, while positions outside the boundaries are not.
     */
    @Test
    void testIsValidPosition() {
        assertTrue(grid.isValidPosition(0, 0), "Position (0, 0) should be valid.");
        assertTrue(grid.isValidPosition(4, 4), "Position (4, 4) should be valid.");
        assertFalse(grid.isValidPosition(-1, 0), "Position (-1, 0) should be invalid.");
        assertFalse(grid.isValidPosition(5, 5), "Position (5, 5) should be invalid.");
    }

    /**
     * Tests if a specific column is full.
     * Verifies that a column is considered full if it has Puyos in all of its rows.
     */
    @Test
    void testIsColumnFull() {
        for (int y = 0; y < grid.getRows(); y++) {
            grid.addPuyo(mockPuyo, 2, y);
        }
        assertTrue(grid.isColumnFull(2), "Column 2 should be full after adding Puyos to all its rows.");
    }

    /**
     * Tests if a specific row is full.
     * Verifies that a row is considered full if it has Puyos in all of its columns.
     */
    @Test
    void testIsRowFull() {
        for (int x = 0; x < grid.getCols(); x++) {
            grid.addPuyo(mockPuyo, x, 2);
        }
        assertTrue(grid.isRowFull(2), "Row 2 should be full after adding Puyos to all its columns.");
    }

    /**
     * Tests if the entire grid is full.
     * Verifies that the grid is considered full if every position is occupied by a Puyo.
     */
    @Test
    void testIsGridFull() {
        for (int y = 0; y < grid.getRows(); y++) {
            for (int x = 0; x < grid.getCols(); x++) {
                grid.addPuyo(mockPuyo, x, y); 
            }
        }
        assertTrue(grid.isGridFull(), "The grid should be full when all positions are occupied by Puyos.");
    }

    /**
     * Tests clearing the grid.
     * Verifies that all Puyos are removed from the grid when the clear method is called.
     */
    @Test
    void testClearGrid() {
        grid.addPuyo(mockPuyo, 2, 2);
        grid.clear();
        assertNull(grid.getPuyo(2, 2), "The Puyo at position (2, 2) should be removed after clearing the grid.");
    }
}
