package logic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import gamelogic.Board;
import gamelogic.GameLogic;
import gamelogic.GameLogicImpl;
import gamelogic.HoldBox;
import gamelogic.Score;
import level.Levels;
import pair.Pair;
import piece.Type;

/**
 * This class tests if the logics works properly.
 *
 */
public class TestLogic {
    private final GameLogic game = new GameLogicImpl(Levels.LEVEL_1, Optional.empty());
    private final Score score = this.game.getScore();
    private final HoldBox holdBox = this.game.getHoldBox();
    private final Board board = this.game.getBoard();
    private final Set<Integer> lines = new HashSet<>();
    private final Set<Pair<Integer, Integer>> coordinates = new HashSet<>();
    private static final Set<Pair<Integer, Integer>> TRYBOARD1 = new HashSet<>();
    static {
        TRYBOARD1.add(new Pair<>(17, 3));
        TRYBOARD1.add(new Pair<>(18, 3));
        TRYBOARD1.add(new Pair<>(19, 3));
        TRYBOARD1.add(new Pair<>(19, 4));
    }

    private static final Set<Pair<Integer, Integer>> TRYBOARD2 = new HashSet<>();
    static {
        TRYBOARD2.add(new Pair<>(2, 3));
        TRYBOARD2.add(new Pair<>(3, 3));
        TRYBOARD2.add(new Pair<>(4, 3));
        TRYBOARD2.add(new Pair<>(4, 4));
    }

    private static final Set<Pair<Integer, Integer>> TRYBOARD3 = new HashSet<>();
    static {
        TRYBOARD3.add(new Pair<>(19, 0));
        TRYBOARD3.add(new Pair<>(19, 1));
        TRYBOARD3.add(new Pair<>(19, 2));
        TRYBOARD3.add(new Pair<>(19, 3));
        TRYBOARD3.add(new Pair<>(19, 4));
        TRYBOARD3.add(new Pair<>(19, 5));
        TRYBOARD3.add(new Pair<>(19, 6));
        TRYBOARD3.add(new Pair<>(19, 7));
        TRYBOARD3.add(new Pair<>(19, 8));
    }

    private static final Set<Pair<Integer, Integer>> TRYCURRENT1 = new HashSet<>();
    static {
        TRYCURRENT1.add(new Pair<>(0, 0));
        TRYCURRENT1.add(new Pair<>(1, 0));
        TRYCURRENT1.add(new Pair<>(2, 0));
        TRYCURRENT1.add(new Pair<>(2, 1));
    }

    private static final Set<Pair<Integer, Integer>> TRYCURRENT2 = new HashSet<>();
    static {
        TRYCURRENT2.add(new Pair<>(0, 0));
        TRYCURRENT2.add(new Pair<>(0, 1));
        TRYCURRENT2.add(new Pair<>(0, 2));
        TRYCURRENT2.add(new Pair<>(0, 3));
        TRYCURRENT2.add(new Pair<>(0, 4));
        TRYCURRENT2.add(new Pair<>(0, 5));
        TRYCURRENT2.add(new Pair<>(0, 6));
        TRYCURRENT2.add(new Pair<>(0, 7));
        TRYCURRENT2.add(new Pair<>(0, 8));
        TRYCURRENT2.add(new Pair<>(0, 9));
    }

    private static final Set<Pair<Integer, Integer>> TRYCURRENT3 = new HashSet<>();
    static {
        TRYCURRENT3.add(new Pair<>(0, 0));
        TRYCURRENT3.add(new Pair<>(0, 1));
        TRYCURRENT3.add(new Pair<>(0, 2));
        TRYCURRENT3.add(new Pair<>(0, 3));
        TRYCURRENT3.add(new Pair<>(0, 4));
        TRYCURRENT3.add(new Pair<>(0, 5));
        TRYCURRENT3.add(new Pair<>(0, 6));
        TRYCURRENT3.add(new Pair<>(0, 7));
        TRYCURRENT3.add(new Pair<>(0, 8));
    }

    private static final Set<Pair<Integer, Integer>> TRYCURRENT4 = new HashSet<>();
    static {
        TRYCURRENT4.add(new Pair<>(0, 0));
    }

