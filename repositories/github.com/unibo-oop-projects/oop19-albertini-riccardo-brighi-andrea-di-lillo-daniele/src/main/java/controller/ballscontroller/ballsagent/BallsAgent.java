package controller.ballscontroller.ballsagent;

import model.ball.Ball;
import element.Point2D;

import java.util.Optional;

public interface BallsAgent {

    /**
     * move all the ball
     */
    void move();

    /**
     * @param endPoint the point to set like end of the movement
     */
    void setEndPoint(Point2D endPoint);

    /**
     * @return the start point
     */

    Optional<Point2D> getStartPoint();

    /**
     * @return the end point
     */

    Optional<Point2D> getEndPoint();

    /**
     * @param ball the ball to group with the other
     */
    void groupBall(Ball ball);

    /**
     * @return true if all the balls are stationary
     */
    boolean areBallsStationary();

    /**
     *
     * @return true if the ball are all in the same position
     */
    boolean areBallsGrouped();
}
