package models;

import java.util.Map;
import java.util.Optional;

/**
 * CollisionImpl is a very simple class that implements CollisionStrategy and its contracts.
 * It checks whether a Player and an Enemy or two Enemies are trying to reach the same position
 * and it blocks the movement for one of them (the second one that tried to reach that same
 * destination) in order to avoid errors. It also blocks Enemies and Players from ever going out of boundaries
 * of the game map
 */
public class CollisionImpl implements CollisionStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkCollisions(final Map<Point2D, Optional<Entity>> board, final Point2D newPos, final int width, final int height) {
        if (newPos.getX() >= width || newPos.getX() < 0 || newPos.getY() >= height || newPos.getY() < 0) {
            return true;
        }
        return board.get(newPos).isPresent() && (board.get(newPos).get() instanceof Enemy || board.get(newPos).get() instanceof Player);
    }

}
