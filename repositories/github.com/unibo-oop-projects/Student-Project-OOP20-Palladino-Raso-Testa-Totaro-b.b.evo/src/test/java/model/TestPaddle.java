package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.input.ControllerInput;
import controller.input.ControllerInputImpl;
import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.Paddle;
import model.entities.Wall;
import model.utilities.Boundaries;
import model.utilities.DirVector;
import model.utilities.ObjectInit;
import model.utilities.Position;

public class TestPaddle {

    private static final String PATH = "Images/paddle/defaultPaddle.png";
    private static final int WALL_DIM = 600;
    private static final int POS_PADDLE_P1_X = 290;
    private static final int POS_PADDLE_P1_Y = 540;
    private static final int POS_PADDLE_P2_X = 600;
    private static final int POS_PADDLE_P2_Y = 580;
    private static final int POS_PADDLE_P3_Y = 580;
    private static final int POS_PADDLE_P3_X = -10;
    private static final int ZERO = 0;
    private static final int NEWPOS_PADDLE_X = 298;
    private static final int NEWPOS_PADDLE_Y = 540;
    private static final int PADDLE_WIDTH = 78;
    private static final int PADDLE_HEIGHT = 20;
    private static final int TIME_ELAPSED = 20;

    private GameBoard board;
    private Paddle p1;
    private Paddle p2;
    private Paddle p3;
    private final Map<Paddle, ControllerInput> inputController = new HashMap<>();

    /**
     * Initialize fields before the test start.
     */
    @BeforeEach
    public void createEntity() {
        this.board = new GameBoardImpl(new Wall(WALL_DIM, WALL_DIM), null);
        this.p1 = createPaddle(ObjectInit.PADDLE.getStartPos());
        this.p2 = createPaddle(new Position(POS_PADDLE_P2_X, POS_PADDLE_P2_Y));
        this.p3 = createPaddle(new Position(POS_PADDLE_P3_X, POS_PADDLE_P3_Y));
        this.board.setPaddle(p1);
        this.inputController.put(p1, new ControllerInputImpl());
    }

    /**
     * Check that the builder sets all the fields correctly.
     */
    @Test
    public void checkBuilderPaddle() {
        assertEquals(ObjectInit.PADDLE.getStartPos(), p1.getPos());
        assertEquals(new DirVector(ZERO, ZERO), p1.getDirVector());
        assertEquals(ObjectInit.PADDLE.getInitHeight(), p1.getHeight());
        assertEquals(ObjectInit.PADDLE.getInitWidth(), p1.getWidth());
    }

    /**
     * Test if the builder create correctly the players.
     */
    @Test
    public void testPaddleCreation() {
        assertEquals(new Position(POS_PADDLE_P1_X, POS_PADDLE_P1_Y), p1.getPos());
        assertEquals(PADDLE_WIDTH, p1.getWidth());
        assertEquals(PADDLE_HEIGHT, p1.getHeight());
        assertThrows(IllegalStateException.class, () -> new Paddle.Builder().build());
        assertThrows(IllegalStateException.class, () -> new Paddle.Builder()
                .position(null).build());
        assertThrows(IllegalStateException.class, () -> new Paddle.Builder()
                .width(-1).build());
        assertThrows(IllegalStateException.class, () -> new Paddle.Builder()
                .height(0).build());
    }

    /**
     * Test if the collision with boundaries are correctly checked.
     */
    @Test
    public void testCollisionWithBoundaries() {
        assertFalse(board.checkGameObjCollisionsWithWall(p1).isPresent());
        assertTrue(board.checkGameObjCollisionsWithWall(p2).isPresent());
        assertTrue(board.checkGameObjCollisionsWithWall(p3).isPresent());
        assertEquals(Boundaries.SIDE_LEFT, board.checkGameObjCollisionsWithWall(p3).get());
        assertEquals(Boundaries.SIDE_RIGHT, board.checkGameObjCollisionsWithWall(p2).get());
    }

    /**
     * Test if the ControllerInput notify correctly the movement of the player
     * and if it moves depending on speed of player and time elapsed given by the gameLoop.
     */
    @Test
    public void testPlayerMovement() {
        //move paddle right
        assertEquals(new Position(POS_PADDLE_P1_X, POS_PADDLE_P1_Y), p1.getPos());
        inputController.get(p1).setMoveRight(true);
        inputController.entrySet().forEach(i -> {
            board.movePaddle(i.getValue());
        });
        board.updateState(TIME_ELAPSED);
        assertEquals(new Position(NEWPOS_PADDLE_X, NEWPOS_PADDLE_Y), p1.getPos());

        //move paddle left
        inputController.get(p1).setMoveLeft(true);
        inputController.entrySet().forEach(i -> {
            board.movePaddle(i.getValue());
        });
        board.updateState(TIME_ELAPSED);
        assertEquals(new Position(POS_PADDLE_P1_X, POS_PADDLE_P1_Y), p1.getPos());
    }

    private Paddle createPaddle(final Position pos) {
        return new Paddle.Builder()
                .position(pos)
                .width(PADDLE_WIDTH)
                .height(PADDLE_HEIGHT)
                .texturePath(PATH)
                .build();
    }
}
