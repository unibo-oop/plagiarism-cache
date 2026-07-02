package it.unibo.input;

import it.unibo.api.Vector2D;

/**
 * Command that handles the player's upward movement.
 */ 
public class MoveUp implements Command {

    /**
     * Creates a MoveUp command.
     */
    public MoveUp() {
        // default constructor
    }

    @Override
    public Vector2D execute() {
        
        return new Vector2D(0, -1);
    }

}
