package bzzbomber.model.utilities;

import java.awt.Point;

/**
 * Enumeration for Direction.
 */
public enum Direction {

    /**
     * Up movement.
     */
    UP(new Point(0, -1)),

    /**
     * Down movement.
     */
    DOWN(new Point(0, 1)),

    /**
     * Left movement.
     */
    LEFT(new Point(-1, 0)),

    /**
     * Right movement.
     */
    RIGHT(new Point(1, 0));

    static {
        UP.opposite = DOWN;
        DOWN.opposite = UP;
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
    }

    private Point translation;
    private Direction opposite;

    Direction(final Point pos) {
        this.translation = pos;
    }

    /**
     * Getter for point associated to the Direction.
     * 
     * @return the relative Point
     */
    public Point getTranslation() {
        return this.translation;
    }

    /**
     * Getter for the opposite direction.
     * 
     * @return the opposite direction
     */
    public Direction getOppositeDirection() {
        return this.opposite;
    }
}
