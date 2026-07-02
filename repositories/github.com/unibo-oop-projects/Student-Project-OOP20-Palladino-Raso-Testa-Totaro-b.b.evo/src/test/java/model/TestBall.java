package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import model.entities.Ball;
import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.Wall;
import model.utilities.Angle;
import model.utilities.Difficulty;
import model.utilities.DirVector;
import model.utilities.ObjectInit;
import model.utilities.Position;

public class TestBall {

    private static final String PATH = "Images/ball/defaultBall.png";
    private static final int NEGATIVE = -1;
    private static final double FAST_SPEED = 1;
    private static final double NULL_SPEED = 0;
    private static final double LOW_SPEED = 0.1;
    private static final int WALL_DIM = 100;
    private static final int BALL_DIM = 10;
    private static final int ANGLE_SOUTH = 270;
    private static final int ANGLE_WEST = 180;
    private static final int ANGLE_NORTH = 90;
    private static final int ANGLE_EAST = 0;
    private static final int STANDARD_POS = 50;
    private static final int AXIS_POS = 0;
    private static final int RIGHT_ANGLE_POS = 90;
    private static final int MOV_SOUTH_WEST_POS = 46;
    private static final int MOV_NORTH_EAST_POS = 54;
    private static final int NEWPOS_LOW_SPEED = 51;
    private static final int NEWPOS_FAST_SPEED = 60;
    private static final int TIME_ELAPSED_LOW = 10;
    private static final int TIME_ELAPSED_HIGH = 1000;

    /**
     * create a ball and check that the builder sets all the fields correctly.
     */
    @Test
    public void ballCreation() {
        final Ball ball = new Ball.Builder().position(ObjectInit.BALL.getStartPos())
                .direction(Angle.MIDDLE_LEFT.getAngleVector()).height(ObjectInit.BALL.getInitHeight())
                .width(ObjectInit.BALL.getInitWidth()).speed(Difficulty.HARD.getBallVelocity())
                .path(PATH)
                .build();
        assertEquals(ObjectInit.BALL.getStartPos(), ball.getPos());
        assertEquals(Angle.MIDDLE_LEFT.getAngleVector(), ball.getDirVector());
        assertEquals(Difficulty.HARD.getBallVelocity(), ball.getSpeed());
        assertEquals(ObjectInit.BALL.getInitHeight(), ball.getHeight());
        assertEquals(ObjectInit.BALL.getInitWidth(), ball.getWidth());
    }
 
    /**
    * create a ball with the wrong fields and check that exceptions are thrown.
    */
    @Test
    public void failBallCreation() {
        final Ball.Builder ballBuilder = new Ball.Builder().position(null);
        assertThrows(IllegalStateException.class, () -> ballBuilder.build());
        ballBuilder.position(ObjectInit.BALL.getStartPos()).direction(null);
        assertThrows(IllegalStateException.class, () -> ballBuilder.build());
        ballBuilder.position(ObjectInit.BALL.getStartPos()).direction(Angle.RIGHT.getAngleVector()).height(NEGATIVE);
        assertThrows(IllegalStateException.class, () -> ballBuilder.build());
        ballBuilder.position(ObjectInit.BALL.getStartPos()).direction(Angle.MIDDLE_RIGHT.getAngleVector())
            .height(ObjectInit.BALL.getInitHeight()).width(NEGATIVE);
        assertThrows(IllegalStateException.class, () -> ballBuilder.build());
    }

    /**
     *  put ball into the world. Update the time to see the movement of the ball.
     */
    @Test
    public void ballMovement() {
        final GameBoard board = new GameBoardImpl(new Wall(WALL_DIM, WALL_DIM), null);
        final Ball.Builder ballBuilder = new Ball.Builder();
        ballBuilder.height(ObjectInit.BALL.getInitHeight()).width(ObjectInit.BALL.getInitWidth())
            .speed(Difficulty.NORMAL.getBallVelocity()).path(PATH);

        // south direction
        double py = Math.sin(Math.toRadians(ANGLE_SOUTH));
        double px = Math.cos(Math.toRadians(ANGLE_SOUTH));
        ballBuilder.position(new Position(STANDARD_POS, STANDARD_POS)).direction(new DirVector(px, py));
        board.setBalls(Arrays.asList(ballBuilder.build()));
        assertEquals(new Position(STANDARD_POS, STANDARD_POS), board.getBalls().stream().findFirst().get().getPos());
        board.updateState(TIME_ELAPSED_LOW);
        assertEquals(new Position(STANDARD_POS, MOV_SOUTH_WEST_POS), board.getBalls().stream().findFirst().get().getPos());

        // west direction
        py = Math.sin(Math.toRadians(ANGLE_WEST));
        px = Math.cos(Math.toRadians(ANGLE_WEST));
        ballBuilder.position(new Position(STANDARD_POS, STANDARD_POS)).direction(new DirVector(px, py));
        board.setBalls(Arrays.asList(ballBuilder.build()));
        assertEquals(new Position(STANDARD_POS, STANDARD_POS), board.getBalls().stream().findFirst().get().getPos());
        board.updateState(TIME_ELAPSED_LOW);
        assertEquals(new Position(MOV_SOUTH_WEST_POS, STANDARD_POS), board.getBalls().stream().findFirst().get().getPos());

        // north direction
        py = Math.sin(Math.toRadians(ANGLE_NORTH));
        px = Math.cos(Math.toRadians(ANGLE_NORTH));
        ballBuilder.position(new Position(STANDARD_POS, STANDARD_POS)).direction(new DirVector(px, py));
        board.setBalls(Arrays.asList(ballBuilder.build()));
        assertEquals(new Position(STANDARD_POS, STANDARD_POS), board.getBalls().stream().findFirst().get().getPos());
        board.updateState(TIME_ELAPSED_LOW);
        assertEquals(new Position(STANDARD_POS, MOV_NORTH_EAST_POS), board.getBalls().stream().findFirst().get().getPos());

        // east direction
        py = Math.sin(Math.toRadians(ANGLE_EAST));
        px = Math.cos(Math.toRadians(ANGLE_EAST));
        ballBuilder.position(new Position(STANDARD_POS, STANDARD_POS)).direction(new DirVector(px, py));
        board.setBalls(Arrays.asList(ballBuilder.build()));
        assertEquals(new Position(STANDARD_POS, STANDARD_POS), board.getBalls().stream().findFirst().get().getPos());
        board.updateState(TIME_ELAPSED_LOW);
        assertEquals(new Position(MOV_NORTH_EAST_POS, STANDARD_POS), board.getBalls().stream().findFirst().get().getPos());
    }

