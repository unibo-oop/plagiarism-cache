package it.unibo.input;

import it.unibo.api.Vector2D;

/**
 * Command that handles the player's rightward movement.
 */
public class MoveRight implements Command {

    /**
     * Creates a MoveRight command.
     */
    public MoveRight() {
        // default constructor
    }

    @Override
    public Vector2D execute() {
        
        return new Vector2D(1, 0);
    }

}
