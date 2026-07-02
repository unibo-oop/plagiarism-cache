package model.collision;

import java.util.Optional;

import model.entities.Ball;
import model.entities.Brick;
import model.entities.GameObject;
import model.entities.Paddle;
import model.entities.PowerUp;
import model.entities.Wall;
import model.utilities.Boundaries;
import model.utilities.Pair;

public interface CollisionController {

    /**
     * Check for collisions between the ball and the brick.
     * @param ball the object that can collide with the brick
     * @param brick the brick to check the collision
     * @return a pair consisting of the hit brick and the side on which it was hit
     */
    Optional<Pair<Brick, Boundaries>> checkBallCollisionsWithBrick(Ball ball, Brick brick);

    /**
     * Check for collisions between the object and the wall.
     * @param obj the object that can collide with the wall
     * @param wall border of the world
     * @return the side of the collision
     */
    Optional<Boundaries> checkGameObjCollisionsWithWall(Wall wall, GameObject obj);

    /**
     * Check for collisions between the ball and the paddle.
     * @param paddle the paddle to check the collision
     * @param ball the object that can collide with the paddle
     * @return the side of the collision
     */
    Optional<Boundaries> checkBallCollisionsWithPaddle(Ball ball, Paddle paddle);

    /**
     * Check for collisions between the pwup and the paddle.
     * @param pwup the object that can collide with the paddle
     * @param paddle the paddle to check the collision
     * @return a pair consisting of the type of powerUp and the side it was hit on
     */
    Optional<Pair<PowerUp, Boundaries>> checkPwUpCollisionWithPaddle(PowerUp pwup, Paddle paddle);

} 
