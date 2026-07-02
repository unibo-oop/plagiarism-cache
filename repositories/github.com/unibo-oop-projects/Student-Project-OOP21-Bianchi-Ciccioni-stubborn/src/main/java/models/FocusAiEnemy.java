package models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * FocusAiEnemy is a class that implements AiEnemy and its contracts.
 * This class implements AiEnemy for a focused movements of enemy.
 */
public class FocusAiEnemy implements AiEnemy {

    private static final int FMIN = 100000;

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D move(final Map<Point2D, Optional<Entity>> board, final Point2D playerPosition, final Point2D position) {
        List<MOVEMENT> movements = Arrays.asList(MOVEMENT.UP, MOVEMENT.DOWN, MOVEMENT.LEFT, MOVEMENT.RIGHT);
        Map<Point2D, Integer> open = getDirections(movements, position);
        Point2D q = new Point2D(-1, -1);
        int g;
        int fMin = FMIN;

        for (var e : open.entrySet()) {
            g = ((e.getKey().getX() - playerPosition.getX()) + (e.getKey().getY() - playerPosition.getY()));
            e.setValue(g);
        }

        for (var e : open.entrySet()) {
            if (e.getValue() < fMin) {
                fMin = e.getValue();
                q = e.getKey();
            }
        }

        return q;
    }

    /**
     * Get every possible directions for enemy based on his current position.
     * 
     * @param movements the possible movements
     * @param position the current position of enemy
     * @return every possible movements that can be done from current position
     */
    private Map<Point2D, Integer> getDirections(final List<MOVEMENT> movements, final Point2D position) {
        Map<Point2D, Integer> directions = new HashMap<>();
        for (MOVEMENT i : movements) {
            directions.put(Point2D.sum(position, i.movement), 0);
        }
        return directions;
    }

}
