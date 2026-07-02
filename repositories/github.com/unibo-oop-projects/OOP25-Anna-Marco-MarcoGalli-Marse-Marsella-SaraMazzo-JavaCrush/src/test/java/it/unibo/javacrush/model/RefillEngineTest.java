package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.RefillEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.gravity.DownwardGravity;
import it.unibo.javacrush.model.impl.gravity.LeftwardGravity;
import it.unibo.javacrush.model.impl.gravity.RightwardGravity;
import it.unibo.javacrush.model.impl.gravity.UpwardGravity;
import it.unibo.javacrush.model.impl.AdaptiveRefill;

class RefillEngineTest {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new BoardImpl(ROWS, COLS);
    }

    @Test
    void testRefillDownward() {
        final GravityEngine gravity = new DownwardGravity();
        final RefillEngine refillEngine = new AdaptiveRefill(gravity);

        for (int i = 0; i < COLS; i++) {
            assertTrue(board.getCellAt(new Position(i, 0)).isEmpty(), "The cell should be empty before refill");
        }

        final boolean changed = refillEngine.refill(board);
        assertTrue(changed, "The refill method should return true if the board was changed");

        for (int i = 0; i < COLS; i++) {
            assertTrue(board.getCellAt(new Position(i, 0)).isPresent(), "The cell should be filled after refill");
        }
    }

    @Test
    void testRefillUpward() {
        final GravityEngine gravity = new UpwardGravity();
        final RefillEngine refillEngine = new AdaptiveRefill(gravity);

        final boolean changed = refillEngine.refill(board);
        assertTrue(changed);

        for (int col = 0; col < COLS; col++) {
            assertTrue(board.getCellAt(new Position(col, ROWS - 1)).isPresent(), 
                "The cell at the bottom should be filled after refill with upward gravity");
        }
    }

    @Test
    void testRefillLeftward() {
        final GravityEngine gravity = new LeftwardGravity();
        final RefillEngine refillEngine = new AdaptiveRefill(gravity);

        final boolean changed = refillEngine.refill(board);
        assertTrue(changed);

        for (int row = 0; row < ROWS; row++) {
            assertTrue(board.getCellAt(new Position(COLS - 1, row)).isPresent(), 
                "The cell at the rightmost column should be filled after refill with leftward gravity");
        }
    }

    @Test
    void testRefillRightward() {
        final GravityEngine gravity = new RightwardGravity();
        final RefillEngine refillEngine = new AdaptiveRefill(gravity);

        final boolean changed = refillEngine.refill(board);
        assertTrue(changed);

        for (int row = 0; row < ROWS; row++) {
            assertTrue(board.getCellAt(new Position(0, row)).isPresent(), 
                "The cell at the leftmost column should be filled after refill with rightward gravity");
        }
    }

    @Test
    void testRefillNoChange() {
        final RefillEngine refillEngine = new AdaptiveRefill(new DownwardGravity());

        refillEngine.refill(board);
        final boolean changed = refillEngine.refill(board);
        assertFalse(changed, "The refill method should return false if the board was already full");
    }

    @Test
    void testRefillAll() {
        final RefillEngine refillEngine = new AdaptiveRefill(new DownwardGravity());
        refillEngine.refillAll(board);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                assertTrue(board.getCellAt(new Position(col, row)).isPresent(), 
                    "the cell at (" + col + ", " + row + ") should be filled");
            }
        }
    }
}
