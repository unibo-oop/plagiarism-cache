package it.unibo.javacrush.model;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;
import it.unibo.javacrush.model.impl.MatchImpl;
import it.unibo.javacrush.model.impl.MatchManagerImpl;

class MatchManagerTest {

    private static final int FIVE = 5;
    private static final int SIX = 6;
    private Board board;
    private MatchManager manager;

    @BeforeEach
    void init() {
        board = new BoardImpl(FIVE, FIVE);
        manager = new MatchManagerImpl();
    }

    @Test
    void testFindMatchesAtHorizontal() {
        final Position pos1 = new Position(0, 0);
        final Position pos2 = new Position(1, 0);
        final Position pos3 = new Position(2, 0);
        final CellType expectedType = CellType.CUP;

        board.setCell(pos1, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos2, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos3, Optional.of(new CellImpl(expectedType)));

        final Match horizontalMatch = manager.findMatchesAt(board, new Position(1, 0));

        assertFalse(horizontalMatch.isEmpty());
        assertEquals(3, horizontalMatch.getSize());
        assertEquals(Set.of(pos1, pos2, pos3), horizontalMatch.getPositions());
        assertEquals(expectedType, horizontalMatch.getType());
    }

    @Test
    void testFindMatchesAtVertical() {
        final Position pos1 = new Position(0, 0);
        final Position pos2 = new Position(0, 1);
        final Position pos3 = new Position(0, 2);
        final Position pos4 = new Position(0, 3);
        final CellType expectedType = CellType.CUP;

        board.setCell(pos1, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos2, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos3, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos4, Optional.of(new CellImpl(expectedType)));

        final Match verticalMatch = manager.findMatchesAt(board, new Position(0, 1));

        assertFalse(verticalMatch.isEmpty());
        assertEquals(4, verticalMatch.getSize());
        assertEquals(Set.of(pos1, pos2, pos3, pos4), verticalMatch.getPositions());
        assertEquals(expectedType, verticalMatch.getType());
    }

    @Test
    void testFindMatchesAtTshape() {
        final Position pos1 = new Position(0, 0);
        final Position pos2 = new Position(1, 0);
        final Position pos3 = new Position(2, 0);
        final Position pos4 = new Position(1, 1);
        final Position pos5 = new Position(1, 2);
        final CellType expectedType = CellType.MILK;

        board.setCell(pos1, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos2, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos3, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos4, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos5, Optional.of(new CellImpl(expectedType)));

        final Match tShapeMatch = manager.findMatchesAt(board, new Position(1, 0));

        assertFalse(tShapeMatch.isEmpty());
        assertEquals(FIVE, tShapeMatch.getSize());
        assertEquals(Set.of(pos1, pos2, pos3, pos4, pos5), tShapeMatch.getPositions());
        assertEquals(expectedType, tShapeMatch.getType());
    }

    @Test
    void testFindMatchesAtLshape() {
        final Position pos1 = new Position(0, 0);
        final Position pos2 = new Position(0, 1);
        final Position pos3 = new Position(0, 2);
        final Position pos4 = new Position(1, 2);
        final Position pos5 = new Position(2, 2);
        final Position pos6 = new Position(3, 2);
        final CellType expectedType = CellType.SUGAR;

        board.setCell(pos1, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos2, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos3, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos4, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos5, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos6, Optional.of(new CellImpl(expectedType)));

        final Match lShapeMatch = manager.findMatchesAt(board, new Position(0, 2));

        assertFalse(lShapeMatch.isEmpty());
        assertEquals(SIX, lShapeMatch.getSize());
        assertEquals(Set.of(pos1, pos2, pos3, pos4, pos5, pos6), lShapeMatch.getPositions());
        assertEquals(expectedType, lShapeMatch.getType());
    }

    @Test
    void testFindMatchesAtNoMatch() {
        final Position pos1 = new Position(0, 0);
        final Position pos2 = new Position(1, 0);
        final Position pos3 = new Position(2, 0);
        final CellType type1 = CellType.CUP;
        final CellType type2 = CellType.MILK;

        board.setCell(pos1, Optional.of(new CellImpl(type1)));
        board.setCell(pos2, Optional.of(new CellImpl(type1)));
        board.setCell(pos3, Optional.of(new CellImpl(type2)));

        final Match noMatch = manager.findMatchesAt(board, new Position(1, 0));

        assertNull(noMatch);
    }

    @Test
    void testFindAllMatches() {
        final Position pos1 = new Position(0, 0);
        final Position pos2 = new Position(1, 0);
        final Position pos3 = new Position(2, 0);

        final Position pos4 = new Position(3, 1);
        final Position pos5 = new Position(3, 2);
        final Position pos6 = new Position(3, 3);
        final Position pos7 = new Position(3, 4);

        final CellType expectedType1 = CellType.COFFEE_BEAN;
        final CellType expectedType2 = CellType.SUGAR;

        board.setCell(pos1, Optional.of(new CellImpl(expectedType1)));
        board.setCell(pos2, Optional.of(new CellImpl(expectedType1)));
        board.setCell(pos3, Optional.of(new CellImpl(expectedType1)));

        board.setCell(pos4, Optional.of(new CellImpl(expectedType2)));
        board.setCell(pos5, Optional.of(new CellImpl(expectedType2)));
        board.setCell(pos6, Optional.of(new CellImpl(expectedType2)));
        board.setCell(pos7, Optional.of(new CellImpl(expectedType2)));

        final Set<Match> allMatches = manager.findAllMatches(board);

        assertEquals(2, allMatches.size());
    }

    @Test
    void testRemoveMatch() {
        final Position pos1 = new Position(2, 0);
        final Position pos2 = new Position(2, 1);
        final Position pos3 = new Position(2, 2);
        final CellType type = CellType.CUP;

        board.setCell(pos1, Optional.of(new CellImpl(type)));
        board.setCell(pos2, Optional.of(new CellImpl(type)));
        board.setCell(pos3, Optional.of(new CellImpl(type)));

        final Match match = new MatchImpl(Set.of(pos1, pos2, pos3), type);

        manager.removeMatch(board, match);

        assertTrue(board.getCellAt(pos1).isEmpty());
        assertTrue(board.getCellAt(pos2).isEmpty());
        assertTrue(board.getCellAt(pos3).isEmpty());

    }
}
