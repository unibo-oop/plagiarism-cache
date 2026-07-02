package it.unibo.javacrush.model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;

/**
 * Test for {@link it.unibo.javacrush.model.api.Board}.
 */
class BoardTest {

    private static final int DIMENSION = 3;
    private Board board;

    /**
     * Initialises the testMap with empty optionals and create the board.
     */
    @BeforeEach
    void init() {
        board = new BoardImpl(DIMENSION, DIMENSION);
    }

    @Test
    void testDimensions() {
        assertEquals(DIMENSION, board.getRows()); //testa getRows
        assertEquals(DIMENSION, board.getCols()); //testa getCols
    }

    @Test
    void testSetAndGetCell() {
        final Cell testCell = new CellImpl(CellType.MOKA);
        board.setCell(new Position(0, 0), Optional.of(testCell));
        assertTrue(board.getCellAt(new Position(0, 0)).isPresent());
        assertFalse(board.getCellAt(new Position(0, 1)).isPresent());
        assertEquals(testCell, board.getCellAt(new Position(0, 0)).get());
    }

    @Test
    void testSwapCell() {
        final Position p1 = new Position(1, 1);
        final Position p2 = new Position(1, 2);
        final Position p3 = new Position(1, 0);
        final Cell c1 = new CellImpl(CellType.COFFEE_BEAN);
        final Cell c2 = new CellImpl(CellType.CUP);
        board.setCell(p1, Optional.of(c1));
        board.setCell(p2, Optional.of(c2));

        board.swapCells(p1, p2);

        assertTrue(board.getCellAt(p1).isPresent());
        assertEquals(c2, board.getCellAt(p1).get());
        assertTrue(board.getCellAt(p2).isPresent());
        assertEquals(c1, board.getCellAt(p2).get());

        board.swapCells(p1, p2);
        board.swapCells(p1, p3);

        assertEquals(Optional.empty(), board.getCellAt(p1));
        assertEquals(c1, board.getCellAt(p3).get());
    }

    @Test
    void testRemoveCell() {
        final Position pos = new Position(0, 0);

        board.setCell(pos, Optional.of(new CellImpl(CellType.COFFEE_BEAN)));
        assertTrue(board.getCellAt(pos).isPresent());

        board.removeCell(pos);
        assertTrue(board.getCellAt(pos).isEmpty());

    }
}
