package controller.ballscontroller;

import model.ball.Ball;

import java.util.Set;

public interface BallsController {

    /**
     * @param x the x of the point selected
     * @param y the y of the point selected
     * @return true direction is valid else false
     */

    boolean isValidLaunch(double x, double y);

    /**
     * @param x the x of the point selected
     * @param y the y of the point selected
     * @throws IllegalStateException    if the balls are in movement
     */

    void launchBalls(double x, double y);

    /**
     * add a ball to the other
     */

    void addBall();

    /**
     * pause the movement of the balls
     */

    void pause();

    /**
     * restart the movement of the balls
     */

    void restart();

    /**
     * @return true if the controller is paused
     */
    boolean isPaused();

    /**
     * @return the set of the balls
     */

    Set<Ball> getBalls();

    /**
     * @return true if all the balls have direction equala to the vector arithmetic null else false
     */

    boolean areBallsStationary();
}
