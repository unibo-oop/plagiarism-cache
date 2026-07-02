package model.ball;

import element.Point2D;
import element.Vector2D;

public interface Ball {

    /**
     * @return the radius of the ball
     */

    double getRadius();

    /**
     * @return the speed of the ball
     */

    double getSpeed();

    /**
     * increment the speed of the ball
     */

    void incrementSpeed();

    /**
     * @return damage inflicted by the ball to blocks
     */

    int getDamage();

    /**
     * @return the current position of the ball
     */

    Point2D getPosition();

    /**
     * @return the current direction of the ball
     */

    Vector2D getDirection();

    /**
     * @param newDirection the new direction of the ball
     * @throws IllegalArgumentException if @param newDirection is null
     *                                  <p>
     *                                  change the direction of the ball
     *                                  </p>
     */

    void setDirection(Vector2D newDirection);

    /**
     * @return true if the ball is stationary, false otherwise
     */

    boolean isStationary();

    /**
     * @param timeInterval elapsed time interval
     *                     <p>
     *                     move the ball to the next position based on current direction, speed and @param timeInterval
     *                     </p>
     */

    void moveByTime(long timeInterval);

    /**
     * @param distance the distance of the movement
     *                 <p>
     *                 move the ball to the next position based on current direction and @param distance
     *                 </p>
     */

    void moveByDistance(double distance);

    /**
     * @param centerPoint  the point of the ball's center when collides with other elements that influence is direction
     * @param newDirection the new direction of the ball
     *                     <p>
     *                     update the ball position based on @param centerPoint and @param newDirection
     *                     </p>
     */

    void collision(Point2D centerPoint, Vector2D newDirection);

    /**
     * @param destination the destination to reach
     */
    void setDestination(Point2D destination);
}
