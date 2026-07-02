package model.entities;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import controller.event.EventHandler;
import controller.event.GameBoardEventListeners;
import controller.input.ControllerInput;
import model.utilities.Angle;
import model.utilities.Boundaries;
import model.utilities.Pair;

/**
 * 
 * Interface that instantiates methods for creating the game board.
 */
public interface GameBoard extends GameBoardEventListeners {

    /**
     * 
     * @param balls added on gameboard
     */
    void setBalls(Collection<Ball> balls);

    /**
     * 
     * @return all the balls in the game
     */
    Set<Ball> getBalls();

    /**
     * 
     * @param ball to remove
     */
    void removeBall(Ball ball);

    /**
     * 
     * @param paddle add on gameboard
     */
    void setPaddle(Paddle paddle);

    /**
     * 
     * @return paddle
     */
    Paddle getpaddle();

    /**
     * 
     * @param bricks added on gameboard
     */
    void setBricks(Collection<Brick> bricks);

    /**
     * 
     * @return all the bricks in the gameboard
     */
    Set<Brick> getBricks();

    /**
     * 
     * @param brick to remove
     */
    void removeBrick(Brick brick);

    /**
     * 
     * @param pwup added on gameboard
     */
    void setPowerUps(Collection<PowerUp> pwup);

    /**
     * 
     * @return all the powerups in the gameboard
     */
    Set<PowerUp> getPowerUps();

    /**
     * 
     * @param pwup to remove
     */
    void removePowerUp(PowerUp pwup);

    /**
     * 
     * @param type of powerUp
     */
    void setTypePowerUp(String type);

    /**
     * 
     * @return type of powerUp
     */
    String getTypePowerUp();

    /**
     * 
     * Clears the powerup set.
     */
    void clearPowerUps();

    /**
     * 
     * @return the border of the gameboard
     */
    Wall getWall();

    /**
     * The GameBoard asks the collision controller to check 
     * if there have been collisions between wall and object.
     * @param obj the object to be checked
     * @return on what surface the object collides
     */
    Optional<Boundaries> checkGameObjCollisionsWithWall(GameObject obj);

    /**
     * The GameBoard asks the collision controller to check 
     * if there have been collisions between ball and bricks.
     * @param ball object that can collide
     * @return Pair of brick and border
     */
    Optional<Pair<Brick, Boundaries>> checkBallCollisionsWithBrick(Ball ball);

    /**
     * The GameBoard asks the collision controller to check 
     * if there have been collisions between ball and the paddle.
     * @param ball object that can collide
     * @return if a collision has occurred in the upper part of the player, 
     * the direction the ball will take is also calculated. 
     */
    Pair<Optional<Boundaries>, Optional<Angle>> checkBallCollisionsWithPaddle(Ball ball);

    /**
     * The GameBoard asks the collision controller to check 
     * if there have been collisions between powerUp and bricks.
     * @param pwUp object that can collide
     * @return Pair of pwUp and border
     */
    Optional<Pair<PowerUp, Boundaries>> checkPowerUpCollisionsWithPaddle(PowerUp pwUp);

    /**
     * 
     * @return a set of all the gameObj in the board
     */
    Set<GameObject> getSceneEntities();

    /**
     * 
     * @param elapsedTime the time difference delta time
     */
    void updateState(int elapsedTime);

    /**
     * Update paddle input component.
     * @param controllerInput controller that check the key pressed by user
     */
    void movePaddle(ControllerInput controllerInput);

    /**
     * @return the eventHandler 
     */
    EventHandler getEventHanlder();

}
