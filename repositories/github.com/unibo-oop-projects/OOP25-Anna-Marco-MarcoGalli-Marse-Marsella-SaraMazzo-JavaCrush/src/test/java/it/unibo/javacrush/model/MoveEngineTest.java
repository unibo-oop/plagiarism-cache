package it.unibo.javacrush.model;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.model.api.MoveEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;
import it.unibo.javacrush.model.impl.MatchImpl;
import it.unibo.javacrush.model.impl.MoveEngineImpl;

class MoveEngineTest {

    private static final int BOARD_SIZE = 5;
    private Board board;
    private MoveEngine moveEngine;

    @BeforeEach
    void init() {
        board = new BoardImpl(BOARD_SIZE, BOARD_SIZE);
        moveEngine = new MoveEngineImpl();
    }

    @Test
    void testCanSwap() {
        final Position pos1 = new Position(0, 0);
        final Position pos2 = new Position(1, 0);
        final Position pos3 = new Position(2, 0);
        final Position pos4 = new Position(3, 0);
        final CellType type1 = CellType.CUP;
        final CellType type2 = CellType.COFFEE_BEAN;

        board.setCell(pos1, Optional.of(new CellImpl(type1)));
        board.setCell(pos2, Optional.of(new CellImpl(type1)));
        board.setCell(pos3, Optional.of(new CellImpl(type2)));
        board.setCell(pos4, Optional.of(new CellImpl(type1)));

        assertFalse(moveEngine.canSwap(board, pos1, pos3));
        assertFalse(moveEngine.canSwap(board, pos1, pos2));
        assertTrue(moveEngine.canSwap(board, pos3, pos4));

    }

    @Test
    void testGetMatches() {
        final Position pos1 = new Position(0, 0);
        final Position pos2 = new Position(1, 0);
        final Position pos3 = new Position(2, 0);
        final Position pos4 = new Position(3, 0);
        final Position pos5 = new Position(3, 1);
        final Position pos6 = new Position(3, 2);
        final CellType type1 = CellType.CUP;
        final CellType type2 = CellType.COFFEE_BEAN;

        board.setCell(pos1, Optional.of(new CellImpl(type1)));
        board.setCell(pos2, Optional.of(new CellImpl(type1)));
        board.setCell(pos3, Optional.of(new CellImpl(type2)));
        board.setCell(pos4, Optional.of(new CellImpl(type1)));
        board.setCell(pos5, Optional.of(new CellImpl(type2)));
        board.setCell(pos6, Optional.of(new CellImpl(type2)));

        final Set<Match> expectedMatches = Set.of(
            new MatchImpl(Set.of(pos1, pos2, pos3), type1), 
            new MatchImpl(Set.of(pos4, pos5, pos6), type2));

        assertTrue(moveEngine.canSwap(board, pos3, pos4));
        assertEquals(2, moveEngine.getMatches().size());
        assertEquals(expectedMatches, moveEngine.getMatches());
    }

}
