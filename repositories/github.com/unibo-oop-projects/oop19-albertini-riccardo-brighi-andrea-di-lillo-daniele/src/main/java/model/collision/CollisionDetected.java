package model.collision;

import model.block.Block;
import element.Point2D;
import element.Vector2D;
import java.util.List;
import java.util.Optional;

/**
 * This is the interface that allows me to create a class
 * that allows me to return multiple parameter from one method
 */
public interface CollisionDetected {

    /**
     *
     * @return an Optional immutableList of blocks
     */
    Optional<List<Block>> getBlocks();

    /**
     *
     * @return an Optional for new direction
     */
    Optional<Vector2D> getNewDirection();

    /**
     *
     * @return an Optional for new center position
     */
    Optional<Point2D> getNewCenterPosition();
}
