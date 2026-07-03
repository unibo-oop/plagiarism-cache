package model.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * Represents the cardinal four direction. All the angle associated are computed
 * based on the Math.atan2 method.
 * {@link Math.atan2}
 */
public enum Direction implements Serializable {
    /**
     * The direction right.
     */
    RIGHT(90),

    /**
     * The direction left.
     */
    LEFT(-90),

    /**
     * The direction up.
     */
    UP(0),

    /**
     * The direction down.
     */
    DOWN(180);

    private final double angle;

    Direction(final double angle) {
        this.angle = angle;
    }

    /**
     * Shuffle the values to generate four random directions.
     * @return A List of four random directions.
     */
    public static List<Direction> getRandomDirections() {
        final List<Direction> values = new ArrayList<>(Arrays.asList(values()));
        Collections.shuffle(values);
        return values;
    }

    /**
     * Get the opposite direction of the one provided as parameter.
     * @param d
     *            A direction.
     * @return The opposite direction.
     */
    public static Direction getOpposite(final Direction d) {
        switch (d) {
        case UP:
            return DOWN;
        case DOWN:
            return UP;
        case LEFT:
            return RIGHT;
        default: /* case RIGHT */
            return LEFT;

        }
    }

    /**
     * 
     * @return The angle associated with this direction,
     */
    public double getAngle() {
        return this.angle;
    }

}
