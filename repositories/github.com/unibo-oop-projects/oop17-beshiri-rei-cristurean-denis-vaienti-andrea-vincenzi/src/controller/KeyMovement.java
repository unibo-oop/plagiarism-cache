package controller;

import utility.Command;

/**
 * For add movement and remove movement.
 */
public interface KeyMovement {

    /**
     * Add a movement.
     * @param d The direction of movement.
     */
    void addMovement(Command d);

    /**
     * Remove a movement.
     * @param d The direction of movement.
     */
    void removeMovement(Command d);
}
