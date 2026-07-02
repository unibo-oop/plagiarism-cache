package it.dpg.minigames.ballgame.model;

/**
 * coordinates go from 0 to 100
 */
public interface Boundary {
    /**
     * @return x position of the starting point
     */
    double getStartX();

    /**
     * @return y position of the starting point
     */
    double getStartY();

    /**
     * @return x position of the end point
     */
    double getEndX();

    /**
     * @return y position of the end point
     */
    double getEndY();

    /**
     * @return true if it's vertical
     */
    boolean isVertical();

    /**
     * @return true if it's horizontal
     */
    boolean isHorizontal();

    /**
     * @param ballCenterX x coordinate of the center of the ball
     * @param ballCenterY y coordinate of the center of the ball
     * @param ballRadius  radius value of the ball
     * @return true if a collision is detected with a ball of the specified characteristics
     */
    boolean isBallColliding(double ballCenterX, double ballCenterY, double ballRadius);

    /**
     * @return the type of collision the boundary provides
     */
    CollisionType getCollisionType();
}
