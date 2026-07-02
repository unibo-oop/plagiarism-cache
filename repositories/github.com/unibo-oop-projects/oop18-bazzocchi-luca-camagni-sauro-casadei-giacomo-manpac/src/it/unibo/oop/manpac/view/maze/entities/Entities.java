package it.unibo.oop.manpac.view.maze.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import it.unibo.oop.manpac.model.Directions;

/**
 * Interface for the implementation of pacman and phantoms.
 */

public interface Entities {

    /**
     * Set the position of the Entity.
     * 
     * @param position The position to set
     */
    void setPosition(Vector2 position);

    /**
     * Return the current position.
     * 
     * @return the position
     */
    Vector2 getPosition();

    /**
     * Set the Entity's position at the spawn point.
     */
    void setPositionToSpawnPoint();

    /**
     * Set the direction of the Entity.
     * 
     * @param direction The direction
     */
    void setDirection(Directions direction);

    /**
     * Get the direction of the Entity.
     * 
     * @return The direction
     */
    Directions getDirection();

    /**
     * Get the body definition of the Entity.
     * 
     * @return The body
     */
    Body getBody();

    /**
     * Get the Entity's vector of movement.
     * 
     * @return The vector of movement
     */
    Vector2 getMovement();
}
