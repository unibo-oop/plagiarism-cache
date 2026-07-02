package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.GameBoard;
import model.entities.GameBoardImpl;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.entities.Wall;
import model.utilities.Angle;
import model.utilities.Boundaries;
import model.utilities.Difficulty;
import model.utilities.BrickStatus;
import model.utilities.ObjectInit;
import model.utilities.Pair;
import model.utilities.Position;
import resource.routing.BallTexture;
import resource.routing.BrickTexture;
import resource.routing.PaddleTexture;
import resource.routing.PowerUpDropTexture;
import resource.routing.PowerUpTexture;

public class TestBoard {

    private static final String PATH_PADDLE = "Images/paddle/defaultPaddle.png";
    private static final String PATH_BALL = "Images/ball/defaultBall.png";
    private static final String PATH_POWERUP = "Images/powerup/defaultPowerUp.png";
    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 100;
    private static final int NUM_OBJ = 4;
    private static final int STAND_POS_X = 50;
    private static final int STAND_POS_Y = 50;
    private static final int POS_POWERUP_INIT_X = 270;
    private static final int POS_POWERUP_INIT_Y = 510;
    private static final int PW_HEIGHT = 10;
    private static final int PW_WIDTH = 10;
    private static final int POS_FIFTY = 50;
    private static final int POS_NEGFIVE = -5;
    private static final int POS_FIVE = 5;
    private static final int POS_NINETYFIVE = 95;
    private static final int POS_TWENTYTH = 23;
    private static final int POS_FIFTYFIVE = 55;
    private static final int POS_FOURTYFIVE = 45;
    private static final int POS_THIRTY = 30;
    private static final int POS_EIGHTYFIVE = 85;
    private static final int POS_SEVENTYFIVE = 75;
    private static final int POS_SYXTY = 60;
    private static final int POS_HUNDREDFIVE = 105;
    private static final int POS_HUNDREDTWENTY = 120;
    private static final int WALL_COST = 600;
    private final List<PowerUp> listPwUp = new ArrayList<>();

