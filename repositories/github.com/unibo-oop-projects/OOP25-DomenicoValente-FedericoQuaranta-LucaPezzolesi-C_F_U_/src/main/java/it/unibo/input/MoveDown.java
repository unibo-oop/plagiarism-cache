package it.unibo.input;

import it.unibo.api.Vector2D;

/**
 * Command that handles the player's downward movement.
 */
public class MoveDown implements Command {

    /**
     * Creates a MoveDown command.
     */
    public MoveDown() {
        // default constructor
    }

    @Override
    public Vector2D execute() {
        
        return new Vector2D(0, 1);
    }

}
