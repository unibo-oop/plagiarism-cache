package model.collision;

import model.ball.Ball;
import model.block.Block;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface to checking the collisions
 */
public interface Collision {
    /**
     * @param ball the ball that i need to check
     * @return CollisionDetectedImpl with different cases:
     * <ul>
     *     <li>Empty if there isn't any collision</li>
     *     <li>If the collision is present there are 4 types of return CollisionDetectedImpl</li>
     *     <ul>
     *         <li>BONUS, list<block> contains the blocks and Vector2D and Point 2D are empty.</li>
     *         <li>BLOCKS, list<block> contains the blocks, Vector2D that contain the new direction, Point 2D that contain the center of the ball where was the collision.</li>
     *         <li>BOTTOM BORDER, list<block> empty, Vector2D empty, Point 2D that contain the center of the ball where was the collision.</li>
     *         <li>OTHER BORDERS, list<block> empty, Vector2D that contain the new direction, Point 2D that contain the center of the ball where was the collision.</li>
     *     </ul>
     * </ul>
     */
    Optional<CollisionDetectedImpl> checkCollision(final Ball ball);

    /**
     * Method to Update all internal Blocks
     *
     * @param blocks all the current blocks on the field
     */
    void updateBlocks(final Map<Pair<Integer, Integer>, Block> blocks);

    /**
     * Method to Update all internal Blocks
     *
     * @param blocks all the current blocks on the field
     */
    void updateBlocks(final List<Block> blocks);
}
