package it.unibo.input;

import it.unibo.api.Vector2D;

/**
 * Command that intentionally does nothing and stops the player
 */
public class StopMovement implements Command {

    /**
     * Creates a StopMovement command.
     */
    public StopMovement() {
        // default constructor
    }

    @Override
    public Vector2D execute() {
        // Intentionally does nothing: player is stopped
        return new Vector2D(0, 0);
    }

}