    /**
     * Tests class Score, the score system and the level up.
     */
    @org.junit.Test
    public void testScore() {
        this.score.addPoints(1);
        assertEquals(10, this.score.getScore());
        this.score.addPoints(2);
        assertEquals(40, this.score.getScore());
        this.score.addPoints(3);
        assertEquals(100, this.score.getScore());
        this.score.addPoints(4);
        assertEquals(200, this.score.getScore());
        assertEquals(Levels.LEVEL_2, this.score.getLevel());
        this.score.addPoints(16);
        assertEquals(1560, this.score.getScore());
        assertEquals(Levels.LEVEL_10, this.score.getLevel());
        this.score.addPoints(4);
        assertEquals(1660, this.score.getScore());
        assertEquals(Levels.LEVEL_10, this.score.getLevel());
    }

    /**
     * Tests the class HoldBox and the rules it needs to follow. 
     */
    @org.junit.Test
    public void testHoldBox() {
        assertFalse(this.holdBox.isHolding());
        assertTrue(this.holdBox.canHold());
        final Type test1 = this.game.getCurrent().getType();
        this.game.hold();
        final Type test2 = this.game.getCurrent().getType();
        assertTrue(this.holdBox.isHolding());
        assertFalse(this.holdBox.canHold());
        this.holdBox.setCanHold(true);
        this.game.hold();
        assertEquals(test1, this.game.getCurrent().getType());
        this.holdBox.setCanHold(true);
        this.game.hold();
        assertEquals(test2, this.game.getCurrent().getType());
        assertTrue(this.holdBox.isHolding());
        assertFalse(this.holdBox.canHold());
    }

    /**
     * Tests the class Board, the checks on the rows completed, their elimination and the adaptation of the board after that.
     */
    @org.junit.Test
    public void testBoard() {
        this.game.getCurrent().setCoordinates(TRYCURRENT1);
        this.game.getCurrent().setTop(17);
        this.board.placePiece();
        assertEquals(TRYBOARD1, this.board.getBoard().keySet());
        this.board.getBoard().clear();
        this.game.getCurrent().setCoordinates(TRYCURRENT3);
        this.game.getCurrent().setTop(18);
        this.game.getCurrent().setLeft(0);
        this.board.placePiece();
        this.game.getCurrent().setCoordinates(TRYCURRENT2);
        this.game.getCurrent().setTop(19);
        this.game.getCurrent().setLeft(0);
        this.board.placePiece();
        this.board.findRowsCompleted();
        assertEquals(TRYBOARD3, this.board.getBoard().keySet());
        this.game.getCurrent().setCoordinates(TRYCURRENT3);
        this.game.getCurrent().setTop(18);
        this.game.getCurrent().setLeft(0);
        this.game.placePiece();
        this.lines.add(19);
        this.board.cancelLines(this.lines);
        this.lines.remove(19);
        assertEquals(TRYBOARD3, this.board.getBoard().keySet());
    }

    /**
     * Tests the class GameImpl, the game over and the collisions.
     */
    @org.junit.Test
    public void testGameImpl() {
        this.game.getCurrent().setCoordinates(TRYCURRENT3);
        this.game.getCurrent().setTop(19);
        this.game.getCurrent().setLeft(0);
        this.game.placePiece();
        this.coordinates.add(new Pair<>(19, 3));
        assertFalse(this.game.isLegalPosition(this.coordinates));
        this.coordinates.remove(new Pair<>(19, 3));
        this.coordinates.add(new Pair<>(0, 0));
        assertTrue(this.game.isLegalPosition(this.coordinates));
        this.game.getCurrent().setCoordinates(TRYCURRENT3);
        this.game.getCurrent().setTop(19);
        this.game.getCurrent().setLeft(0);
        assertFalse(this.game.isCurrentLegal());
        this.game.getCurrent().setTop(18);
        assertTrue(this.game.isCurrentLegal());
        this.game.placePiece();
        assertFalse(this.game.isOver());
        this.game.getCurrent().setCoordinates(TRYCURRENT4);
        this.game.getNext().setCoordinates(TRYCURRENT4);
        this.game.placePiece();
        assertFalse(this.game.isOver());
        this.game.getNext().setCoordinates(TRYCURRENT4);
        this.game.placePiece();
        assertTrue(this.game.isOver());
    }

}
