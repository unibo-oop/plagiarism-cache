package it.unibo.isaccoop.model.player;

import it.unibo.isaccoop.model.common.Direction;
import it.unibo.isaccoop.model.common.Point2D;

/**
 * Interface for the player movement.
 * */
public interface PlayerMovement {

    /**
     * Update the player position.
     * @param direction the direction where player is moving
     * */
    void update(Direction direction);

    /**
     * Get the next position in preview.
     * @param direction the direction in which the player moves
     * @return the future position of the player in the direction
     * */
    Point2D getMovePreview(Direction direction);
}
