package it.unibo.input;

import it.unibo.api.Vector2D;

/**
 * Command that handles the player's leftward movement.
 */
public class MoveLeft implements Command {

    /**
     * Creates a MoveLeft command.
     */
    public MoveLeft() {
        // default constructor
    }

    @Override
    public Vector2D execute() {
        
        return new Vector2D(-1, 0);
    }

}
