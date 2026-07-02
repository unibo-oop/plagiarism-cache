package movements.test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import gamelogic.Board;
import gamelogic.GameLogic;
import gamelogic.GameLogicImpl;
import level.Levels;
import movements.Movements;
import movements.MovementsImpl;
import pair.Pair;
import piece.PieceImpl;
import piece.Type;

/**
 * This class tests methods of class Movements.
 */
public class TestMovements {
    private static final int SIZE_1 = 2;
    private static final int SIZE_2 = 3;
    private static final int EXPECTED_LEFT = 4;
    private static final int EXPECTED_TOP = 0;
    // sets with coordinates of the rotated pieces
    // L piece
    private static final Set<Pair<Integer, Integer>> COORDINATES_L = new HashSet<>();
    static {
        COORDINATES_L.add(new Pair<>(1, 0));
        COORDINATES_L.add(new Pair<>(1, 1));
        COORDINATES_L.add(new Pair<>(1, 2));
        COORDINATES_L.add(new Pair<>(0, 2));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_1_L = new HashSet<>();
    static {
        COORDINATES_1_L.add(new Pair<>(0, 0));
        COORDINATES_1_L.add(new Pair<>(1, 0));
        COORDINATES_1_L.add(new Pair<>(2, 0));
        COORDINATES_1_L.add(new Pair<>(2, 1));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_2_L = new HashSet<>();
    static {
        COORDINATES_2_L.add(new Pair<>(1, 0));
        COORDINATES_2_L.add(new Pair<>(1, 1));
        COORDINATES_2_L.add(new Pair<>(1, 2));
        COORDINATES_2_L.add(new Pair<>(2, 0));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_3_L = new HashSet<>();
    static {
        COORDINATES_3_L.add(new Pair<>(2, 1));
        COORDINATES_3_L.add(new Pair<>(1, 1));
        COORDINATES_3_L.add(new Pair<>(0, 0));
        COORDINATES_3_L.add(new Pair<>(0, 1));
    }
    // I piece
    private static final Set<Pair<Integer, Integer>> COORDINATES_I = new HashSet<>();
    static {
        COORDINATES_I.add(new Pair<>(0, 0));
        COORDINATES_I.add(new Pair<>(0, 1));
        COORDINATES_I.add(new Pair<>(0, 2));
        COORDINATES_I.add(new Pair<>(0, 3));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_1_I = new HashSet<>();
    static {
        COORDINATES_1_I.add(new Pair<>(0, 0));
        COORDINATES_1_I.add(new Pair<>(1, 0));
        COORDINATES_1_I.add(new Pair<>(2, 0));
        COORDINATES_1_I.add(new Pair<>(3, 0));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_2_I = new HashSet<>();
    static {
        COORDINATES_2_I.add(new Pair<>(1, 0));
        COORDINATES_2_I.add(new Pair<>(1, 1));
        COORDINATES_2_I.add(new Pair<>(1, 2));
        COORDINATES_2_I.add(new Pair<>(1, 3));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_3_I = new HashSet<>();
    static {
        COORDINATES_3_I.add(new Pair<>(2, 0));
        COORDINATES_3_I.add(new Pair<>(2, 1));
        COORDINATES_3_I.add(new Pair<>(2, 2));
        COORDINATES_3_I.add(new Pair<>(2, 3));
    }
    // J piece
    private static final Set<Pair<Integer, Integer>> COORDINATES_J = new HashSet<>();
    static {
        COORDINATES_J.add(new Pair<>(0, 0));
        COORDINATES_J.add(new Pair<>(1, 0));
        COORDINATES_J.add(new Pair<>(1, 1));
        COORDINATES_J.add(new Pair<>(1, 2));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_1_J = new HashSet<>();
    static {
        COORDINATES_1_J.add(new Pair<>(0, 0));
        COORDINATES_1_J.add(new Pair<>(0, 1));
        COORDINATES_1_J.add(new Pair<>(1, 0));
        COORDINATES_1_J.add(new Pair<>(2, 0));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_2_J = new HashSet<>();
    static {
        COORDINATES_2_J.add(new Pair<>(1, 0));
        COORDINATES_2_J.add(new Pair<>(1, 1));
        COORDINATES_2_J.add(new Pair<>(1, 2));
        COORDINATES_2_J.add(new Pair<>(2, 2));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_3_J = new HashSet<>();
    static {
        COORDINATES_3_J.add(new Pair<>(0, 1));
        COORDINATES_3_J.add(new Pair<>(1, 1));
        COORDINATES_3_J.add(new Pair<>(2, 0));
        COORDINATES_3_J.add(new Pair<>(2, 1));
    }
    // S piece
    private static final Set<Pair<Integer, Integer>> COORDINATES_S = new HashSet<>();
    static {
        COORDINATES_S.add(new Pair<>(0, 1));
        COORDINATES_S.add(new Pair<>(0, 2));
        COORDINATES_S.add(new Pair<>(1, 0));
        COORDINATES_S.add(new Pair<>(1, 1));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_1_S = new HashSet<>();
    static {
        COORDINATES_1_S.add(new Pair<>(0, 0));
        COORDINATES_1_S.add(new Pair<>(1, 0));
        COORDINATES_1_S.add(new Pair<>(1, 1));
        COORDINATES_1_S.add(new Pair<>(2, 1));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_2_S = new HashSet<>();
    static {
        COORDINATES_2_S.add(new Pair<>(1, 1));
        COORDINATES_2_S.add(new Pair<>(1, 2));
        COORDINATES_2_S.add(new Pair<>(2, 0));
        COORDINATES_2_S.add(new Pair<>(2, 1));
    }
    // Z piece
    private static final Set<Pair<Integer, Integer>> COORDINATES_Z = new HashSet<>();
    static {
        COORDINATES_Z.add(new Pair<>(0, 0));
        COORDINATES_Z.add(new Pair<>(0, 1));
        COORDINATES_Z.add(new Pair<>(1, 2));
        COORDINATES_Z.add(new Pair<>(1, 1));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_1_Z = new HashSet<>();
    static {
        COORDINATES_1_Z.add(new Pair<>(0, 1));
        COORDINATES_1_Z.add(new Pair<>(1, 0));
        COORDINATES_1_Z.add(new Pair<>(1, 1));
        COORDINATES_1_Z.add(new Pair<>(2, 0));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_2_Z = new HashSet<>();
    static {
        COORDINATES_2_Z.add(new Pair<>(1, 0));
        COORDINATES_2_Z.add(new Pair<>(1, 1));
        COORDINATES_2_Z.add(new Pair<>(2, 1));
        COORDINATES_2_Z.add(new Pair<>(2, 2));
    }
    // T piece
    private static final Set<Pair<Integer, Integer>> COORDINATES_T = new HashSet<>();
    static {
        COORDINATES_T.add(new Pair<>(1, 0));
        COORDINATES_T.add(new Pair<>(1, 1));
        COORDINATES_T.add(new Pair<>(1, 2));
        COORDINATES_T.add(new Pair<>(0, 1));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_1_T = new HashSet<>();
    static {
        COORDINATES_1_T.add(new Pair<>(0, 0));
        COORDINATES_1_T.add(new Pair<>(1, 0));
        COORDINATES_1_T.add(new Pair<>(1, 1));
        COORDINATES_1_T.add(new Pair<>(2, 0));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_2_T = new HashSet<>();
    static {
        COORDINATES_2_T.add(new Pair<>(1, 0));
        COORDINATES_2_T.add(new Pair<>(1, 1));
        COORDINATES_2_T.add(new Pair<>(1, 2));
        COORDINATES_2_T.add(new Pair<>(2, 1));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_3_T = new HashSet<>();
    static {
        COORDINATES_3_T.add(new Pair<>(0, 1));
        COORDINATES_3_T.add(new Pair<>(1, 1));
        COORDINATES_3_T.add(new Pair<>(1, 0));
        COORDINATES_3_T.add(new Pair<>(2, 1));
    }
    // CUSTOM piece (created by me)
    private static final Set<Pair<Integer, Integer>> COORDINATES_CUSTOM = new HashSet<>();
    static {
        COORDINATES_CUSTOM.add(new Pair<>(0, 0));
        COORDINATES_CUSTOM.add(new Pair<>(1, 0));
        COORDINATES_CUSTOM.add(new Pair<>(1, 1));
        COORDINATES_CUSTOM.add(new Pair<>(2, 0));
        COORDINATES_CUSTOM.add(new Pair<>(2, 1));
        COORDINATES_CUSTOM.add(new Pair<>(2, 2));
        COORDINATES_CUSTOM.add(new Pair<>(3, 1));
        COORDINATES_CUSTOM.add(new Pair<>(3, 2));
        COORDINATES_CUSTOM.add(new Pair<>(3, 3));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_1_CUSTOM = new HashSet<>();
    static {
        COORDINATES_1_CUSTOM.add(new Pair<>(0, 1));
        COORDINATES_1_CUSTOM.add(new Pair<>(0, 2));
        COORDINATES_1_CUSTOM.add(new Pair<>(0, 3));
        COORDINATES_1_CUSTOM.add(new Pair<>(1, 0));
        COORDINATES_1_CUSTOM.add(new Pair<>(1, 1));
        COORDINATES_1_CUSTOM.add(new Pair<>(1, 2));
        COORDINATES_1_CUSTOM.add(new Pair<>(2, 0));
        COORDINATES_1_CUSTOM.add(new Pair<>(2, 1));
        COORDINATES_1_CUSTOM.add(new Pair<>(3, 0));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_2_CUSTOM = new HashSet<>();
    static {
        COORDINATES_2_CUSTOM.add(new Pair<>(0, 0));
        COORDINATES_2_CUSTOM.add(new Pair<>(0, 1));
        COORDINATES_2_CUSTOM.add(new Pair<>(0, 2));
        COORDINATES_2_CUSTOM.add(new Pair<>(1, 1));
        COORDINATES_2_CUSTOM.add(new Pair<>(1, 2));
        COORDINATES_2_CUSTOM.add(new Pair<>(1, 3));
        COORDINATES_2_CUSTOM.add(new Pair<>(2, 2));
        COORDINATES_2_CUSTOM.add(new Pair<>(2, 3));
        COORDINATES_2_CUSTOM.add(new Pair<>(3, 3));
    }
    private static final Set<Pair<Integer, Integer>> COORDINATES_3_CUSTOM = new HashSet<>();
    static {
        COORDINATES_3_CUSTOM.add(new Pair<>(0, 3));
        COORDINATES_3_CUSTOM.add(new Pair<>(1, 2));
        COORDINATES_3_CUSTOM.add(new Pair<>(1, 3));
        COORDINATES_3_CUSTOM.add(new Pair<>(2, 1));
        COORDINATES_3_CUSTOM.add(new Pair<>(2, 2));
        COORDINATES_3_CUSTOM.add(new Pair<>(2, 3));
        COORDINATES_3_CUSTOM.add(new Pair<>(3, 0));
        COORDINATES_3_CUSTOM.add(new Pair<>(3, 1));
        COORDINATES_3_CUSTOM.add(new Pair<>(3, 2));
    }
    private final GameLogic game = new GameLogicImpl(Levels.LEVEL_1, Optional.empty());
    private final Movements mov = new MovementsImpl(this.game);

    /**
     * Tests the movement to the right. I test just the left of a random piece.
     */
    @org.junit.Test
    public void testMoveRight() {
        this.mov.moveRight();
        assertEquals(EXPECTED_LEFT, this.game.getCurrent().getLeft());
        this.game.getCurrent().setLeft(Board.ROWLENGTH);
        this.mov.moveRight();
        assertEquals(Board.ROWLENGTH, this.game.getCurrent().getLeft());
    }

    /**
     * Tests the movement to the left. I test just the left of a random piece.
     */
    @org.junit.Test
    public void testMoveLeft() {
        this.mov.moveLeft();
        assertEquals(EXPECTED_LEFT - 2, this.game.getCurrent().getLeft());
        this.game.getCurrent().setLeft(0);
        this.mov.moveLeft();
        assertEquals(0, this.game.getCurrent().getLeft());
    }

    /**
     * Tests the fix for rotation on the left wall (it is shifted to the
     * right).
     */
    @org.junit.Test
    public void testFixRotationLeft() {
        // example with piece J
        this.game.setCurrent(new PieceImpl(Type.J, Optional.empty(), Optional.empty(), Optional.empty()));
        // this is the right position
        this.game.getCurrent().setLeft(0);
        assertEquals(0, this.game.getCurrent().getLeft());
        this.mov.rotateClockwise();
        assertEquals(1, this.game.getCurrent().getLeft());
        this.game.getCurrent().setLeft(0);
        // now the piece is adjacent to the left wall
        this.mov.rotateClockwise();
        assertEquals(0, this.game.getCurrent().getLeft());
    }

    /**
     * Test the fix for rotation on the right wall (it is shifted to the
     * left).
     */
    @org.junit.Test
    public void testFixRotationRight() {
        // example with piece J
        this.game.setCurrent(new PieceImpl(Type.J, Optional.empty(), Optional.empty(), Optional.empty()));
        // right position
        this.game.getCurrent().setLeft(Board.ROWLENGTH - SIZE_2);
        assertEquals(Board.ROWLENGTH - SIZE_2, this.game.getCurrent().getLeft());
        this.mov.rotateCounterclockwise();
        assertEquals(Board.ROWLENGTH - SIZE_2, this.game.getCurrent().getLeft());
        this.game.getCurrent().setLeft(this.game.getCurrent().getLeft() + 1);
        // now the piece is adjacent to the left wall
        this.mov.rotateCounterclockwise();
        assertEquals(Board.ROWLENGTH - SIZE_2, this.game.getCurrent().getLeft());
    }

    /**
     * Test the fix for rotation on the bottom.
     */
    @org.junit.Test
    public void testFixRotationBottom() {
        // example with piece J
        this.game.setCurrent(new PieceImpl(Type.J, Optional.empty(), Optional.empty(), Optional.empty()));
        // right position
        this.game.getCurrent().setTop(Board.COLLENGTH - SIZE_1);
        assertEquals(Board.COLLENGTH - SIZE_1, this.game.getCurrent().getTop());
        this.mov.rotateClockwise();
        assertEquals(Board.COLLENGTH - SIZE_2, this.game.getCurrent().getTop());
    }

    /**
     * Tests the drop down. I test just the top of a random piece.
     */
    @org.junit.Test
    public void testDropDown() {
        this.mov.dropDown();
        assertEquals(EXPECTED_TOP + 1, this.game.getCurrent().getTop());
    }

    /**
     * Tests the clockwise rotation of piece L. Checks also the center.
     */
    @org.junit.Test
    public void testRotateClockwiseL() {
        // L piece
        this.game.setCurrent(new PieceImpl(Type.L, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_L, this.game.getCurrent().getCoordinates());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_1_L, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_2_L, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_3_L, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_L, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the clockwise rotation of piece I. Checks also the center.
     */
    @org.junit.Test
    public void testRotateClockwiseI() {
        this.game.setCurrent(new PieceImpl(Type.I, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_I, this.game.getCurrent().getCoordinates());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_1_I, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_2_I, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 2), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_1_I, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(2, 0), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_3_I, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(2, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the clockwise rotation of piece S. Checks also the center.
     */
    @org.junit.Test
    public void testRotateClockwiseS() {
        this.game.setCurrent(new PieceImpl(Type.S, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_S, this.game.getCurrent().getCoordinates());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_1_S, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_2_S, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_1_S, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_S, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the clockwise rotation of piece J. Checks also the center.
     */
    @org.junit.Test
    public void testRotateClockwiseJ() {
        this.game.setCurrent(new PieceImpl(Type.J, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_J, this.game.getCurrent().getCoordinates());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_1_J, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_2_J, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_3_J, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_J, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the clockwise rotation of piece Z. Checks also the center.
     */
    @org.junit.Test
    public void testRotateClockwiseZ() {
        this.game.setCurrent(new PieceImpl(Type.Z, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_Z, this.game.getCurrent().getCoordinates());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_1_Z, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_2_Z, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_1_Z, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_Z, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the clockwise rotation of piece T. Checks also the center.
     */
    @org.junit.Test
    public void testRotateClockwiseT() {
        this.game.setCurrent(new PieceImpl(Type.T, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_T, this.game.getCurrent().getCoordinates());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_1_T, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_2_T, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_3_T, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_T, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the clockwise rotation of piece CUSTOM. Checks also the center.
     */
    @org.junit.Test
    public void testRotateClockwiseCUSTOM() {
        this.game.setCurrent(new PieceImpl(Type.CUSTOM, Optional.of(COORDINATES_CUSTOM), Optional.of(Color.black),
                Optional.of(new Pair<>(1, 1))));
        assertEquals(COORDINATES_CUSTOM, this.game.getCurrent().getCoordinates());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_1_CUSTOM, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 2), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_2_CUSTOM, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(2, 2), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_3_CUSTOM, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(2, 1), this.game.getCurrent().getCenter());
        this.mov.rotateClockwise();
        assertEquals(COORDINATES_CUSTOM, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the counterclockwise rotation of piece L. Checks also the center.
     */
    @org.junit.Test
    public void testRotateCounterclockwiseL() {
        this.game.setCurrent(new PieceImpl(Type.L, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_L, this.game.getCurrent().getCoordinates());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_3_L, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_2_L, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_1_L, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_L, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the counterclockwise rotation of piece I. Checks also the center.
     */
    @org.junit.Test
    public void testRotateCounterclockwiseI() {
        this.game.setCurrent(new PieceImpl(Type.I, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_I, this.game.getCurrent().getCoordinates());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_1_I, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(2, 0), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_3_I, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(2, 2), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_1_I, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_2_I, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the counterclockwise rotation of piece S. Checks also the center.
     */
    @org.junit.Test
    public void testRotateCounterclockwiseS() {
        this.game.setCurrent(new PieceImpl(Type.S, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_S, this.game.getCurrent().getCoordinates());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_1_S, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_2_S, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_1_S, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_S, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the counterclockwise rotation of piece J. Checks also the center.
     */
    @org.junit.Test
    public void testRotateCounterclockwiseJ() {
        this.game.setCurrent(new PieceImpl(Type.J, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_J, this.game.getCurrent().getCoordinates());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_3_J, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_2_J, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_1_J, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_J, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the counterclockwise rotation of piece Z. Checks also the center.
     */
    @org.junit.Test
    public void testRotateCounterclockwiseZ() {
        this.game.setCurrent(new PieceImpl(Type.Z, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_Z, this.game.getCurrent().getCoordinates());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_1_Z, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_2_Z, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_1_Z, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_Z, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the counterclockwise rotation of piece T. Checks also the center.
     */
    @org.junit.Test
    public void testRotateCounterclockwiseT() {
        this.game.setCurrent(new PieceImpl(Type.T, Optional.empty(), Optional.empty(), Optional.empty()));
        assertEquals(COORDINATES_T, this.game.getCurrent().getCoordinates());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_3_T, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_2_T, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_1_T, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 0), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_T, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }

    /**
     * Tests the counterclockwise rotation of CUSTOM piece. Checks also the center.
     */
    @org.junit.Test
    public void testRotateCounterclockwiseCUSTOM() {
        this.game.setCurrent(new PieceImpl(Type.CUSTOM, Optional.of(COORDINATES_CUSTOM), Optional.of(Color.black),
                Optional.of(new Pair<>(1, 1))));
        assertEquals(COORDINATES_CUSTOM, this.game.getCurrent().getCoordinates());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_3_CUSTOM, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(2, 1), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_2_CUSTOM, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(2, 2), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_1_CUSTOM, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 2), this.game.getCurrent().getCenter());
        this.mov.rotateCounterclockwise();
        assertEquals(COORDINATES_CUSTOM, this.game.getCurrent().getCoordinates());
        assertEquals(new Pair<>(1, 1), this.game.getCurrent().getCenter());
    }
}
