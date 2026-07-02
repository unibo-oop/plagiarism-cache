package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;

/**
 * Abstract test class for {@link GravityEngine} implementations.
 */
public abstract class AbstractGravityTest {

    private static final int ROWS = 5;
    private static final int COLS = 5;

    /** The game board instance. */
    private Board board;
    /** The gravity engine instance to be tested. */
    private GravityEngine gravity;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
        void setUp() {
            board = new BoardImpl(ROWS, COLS);
            gravity = createGravity();
        }

    /**
     * Gets the game board.
     * 
     * @return the current board
     */
    protected final Board getBoard() {
        return this.board;
    }

    /**
     * Gets the gravity engine.
     * 
     * @return the current gravity engine
     */
    protected final GravityEngine getGravity() {
        return this.gravity;
    }

    /**
     * Creates an instance of the specific {@link GravityEngine} implementation to be tested.
     * 
     * @return the created gravity engine instance
     */
    protected abstract GravityEngine createGravity();

    /**
     * Tests the movement of a cell under the influence of gravity.
     */
    @Test
    void testSingleMovement() {
        final Position start = new Position(2, 2);
        final Direction dir = gravity.getDirection();

        final Position expected = new Position(start.x() + dir.getDx(), start.y() + dir.getDy());
        board.setCell(start, Optional.of(new CellImpl(CellType.MOKA)));
        final boolean moved = gravity.applyGravity(board);

        assertTrue(moved, "Expected movement in direction " + dir);
        assertTrue(board.getCellAt(start).isEmpty());
        assertTrue(board.getCellAt(expected).isPresent());
    }

    /**
     * Tests the behavior of a cell when blocked by another cell.
     */
    @Test
    void testBlockedByAnotherCell() {
        final Direction dir = gravity.getDirection();
        final int edgeX = dir.getDx() > 0 ? COLS - 1 : dir.getDx() < 0 ? 0 : 2;
        final int edgeY = dir.getDy() > 0 ? ROWS - 1 : dir.getDy() < 0 ? 0 : 2;

        final Position posEdge = new Position(edgeX, edgeY);
        final Position posBeforeEdge = new Position(edgeX - dir.getDx(), edgeY - dir.getDy());

        board.setCell(posEdge, Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(posBeforeEdge, Optional.of(new CellImpl(CellType.CUP)));

        final boolean moved = gravity.applyGravity(board);
        assertFalse(moved, "Expected no movement due to blockage");
    }

    /**
     * Tests the behavior of a cell when it reaches the edge of the board.
     */
    @Test
    void testReachingTheEdge() {
        final Direction dir = gravity.getDirection();

        final int startX = dir.getDx() > 0 ? 0 : dir.getDx() < 0 ? COLS - 1 : 2;
        final int startY = dir.getDy() > 0 ? 0 : dir.getDy() < 0 ? ROWS - 1 : 2;
        final Position startPos = new Position(startX, startY);

        board.setCell(startPos, Optional.of(new CellImpl(CellType.MOKA)));

        for (int i = 0; i < Math.max(ROWS, COLS); i++) {
            gravity.applyGravity(board);
        }

        final int endX = dir.getDx() > 0 ? COLS - 1 : dir.getDx() < 0 ? 0 : startX;
        final int endY = dir.getDy() > 0 ? ROWS - 1 : dir.getDy() < 0 ? 0 : startY;
        final Position endPos = new Position(endX, endY);

        assertTrue(board.getCellAt(endPos).isPresent(), "Expected cell to reach the edge");
        assertDoesNotThrow(() -> gravity.applyGravity(board));
    }
}
