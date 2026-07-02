package aoc.model.entity;

import aoc.utilities.Vector;

/**
 * This interface describes a generic entity of the game.
 * This entity has a position in the game world, and moves in it with a certain speed.
 * It can be destroyed, and its position in the game world can be updated.
 */
public interface EntityInterface {

    /**
     * Returns the current position of the entity in the world,
     * as a Vector, which encapsulates its coordinates.
     * 
     * @return a Vector with his coordinates in the world
     */
    Vector getPosition();

    /**
     * Returns true if the entity is still alive.
     * 
     * @return a boolean representing the status of the entity (dead or alive).
     */
    boolean isAlive();

    /**
     * Return the type of the entity as a String.
     * 
     * @return a String with the type of the entity.
     */
    String getName();
    
    /**
     * Returns the current speed of the entity in the world,
     * as a Vector, which encapsulates its speed on each coordinate.
     * 
     * @return a Vector with the speed of the entity.
     */
    Vector getSpeed();

    /**
     * Sets the speed of the entity with a given Vector.
     * 
     * @param speed
     *                  The Vector representing the new speed.
     */
    void setSpeed(Vector speed);

    /**
     * Updates the position of the entity in the world.
     */
    void update();

}
