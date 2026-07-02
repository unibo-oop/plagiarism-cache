package models;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * RandomAiEnemy is a class that implements AiEnemy and its contracts.
 * This class implements AiEnemy for a random movements of enemy.
 */
public class RandomAiEnemy implements AiEnemy {

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D move(final Map<Point2D, Optional<Entity>> board, final Point2D playerPosition, final Point2D position) {
        Random r = new Random();
        int randomSelect = r.nextInt(4);
        Point2D newPosition = position;

        switch (randomSelect) {
        case 0:
            newPosition = new Point2D(position.getX() + MOVEMENT.LEFT.movement.getX(), position.getY() + MOVEMENT.LEFT.movement.getY());
            break;
        case 1:
            newPosition = new Point2D(position.getX() + MOVEMENT.RIGHT.movement.getX(), position.getY() + MOVEMENT.RIGHT.movement.getY());
            break;
        case 2:
            newPosition = new Point2D(position.getX() + MOVEMENT.UP.movement.getX(), position.getY() + MOVEMENT.UP.movement.getY());
            break;
        case 3:
            newPosition = new Point2D(position.getX() + MOVEMENT.DOWN.movement.getX(), position.getY() + MOVEMENT.DOWN.movement.getY());
            break;
        default:
            break;
        }
        return newPosition;
    }
}
