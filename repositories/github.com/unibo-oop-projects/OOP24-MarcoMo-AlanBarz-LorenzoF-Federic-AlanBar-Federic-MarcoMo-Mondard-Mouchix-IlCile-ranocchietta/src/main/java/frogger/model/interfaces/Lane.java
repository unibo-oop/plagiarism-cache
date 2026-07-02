package frogger.model.interfaces;

import java.util.Set;

import frogger.common.Direction;

/**
 * Model the concept of a lane in level.
 */
public interface Lane {
    /**
     * Add an obstacle to the list.
     * @param obstacle the obstacle to add
     */
    void addMovingObject(MovingObject obstacle);

    /**
     * Get the speed of the lane.
     * @return the lane speed
     */
    float getSpeed();

    /**
     * Get the direction of the lane.
     * @return the lane direction
     */
    Direction getDirection();

    /**
     * Get the obstacles of the lane.
     * @return the Set of the obstacles
     */
    Set<MovingObject> getLaneObstacles();

    /**
     * Get whether the lane is completed or not.
     * @return true if is completed, false otherwise
     */
    boolean isCompleted();

    /**
     * Set the lane as completed.
     */
    void setCompleted();
}