    private final GameBoard board = new GameBoardImpl(new Wall(200, 200), null);
    private final Brick brick = new Brick.Builder().position(new Position(STAND_POS_X, STAND_POS_Y))
                                             .height(10)
                                             .width(10)
                                             .texture(BrickTexture.BRICK_TEXTURE_DEFAULT.getPath())
                                             .status(BrickStatus.NOT_DESTRUCTIBLE)
                                             .durability(1)
                                             .build();
    private final Ball ball = new Ball.Builder().position(new Position(STAND_POS_X, STAND_POS_Y))
                                          .direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1))
                                          .height(ObjectInit.BALL.getInitHeight())
                                          .width(ObjectInit.BALL.getInitWidth())
                                          .speed(Difficulty.NORMAL.getBallVelocity())
                                          .path(PATH_BALL)
                                          .build();
    private final Paddle paddle = new Paddle.Builder()
                                      .position(ObjectInit.PADDLE.getStartPos())
                                      .height(ObjectInit.PADDLE.getInitHeight())
                                      .width(ObjectInit.PADDLE.getInitWidth())
                                      .texturePath(PATH_PADDLE)
                                      .build();

    private final String brickTexturePath = PowerUpTexture.getThemeNameByPath(PATH_POWERUP).getTheme();
    private final PowerUp pwUp = new PowerUp(new Position(POS_POWERUP_INIT_X, POS_POWERUP_INIT_Y), PW_HEIGHT, PW_WIDTH, 
            PowerUpDropTexture.getPowerUpDropTextureByName(brickTexturePath).getPath());

    /**
     * Check insert number of ball on board is always 1.
     */ 
    @Test
    public void insertBallTest() {
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        final Ball.Builder ballBuilder = new Ball.Builder();
        ballBuilder.position(new Position(STAND_POS_X, STAND_POS_Y))
                 .direction(Angle.MIDDLE_LEFT.getAngleVector().mul(-1))
                 .height(ObjectInit.BALL.getInitHeight())
                 .width(ObjectInit.BALL.getInitWidth())
                 .speed(Difficulty.HARD.getBallVelocity())
                 .path(BallTexture.BALL_DEFAULT.getPath())
                 .build();
        assertTrue(board.getSceneEntities().isEmpty());
        board.setBalls(IntStream.range(MIN_RANGE, MAX_RANGE)
                .mapToObj(i -> ballBuilder.build())
                .collect(Collectors.toList()));
        assertEquals(1, board.getSceneEntities().size());
    }

    /**
     * Check insert number of brick on board.
     */
    @Test
    public void insertBrickTest() {
        final Brick.Builder brickBuilder = new Brick.Builder();
        brickBuilder.position(new Position(10, 10))
                    .height(10)
                    .width(10)
                    .texture(BrickTexture.BRICK_TEXTURE_DEFAULT.getPath())
                    .status(BrickStatus.NOT_DESTRUCTIBLE)
                    .durability(1)
                    .build();
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        assertTrue(board.getSceneEntities().isEmpty());
        board.setBricks(IntStream.range(MIN_RANGE, MAX_RANGE)
                                 .mapToObj(i -> brickBuilder.build())
                                 .collect(Collectors.toList()));
        assertEquals(MAX_RANGE, board.getSceneEntities().size());
    }

    /**
     * Check insert of paddle on board.
     */
    @Test
    public void insertPaddleTest() {
        final Paddle.Builder paddle = new Paddle.Builder();
        paddle.position(ObjectInit.PADDLE.getStartPos())
            .height(ObjectInit.PADDLE.getInitHeight())
            .width(ObjectInit.PADDLE.getInitWidth())
            .texturePath(PaddleTexture.PADDLE_DEFAULT.getPath())
            .build();
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        assertTrue(board.getSceneEntities().isEmpty());
        board.setPaddle(paddle.build());
        assertEquals(1, board.getSceneEntities().size());
    }
    /**
     * check that by inserting a number of powerUp in the board 
     * they have been inserted correctly.
     */
    @Test
    public void insertPowerUpTest() {
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        powerUpCreation();
        assertTrue(board.getSceneEntities().isEmpty());
        this.listPwUp.addAll(IntStream.range(MIN_RANGE, MAX_RANGE)
                .mapToObj(i -> powerUpCreation())
                .collect(Collectors.toList()));
        board.setPowerUps(this.listPwUp);
        assertEquals(MAX_RANGE, board.getSceneEntities().size());
    }

    /**
     * method that creates a powerup.
     * @return new PowerUp object
     */
    private PowerUp powerUpCreation() {
        return new PowerUp(new Position(POS_POWERUP_INIT_X, POS_POWERUP_INIT_Y), PW_HEIGHT, PW_WIDTH, PATH_POWERUP);
    }

    /**
     * check that by inserting different types 
     * of game objects they are inserted correctly.
     */
    @Test
    public void insertEntityTest() {
        final GameBoard board = new GameBoardImpl(new Wall(WALL_COST, WALL_COST), null);
        assertTrue(board.getSceneEntities().isEmpty());
        board.setBalls(Arrays.asList(ball));
        board.setPaddle(paddle);
        board.setBricks(Arrays.asList(brick));
        board.setPowerUps(Arrays.asList(pwUp));
        assertEquals(NUM_OBJ, board.getSceneEntities().size());
    }

    /**
     * Check for collisions between the ball and the game wall.
     */
    @Test
    public void checkBallBoardCollision() {
        final GameBoard board = new GameBoardImpl(new Wall(100, 100), null);
        assertTrue(board.getSceneEntities().isEmpty());
        final Ball.Builder ballBuilder = new Ball.Builder();
        ballBuilder.position(new Position(POS_NINETYFIVE, POS_FIFTY))
            .direction(Angle.LEFT.getAngleVector().mul(-1))
            .height(ObjectInit.BALL.getInitHeight())
            .width(ObjectInit.BALL.getInitWidth())
            .speed(Difficulty.HARD.getBallVelocity())
            .path(PATH_BALL);
        board.setBalls(Arrays.asList(ballBuilder.build()));
        //set ball pos to the right edge and check for a collision
        assertEquals(Boundaries.SIDE_RIGHT, board.checkGameObjCollisionsWithWall(ballBuilder.build()).get());
        //set ball pos to the bottom edge and check for a collision
        ballBuilder.position(new Position(POS_FIFTY, POS_NINETYFIVE));
        assertEquals(Boundaries.LOWER, board.checkGameObjCollisionsWithWall(ballBuilder.build()).get());
        //set ball pos to the top edge and check for a collision
        ballBuilder.position(new Position(POS_FIFTY, POS_NEGFIVE));
        assertEquals(Boundaries.UPPER, board.checkGameObjCollisionsWithWall(ballBuilder.build()).get());
        //set ball pos to the left edge and check for a collision
        ballBuilder.position(new Position(POS_NEGFIVE, POS_FIFTY));
        assertEquals(Boundaries.SIDE_LEFT, board.checkGameObjCollisionsWithWall(ballBuilder.build()).get());
        //set ball pos to in the middle of the world and check for no collision;
        ballBuilder.position(new Position(POS_FIFTY, POS_FIFTY));
        assertEquals(Optional.empty(), board.checkGameObjCollisionsWithWall(ballBuilder.build()));
    }

    /**
     * Check for collisions between the paddle and the game wall.
     */
    @Test
    public void checkPaddleBoardCollision() {
        final GameBoard board = new GameBoardImpl(new Wall(100, 100), null);
        assertTrue(board.getSceneEntities().isEmpty());
        final Paddle.Builder paddleBuilder = new Paddle.Builder();
        paddleBuilder.position(new Position(POS_TWENTYTH, POS_FIFTY))
            .height(ObjectInit.PADDLE.getInitHeight())
            .width(ObjectInit.PADDLE.getInitWidth())
            .texturePath(PATH_PADDLE);
        board.setPaddle(paddleBuilder.build());
        //set paddle pos to the right edge and check for a collision
        assertEquals(Boundaries.SIDE_RIGHT, board.checkGameObjCollisionsWithWall(paddleBuilder.build()).get());
        //set paddle pos to the left edge and check for a collision
        paddleBuilder.position(new Position(POS_NEGFIVE, POS_FIFTY));
        assertEquals(Boundaries.SIDE_LEFT, board.checkGameObjCollisionsWithWall(paddleBuilder.build()).get());
        //set paddle pos to in the middle of the world and check for no collision;
        paddleBuilder.position(new Position(POS_FIVE, POS_FIFTY));
        assertEquals(Optional.empty(), board.checkGameObjCollisionsWithWall(paddleBuilder.build()));
    }

    /**
     * Check for collisions between the PowerUp and the game wall.
     */
    @Test
    public void checkPowerUpBoardCollision() {
        final GameBoard board = new GameBoardImpl(new Wall(100, 100), null);
        assertTrue(board.getSceneEntities().isEmpty());
        //set powerUp pos to the bottom edge and check for a collision
        final PowerUp powerUp = new PowerUp(new Position(POS_FIFTY, POS_NINETYFIVE), PW_HEIGHT, PW_WIDTH, PATH_POWERUP);
        assertEquals(Boundaries.LOWER, board.checkGameObjCollisionsWithWall(powerUp).get());
        //set paddle pos to in the middle of the world and check for no collision;
        powerUp.setPos(new Position(POS_FIVE, POS_FIFTY));
        assertEquals(Optional.empty(), board.checkGameObjCollisionsWithWall(powerUp));
    }

    /**
     * 
     * Check that by positioning the ball in contact with a brick,
     * a collision is detected based on the wall where it occurs.
     */
   @Test
    public void checkBrickCollision() {
       brick.setPos(new Position(POS_FIFTY, POS_FIFTY));
       ball.setPos(new Position(POS_FIFTYFIVE, POS_THIRTY));
       board.setBricks(Arrays.asList(this.brick));
       board.setBalls(Arrays.asList(this.ball));
       //fill the map of the last presence areas of the bricks
       Optional<Pair<Brick, Boundaries>> collisionResult = board.checkBallCollisionsWithBrick(board.getBalls().stream().findFirst().get());
       assertEquals(Optional.empty(), collisionResult);
       board.getBalls().stream().findFirst().get().setPos(new Position(POS_FIFTYFIVE, POS_FOURTYFIVE));
       //set ball pos on the same axis perpendicular to the brick wall and check the collisions
       collisionResult = board.checkBallCollisionsWithBrick(board.getBalls().stream().findFirst().get());
       assertTrue(collisionResult.isPresent());
       assertEquals(Boundaries.LOWER, collisionResult.get().getY());
       assertEquals(this.brick, collisionResult.get().getX());
    }

    /**
     * Check that according to the collision zone with the paddle
     * the ball has a direction. The more the collision zone will be to the right,
     * the more acute the angle will be.
    */
    @Test
    public void checkBallPaddleCollision() {
        paddle.setPos(new Position(POS_FIFTY, POS_FIFTY));
        ball.setPos(new Position(POS_FIFTYFIVE, POS_THIRTY));
        board.setPaddle(this.paddle);
        board.setBalls(Arrays.asList(this.ball));
        Pair<Optional<Boundaries>, Optional<Angle>> collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
        //fill the map of the last presence areas of the player
        assertEquals(Optional.empty(), collisionResult.getX());
        assertEquals(Optional.empty(), collisionResult.getY());
        board.getBalls().stream().findFirst().get().setPos(new Position(POS_FIFTYFIVE, POS_FOURTYFIVE));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          //touching the top of the player will also present the direction that the ball will take after the collision
          assertTrue(collisionResult.getX().isPresent());
          assertTrue(collisionResult.getY().isPresent());
          assertEquals(Boundaries.UPPER, collisionResult.getX().get());
          assertEquals(Angle.LEFT, collisionResult.getY().get());
          //I move the ball more and more to the right, making the corner more and more sharp
          board.getBalls().stream().findFirst().get().setPos(new Position(POS_SYXTY, POS_FOURTYFIVE));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Angle.LEFT, collisionResult.getY().get());
          //I move the ball more and more to the right, making the corner more and more sharp
          board.getBalls().stream().findFirst().get().setPos(new Position(POS_SEVENTYFIVE, POS_FOURTYFIVE));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Angle.MIDDLE_LEFT, collisionResult.getY().get());
          //I move the ball more and more to the right, making the corner more and more sharp
          board.getBalls().stream().findFirst().get().setPos(new Position(POS_EIGHTYFIVE, POS_FOURTYFIVE));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Angle.MIDDLE_RIGHT, collisionResult.getY().get());
          //I move the ball more and more to the right, making the corner more and more sharp
          board.getBalls().stream().findFirst().get().setPos(new Position(POS_HUNDREDFIVE, POS_FOURTYFIVE));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Angle.RIGHT, collisionResult.getY().get());
          //I move the ball more and more to the right, making the corner more and more sharp
          board.getBalls().stream().findFirst().get().setPos(new Position(POS_HUNDREDTWENTY, POS_FOURTYFIVE));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Angle.RIGHT, collisionResult.getY().get());
          //if the ball bounces on the player's side 
          //the direction is changed as if it had bounced on a normal vertical wall
          board.getBalls().stream().findFirst().get().setPos(new Position(POS_THIRTY, POS_FIFTYFIVE));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertTrue(collisionResult.getX().isEmpty());
          assertTrue(collisionResult.getY().isEmpty());
          board.getBalls().stream().findFirst().get().setPos(new Position(POS_FOURTYFIVE, POS_FIFTYFIVE));
          collisionResult = board.checkBallCollisionsWithPaddle(board.getBalls().stream().findFirst().get());
          assertEquals(Boundaries.SIDE_LEFT, collisionResult.getX().get());
          assertTrue(collisionResult.getY().isEmpty());
    }

    /**
     * Verify that by placing the powerUp in contact with the paddle, 
     * a collision is detected.
     * 
    */
    @Test
    public void checkPowerUpPaddleCollision() {
        paddle.setPos(new Position(POS_FIFTY, POS_FIFTY));
        pwUp.setPos(new Position(POS_FIFTYFIVE, POS_THIRTY));
        board.setPaddle(this.paddle);
        board.setPowerUps(Arrays.asList(this.pwUp));
        Optional<Pair<PowerUp, Boundaries>> collisionResult = board.checkPowerUpCollisionsWithPaddle(board.getPowerUps().stream().findFirst().get());
        //fill the map of the last presence areas of the player
        assertTrue(collisionResult.isEmpty());
        board.getPowerUps().stream().findFirst().get().setPos(new Position(POS_FIFTYFIVE, POS_FOURTYFIVE));
        collisionResult = board.checkPowerUpCollisionsWithPaddle(board.getPowerUps().stream().findFirst().get());
        //touching the top of the player will also present the direction that the ball will take after the collision
        assertTrue(collisionResult.isPresent());
        assertEquals(Boundaries.UPPER, collisionResult.get().getY());
        //if the ball bounces on the player's side 
        //the direction is changed as if it had bounced on a normal vertical wall
        board.getPowerUps().stream().findFirst().get().setPos(new Position(POS_THIRTY, POS_FIFTYFIVE));
        collisionResult = board.checkPowerUpCollisionsWithPaddle(board.getPowerUps().stream().findFirst().get());
        assertTrue(collisionResult.isEmpty());
        board.getPowerUps().stream().findFirst().get().setPos(new Position(POS_FOURTYFIVE, POS_FIFTYFIVE));
        collisionResult = board.checkPowerUpCollisionsWithPaddle(board.getPowerUps().stream().findFirst().get());
        assertEquals(Boundaries.SIDE_LEFT, collisionResult.get().getY());
    }
}
