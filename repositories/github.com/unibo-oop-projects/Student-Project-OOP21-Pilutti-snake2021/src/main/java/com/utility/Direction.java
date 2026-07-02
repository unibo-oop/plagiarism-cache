package main.java.com.utility;

import java.util.Arrays;
import java.util.List;

/**
 * An enumeration representing all the possible directions that the snake entity is allowed to move.
 *
 */
public enum Direction {

    /**
     * Up.
     */
    UP,

    /**
     * Right.
     */
    RIGHT,

    /**
     * Down.
     */
    DOWN,

    /**
     * Left.
     */
    LEFT;

    /**
     * 
     * @return a list representing all the possible directions of this {@link Direction}.
     */
    public static List<Direction> getDirectionList() {
        return Arrays.asList(Direction.values());
    }

    /**
     * 
     * @param currentDir the current direction in which the snake is moving.
     * @param newDir the new direction in which the snake would like to move.
     * @return whether the new direction is the opposite of the current direction.
     */
    public static boolean isOppositeDirection(final Direction currentDir, final Direction newDir) {
        return currentDir.equals(UP) && newDir.equals(DOWN)
                || currentDir.equals(DOWN) && newDir.equals(UP)
                || currentDir.equals(LEFT) && newDir.equals(RIGHT)
                || currentDir.equals(RIGHT) && newDir.equals(LEFT);
    }

    public String toString() {
        switch (this) {
        case UP:
            return "up";
        case DOWN:
            return "down";
        case LEFT:
            return "left";
        case RIGHT:
            return "right";
        default:
            return "";
        }
    }
}