    /**
     * update time in the world and check that for equal time intervals, if the ball
     * has a higher speed, it will have covered a greater space.
     */
    @Test
    public void ballSpeed() {
        final double py = Math.sin(Math.toRadians(ANGLE_EAST));
        final double px = Math.cos(Math.toRadians(ANGLE_EAST));
        final GameBoard world = new GameBoardImpl(new Wall(WALL_DIM, WALL_DIM), null);
        final Ball.Builder ballBuilder = new Ball.Builder();
        ballBuilder.height(ObjectInit.BALL.getInitHeight()).width(ObjectInit.BALL.getInitWidth()).position(new Position(STANDARD_POS, STANDARD_POS))
            .direction(new DirVector(px, py)).path(PATH);
        // 0 speed
        ballBuilder.speed(NULL_SPEED);
        world.setBalls(Arrays.asList(ballBuilder.build()));
        assertEquals(new Position(STANDARD_POS, STANDARD_POS), world.getBalls().stream().findFirst().get().getPos());
        world.updateState(TIME_ELAPSED_HIGH);
        assertEquals(new Position(STANDARD_POS, STANDARD_POS), world.getBalls().stream().findFirst().get().getPos());

        // 0.1 speed
        ballBuilder.speed(LOW_SPEED);
        world.setBalls(Arrays.asList(ballBuilder.build()));
        assertEquals(new Position(STANDARD_POS, STANDARD_POS), world.getBalls().stream().findFirst().get().getPos());
        world.updateState(TIME_ELAPSED_LOW);
        assertEquals(new Position(NEWPOS_LOW_SPEED, STANDARD_POS), world.getBalls().stream().findFirst().get().getPos());

        // 1 speed
        ballBuilder.speed(FAST_SPEED);
        world.setBalls(Arrays.asList(ballBuilder.build()));
        assertEquals(new Position(STANDARD_POS, STANDARD_POS), world.getBalls().stream().findFirst().get().getPos());
        world.updateState(TIME_ELAPSED_LOW);
        assertEquals(new Position(NEWPOS_FAST_SPEED, STANDARD_POS), world.getBalls().stream().findFirst().get().getPos());
    }

    /**
     * Builds the ball near to the edge and updates the time so that the ball collides with the edge.
     * Invert the x component of the velocity vector, if the ball hits a horizontal wall,
     * and the y component if the ball collides with the vertical wall.
     */
    @Test
    public void ballWallPhysics() {
        // collision with wall vertical
        double py = Math.sin(Math.toRadians(ANGLE_EAST));
        double px = Math.cos(Math.toRadians(ANGLE_EAST));
        final GameBoard world = new GameBoardImpl(new Wall(WALL_DIM, WALL_DIM), null);
        final Ball.Builder ballBuilder = new Ball.Builder();
        ballBuilder.height(BALL_DIM).width(BALL_DIM).position(new Position(RIGHT_ANGLE_POS, STANDARD_POS)).direction(new DirVector(px, py))
            .speed(LOW_SPEED)
            .path(PATH);
        world.setBalls(Arrays.asList(ballBuilder.build()));
        assertEquals(new DirVector(px, py), world.getBalls().stream().findFirst().get().getDirVector());
        world.updateState(TIME_ELAPSED_LOW);
        assertEquals(new DirVector(-px, py), world.getBalls().stream().findFirst().get().getDirVector());

        // collision with wall orizontal
        px = Math.cos(Math.toRadians(ANGLE_SOUTH));
        py = Math.sin(Math.toRadians(ANGLE_SOUTH));
        ballBuilder.height(BALL_DIM).width(BALL_DIM).direction(new DirVector(px, py)).position(new Position(STANDARD_POS, AXIS_POS))
            .speed(LOW_SPEED).path(PATH);
        world.setBalls(Arrays.asList(ballBuilder.build()));
        assertEquals(new DirVector(px, py), world.getBalls().stream().findFirst().get().getDirVector());
        world.updateState(TIME_ELAPSED_LOW);
        assertEquals(new DirVector(px, -py), world.getBalls().stream().findFirst().get().getDirVector());
    }
}
