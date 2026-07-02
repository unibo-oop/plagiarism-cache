package controller;

import utils.Direction;

/**
 * Control the entities movement.
 */
public interface MovementObserver {

    /**
     * Move the Stuntman.
     * 
     * @param direction The direction in which he moves
     */
    void moveStuntman(Direction direction);

    /**
     * Move the vase.
     */
    void moveVase();

    /**
     * Move the hawk.
     */
    void moveHawk();

}
