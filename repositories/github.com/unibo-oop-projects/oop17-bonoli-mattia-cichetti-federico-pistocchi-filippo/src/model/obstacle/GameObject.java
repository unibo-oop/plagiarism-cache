package model.obstacle;

import model.obstacle.GameObjectImpl.GameObjectType;

/**
 * This interface represents an obstacle moving in a lane.
 */
public interface GameObject {

    /**
     * 
     * @return the center of the obstacle.
     */
    double getCenter();

    /**
     * 
     * @param newCenter is the new center of the obstacle.
     */
    void setCenter(double newCenter);

    /**
     * 
     * @return the type of the object.
     */
    GameObjectType getGameObjectType();

    /**
     * 
     * @return the width of the obstacle.
     */
    double getWidth();

    /**
     * 
     * @return the base type of the obstacle.
     */
    GameObjectBaseType getBaseType();

}
