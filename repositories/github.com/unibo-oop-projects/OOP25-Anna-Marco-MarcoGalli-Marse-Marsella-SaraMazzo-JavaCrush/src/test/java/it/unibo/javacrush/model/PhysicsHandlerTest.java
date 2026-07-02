package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.PhysicsHandlerImpl;
import it.unibo.javacrush.model.impl.StallEngineImpl;
import it.unibo.javacrush.model.impl.gravity.DownwardGravity;
import it.unibo.javacrush.model.impl.gravity.LeftwardGravity;
import it.unibo.javacrush.model.impl.gravity.RightwardGravity;
import it.unibo.javacrush.model.impl.gravity.UpwardGravity;

class PhysicsHandlerTest {

    private static final int ROWS = 8;
    private static final int COLS = 8;
    private Board board;
    private PhysicsHandler physics;

    @BeforeEach
    void setUp() {
        this.board = new BoardImpl(ROWS, COLS);
        this.physics = new PhysicsHandlerImpl(new DownwardGravity(), new StallEngineImpl());
    }

    @Test
    void testInitializeBoard() {
        physics.initializeBoard(board);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                assertTrue(board.getCellAt(new Position(col, row)).isPresent(),
                "the cell at (" + col + ", " + row + ") should be filled");
            }
        }
        assertFalse(physics.update(board),
        "The board should be stable and not change after initialization");
    }

    /**
     * Tests the "Continuous Flow" logic: when a cell falls, 
     * a new one should immediately enter from the top in the same update step.
     */
    @Test
    void testContinuousFlow() {

        physics.update(board); 
        final boolean secondUpdate = physics.update(board);

        assertTrue(secondUpdate, "The board should have changed");

        for (int i = 0; i < COLS; i++) {
            // Check if the piece actually moved down
            assertTrue(board.getCellAt(new Position(i, 1)).isPresent(),
            "Cell at column " + i + " should have fallen from row 0 to row 1");

            // Check if a new piece replaced it
            assertTrue(board.getCellAt(new Position(i, 0)).isPresent(),
            "A new cell should have appeared at column " + i + " row 0");
        }
    }

    /**
     * Tests if the board reaches a stable state (completely full and no more moves possible).
     */
    @Test
    void testStability() {
        /* The safetyCounter prevents infinite loops during testing if the 
         * physics logic fails to stabilize.
         */
        int safetyCounter = 0;
        while (physics.update(board) && safetyCounter < 10) {
            safetyCounter++;
        }
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                assertTrue(board.getCellAt(new Position(col, row)).isPresent(),
                "the cell at (" + col + ", " + row + ") should be filled");
            }
        }
        assertFalse(physics.update(board),
        "The board should be stable and not change after the last update");
    }

    @Test
    void testGravityDirectionSwitch() {

        physics.setGravity(new DownwardGravity());
        clearBoard();
        physics.update(board);
        for (int col = 0; col < COLS; col++) {
            assertTrue(board.getCellAt(new Position(col, 0)).isPresent(),
            "Cell at col " + col + " should have fallen from row 0 to row 1");
            for (int row = 1; row < ROWS; row++) {
                assertTrue(board.getCellAt(new Position(col, row)).isEmpty(),
                "Cells below the first row should be empty");
            }
        }

        physics.setGravity(new UpwardGravity());
        clearBoard();
        physics.update(board);
        for (int col = 0; col < COLS; col++) {
            assertTrue(board.getCellAt(new Position(col, ROWS - 1)).isPresent(),
            "Cell at col " + col + " should have risen to the top row");
            for (int row = 0; row < ROWS - 1; row++) {
                assertTrue(board.getCellAt(new Position(col, row)).isEmpty(),
                "Cells above the last row should be empty");
            }
        }

        physics.setGravity(new LeftwardGravity());
        clearBoard();
        physics.update(board);
        for (int row = 0; row < ROWS; row++) {
            assertTrue(board.getCellAt(new Position(COLS - 1, row)).isPresent(),
            "Cell at row " + row + " should have moved to the leftmost column");
            for (int col = 0; col < COLS - 1; col++) {
                assertTrue(board.getCellAt(new Position(col, row)).isEmpty(),
                "Cells to the right of the first column should be empty");
            }
        }

        physics.setGravity(new RightwardGravity());
        clearBoard();
        physics.update(board);
        for (int row = 0; row < ROWS; row++) {
            assertTrue(board.getCellAt(new Position(0, row)).isPresent(),
            "Cell at row " + row + " should have moved to the rightmost column");
            for (int col = 1; col < COLS; col++) {
                assertTrue(board.getCellAt(new Position(col, row)).isEmpty(),
                "Cells to the left of the last column should be empty");
            }
        }
    }

    private void clearBoard() {
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    this.board.setCell(new Position(col, row), Optional.empty());
                }
            }
        }
}
