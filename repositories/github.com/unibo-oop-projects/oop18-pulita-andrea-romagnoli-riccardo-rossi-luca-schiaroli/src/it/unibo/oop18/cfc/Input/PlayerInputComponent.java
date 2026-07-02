package it.unibo.oop18.cfc.Input;

import it.unibo.oop18.cfc.Physics.Direction;
import it.unibo.oop18.cfc.Util.Position;

/**
 * Interface to models the player input and manage its commands in the game.
 */
public interface PlayerInputComponent extends InputComponent {

    /**
     * Moves the player in a specific {@link Direction}.
     *
     * @param way to take in the next movement
     */
    void move(Direction way);

    /**
     * Stops player's movement.
     */
    void stop();

    /**
     * Drops the bomb in a specific {@link Position}.
     *
     * @param position where to drop the bomb
     */
    void doAction(Position position);
}
