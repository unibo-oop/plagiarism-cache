package model.world;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.ball.Ball;
import model.balllaucher.BallLauncher;
import model.bucket.Bucket;
import model.obstacle.Obstacle;

/**
 * Interface to model the world that contains all the physical elements of the game.
 */
public interface GameWorld {

    /**
     * Update all the components of the {@link GameWorld}.
     * @param elapsedTime time passed from the last update in seconds
     * @param mousePosition current position of the mouse; needed to update the position of the {@link BallLauncher}
     */
    void update(double elapsedTime, Pair<Double, Double> mousePosition);

    /**
     * Set the {@link Ball}.
     * @param ball
     */
    void setBall(Ball ball);

    /**
     * Set the {@link Bucket}.
     * @param bucket
     */
    void setBucket(Bucket bucket);

    /**
     * Set the gravity of the physicalWorld.
     * @param gravity
     */
    void setGravity(double gravity);

    /**
     * Method to add a many {@link Obstacles}s.
     * @param obstacles
     */
    void addObstacles(List<Obstacle> obstacles);

    /**
     * @return the {@link Ball}
     */
    Ball getBall();

    /**
     * @return the {@link BallLauncher}
     */
    BallLauncher getBallLauncher();

    /**
     * @return the {@link Bucket}
     */
    Bucket getBucket();

    /**
     * @return a list of {@link Obstacle}s that have been hit in the current turn 
     */
    List<Obstacle> getHitObstacles();

    /**
     * @return a list of {@link Obstacle}s that have not been hit yet
     */
    List<Obstacle> getNotHitObstacles();

    /**
     * Set a new turn of the game.
     */
    void setNewTurn();

    /**
     * Remove from all the {@link Obstacle} that have been hit.
     */
    void removeHitObstacles();

}
