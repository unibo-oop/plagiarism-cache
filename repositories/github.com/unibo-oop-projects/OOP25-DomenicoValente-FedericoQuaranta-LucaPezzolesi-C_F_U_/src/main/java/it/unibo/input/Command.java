package it.unibo.input;

import it.unibo.api.Vector2D;

/**
 * a basic command
 */
public interface Command {
    
    /**
     * executes the command using the given model
     * @return the vector direction
     */
    Vector2D execute();

}
