package main.java.com.model;

import java.util.List;

import main.java.com.utility.Direction;
import main.java.com.utility.Position;

/**
 * The snake entity, which is able to move around the map and eat the eatable
 * entities.
 *
 */
public interface SnakeEntity extends GameEntity {

    /**
     * 
     * @return the current direction in which the snake is moving.
     */
    Direction getDirection();

    /**
     * Set the new moving direction.
     * 
     * @param dir the new direction
     */
    void setDirection(Direction dir);

    /**
     * Resets the moving direction to the starting one, which is UP.
     */
    void resetDirection();

    /**
     * Computes the next position in which the snake would move (considering only
     * the snake's head).
     * 
     * @return the next position.
     */
    Position nextPosition();

    /**
     * 
     * @return a set of Position of all of the snake's body.
     */
    List<Position> getBodyPosition();

    /**
     * Sets the position of the snake's body.
     * 
     * @param body as a list of positions
     */
    void setBodyPosition(List<Position> body);

    /**
     * Increases the length of the snake by one unit.
     */
    void increaseLength();

    /**
     * Sets the length of the snake.
     * 
     * @param l the length
     */
    void setLength(int l);

    /**
     * Move the snake by one step and update all of its positions.
     */
    void move();

    /**
     * Sets boolean field dead to true, stopping snakes' movement.
     */
    void die();
}
