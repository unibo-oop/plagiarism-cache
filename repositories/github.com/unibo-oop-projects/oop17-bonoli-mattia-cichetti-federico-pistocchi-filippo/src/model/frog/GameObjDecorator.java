package model.frog;

import model.obstacle.GameObject;
import model.obstacle.GameObjectBaseType;
import model.obstacle.GameObjectImpl.GameObjectType;

/**
* Decorator to add functionality required for the frog to a GameObject.
*/
public abstract class GameObjDecorator implements GameObject {

    private final GameObject gameObject;
    private int occupiedLane;
    private double speed;

    /**
    * Wrapper for the GameObject, sets own fields while delegating common fields to the GameObject interface.
    * @param occupiedLane index of the lane the object is in, 0 being the top most one
    * @param speed speed at which the object will move once the updatePosition is called, used while the frog is on the logs
    * @param center is the center of the object
    * @param gameObject is the gameObject to be decorated
    */
    public GameObjDecorator(final int occupiedLane, final double speed, final double center, final GameObject gameObject) {
        this.gameObject = gameObject;
        this.speed = speed;
        this.occupiedLane = occupiedLane;
        gameObject.setCenter(center);
    }

    /**
     *@return the center of the object
     */
    public double getCenter() {
        return gameObject.getCenter();
    }

    /**
    *@param newCenter the center to be set
    */
    public void setCenter(final double newCenter) {
        gameObject.setCenter(newCenter);

    }

    /**
    *@return the ObjectType
    */
    public GameObjectType getGameObjectType() {
        return gameObject.getGameObjectType();
    }

    /**
    *@return the width of the object
    */
    public double getWidth() {
        return gameObject.getWidth();
    }

    /**
    *@return the base type of the object 
    */
    public GameObjectBaseType getBaseType() {
        return gameObject.getBaseType();
    }

    /**
    *@return the lane the object is in
    */
    public int getOccupiedLane() {
        return occupiedLane;
    }

    /**
    *@param occupiedLane sets the lane the object is in
    */
    public void setOccupiedLane(final int occupiedLane) {
        this.occupiedLane = occupiedLane;
    }

    /**
    *@return the speed the object will move every time updatePosition is called
    */
    public double getSpeed() {
        return speed;
    }

    /**
    *@param speed sets the speed the object will move every time updatePosition is called
    */
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
    * Move the object based on the set position.
    */
    public void udpatePosition() {
        gameObject.setCenter(gameObject.getCenter() + this.speed);
    }

}
