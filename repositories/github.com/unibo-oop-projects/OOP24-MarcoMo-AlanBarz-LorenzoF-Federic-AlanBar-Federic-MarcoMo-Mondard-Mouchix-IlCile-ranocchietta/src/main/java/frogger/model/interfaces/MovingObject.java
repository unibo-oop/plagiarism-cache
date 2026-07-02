package frogger.model.interfaces;

import frogger.common.Direction;
import frogger.common.Position;

/**
 * Represents all the GameObject that have a direction and can move by their self withe the method move().
 */
public interface MovingObject extends GameObject {
    /**
     * set the direction from @class frogger.common.Direction in whitch the Moving Object will move.
     * @param direction the direction choosed.
     */
    void setDirection(Direction direction);

    /** 
     * @return the direction settled to the object.
     */
    Direction getDirection();

    /**
     * Set the speed of the moving object.
     * The speed is a float value that determines how fast the object moves.
     *
     * @param speed the speed to set for the object.
     */
    void setSpeed(float speed);

    /**
     * @return the speed settled to the object.
     */
    float getSpeed();

    /**
     * Templete method to move the object.
     * It calls the step method, which is implemented in the MovingObjectImpl class.
     * The step method is responsible for updating the position of the object based on its direction and speed.
     * It also checks for boundary conditions to ensure the object does not move out of the game board.
     */
    void move();

    /**
     * Returns a Position object representing the directional vector for the current direction.
     * For example, LEFT returns (-1, 0), UP returns (0, 1), etc.
     *
     * @return the direction vector as a Position.
     */
    Position getDirectionValue();
}
