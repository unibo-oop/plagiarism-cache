package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;
import it.unibo.javacrush.model.impl.StallEngineImpl;

/**
 * Test for {@link it.unibo.javacrush.model.api.StallEngine}.
 */
class StallEngineTest {

    private static final int DIMENSION = 4;
    private Board board;
    private Board init;
    private StallEngine st;

    /**
     * Initialises and fill board and initial with equal-typed cells.
     */
    @BeforeEach
    void setUp() {

        board = new BoardImpl(DIMENSION, DIMENSION);
        init = new BoardImpl(DIMENSION, DIMENSION);
        st = new StallEngineImpl();

        for (int y = 0; y < board.getCols(); y++) {
            for (int x = 0; x < board.getRows(); x++) {
                init.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.CUP)));
                board.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.CUP)));
            }
        }
    }

    /**
     * Test that StallEngine does nothing if the given board contains any null cell.
     */
    @Test
    void testNoStallEmptyBoard() {
        final Position p = new Position(0, 0);

        init.setCell(p, Optional.empty());
        board.setCell(p, Optional.empty());

        assertFalse(st.isStall(board));
        assertEquals(init, board);
    }

    /**
     * Test that StallEngine doesn't modify the given board if there are possible moves.
     */
    @Test
    void testNoStallWithMoves() {

        //set board and init with the same distribution of cellTypes that forms possible moves
        board.setCell(new Position(1, 0), Optional.of(new CellImpl(CellType.MILK)));
        board.setCell(new Position(0, 1), Optional.of(new CellImpl(CellType.MILK)));
        board.setCell(new Position(1, 2), Optional.of(new CellImpl(CellType.MILK)));
        board.setCell(new Position(2, 1), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(3, 1), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(0, 3), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(1, 3), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(2, 2), Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(new Position(3, 2), Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(new Position(2, 3), Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(new Position(3, 3), Optional.of(new CellImpl(CellType.MOKA)));

        init.setCell(new Position(1, 0), Optional.of(new CellImpl(CellType.MILK)));
        init.setCell(new Position(0, 1), Optional.of(new CellImpl(CellType.MILK)));
        init.setCell(new Position(1, 2), Optional.of(new CellImpl(CellType.MILK)));
        init.setCell(new Position(2, 1), Optional.of(new CellImpl(CellType.SPOON)));
        init.setCell(new Position(3, 1), Optional.of(new CellImpl(CellType.SPOON)));
        init.setCell(new Position(0, 3), Optional.of(new CellImpl(CellType.SPOON)));
        init.setCell(new Position(1, 3), Optional.of(new CellImpl(CellType.SPOON)));
        init.setCell(new Position(2, 2), Optional.of(new CellImpl(CellType.MOKA)));
        init.setCell(new Position(3, 2), Optional.of(new CellImpl(CellType.MOKA)));
        init.setCell(new Position(2, 3), Optional.of(new CellImpl(CellType.MOKA)));
        init.setCell(new Position(3, 3), Optional.of(new CellImpl(CellType.MOKA)));

        assertFalse(st.isStall(board));

        st.resolveStall(board);

        assertFalse(st.isStall(board));
        assertEquals(init, board);

    }

    /**
     * Test that, if there's stall, StallEngine shuffles the cells in a manner that possible moves appear.
     */
    @Test
    void testStall() {

        int index = 0;
        for (int y = 0; y < DIMENSION; y++) {
            for (int x = 0; x < DIMENSION; x++) {
                init.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.values()[index % CellType.values().length])));
                board.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.values()[index % CellType.values().length])));
                index++;
            }
        }

        assertTrue(st.isStall(board));

        st.resolveStall(board);

        assertFalse(st.isStall(board));
        assertNotEquals(init, board);

    }

    @Test
    void testHint() {

        final Set<Position> posRes1 = new HashSet<>();
        final Set<Position> posRes2 = new HashSet<>();
        final Set<Position> posRes3 = new HashSet<>();
        final Set<Position> posRes4 = new HashSet<>();
        final Set<Position> posRes5 = new HashSet<>();
        final Position p1 = new Position(0, 0);
        final Position p2 = new Position(1, 1);
        final Position p3 = new Position(2, 0);
        final Position p4 = new Position(3, 0);
        final Position p5 = new Position(1, 0);
        final Position p6 = new Position(2, 1);
        final Position p7 = new Position(1, 2);
        final Position p8 = new Position(0, 2);

        posRes1.add(p1);
        posRes1.add(p2);
        posRes1.add(p3);
        posRes1.add(p4);

        posRes2.add(p5);
        posRes2.add(p6);
        posRes2.add(p7);

        posRes3.add(p1);
        posRes3.add(p2);
        posRes3.add(p8);

        posRes4.addAll(posRes1);
        posRes4.remove(p2);

        posRes5.addAll(posRes1);
        posRes5.remove(p1);

        board.setCell(p5, Optional.of(new CellImpl(CellType.MILK)));
        board.setCell(p6, Optional.of(new CellImpl(CellType.MILK)));
        board.setCell(p7, Optional.of(new CellImpl(CellType.MILK)));
        board.setCell(new Position(0, 1), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(3, 1), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(0, 3), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(1, 3), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(2, 2), Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(new Position(3, 2), Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(new Position(2, 3), Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(new Position(3, 3), Optional.of(new CellImpl(CellType.MOKA)));

        final Set<Position> result = this.st.getHint(board);

        assertFalse(result.isEmpty());

        assertTrue(result.equals(posRes1) 
                    || result.equals(posRes2)
                    || result.equals(posRes3)
                    || result.equals(posRes4)
                    || result.equals(posRes5));

    }
}
