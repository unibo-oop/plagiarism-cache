package it.unibo.pacman.model.utilities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * An enum that represents the {@link Direction} of the {@link Entity} in this game.
 */
public enum Direction {
    /**
     * Used to set the direction to LEFT.
     */
    LEFT,
    /**
     * Used to set the direction to RIGHT.
     */
    RIGHT,
    /**
     * Used to set the direction to UP.
     */
    UP,
    /**
     * Used to set the direction to DOWN.
     */
    DOWN;
    private static final Direction[] VALUES = values();
    /**
     * Used to know all the possible direction of entity.
     * 
     * @return the list of this {@link Direction}
     */
    public static List<Direction> getDirectionList() {
        return Arrays.asList(Direction.values());
    }
    /**
     * Used to get a random direction.
     * 
     * @return a random {@link Direction}
     */
    public static Direction getRandomDirection() {
        return VALUES[new Random().nextInt(VALUES.length)];
    }
    /**
     * @return a {@link String} for each direction.
     */
    @Override
    public String toString() {
        switch (this) {
        case UP:
            return "Up";
        case DOWN:
            return "Down";
        case LEFT:
            return "Left";
        case RIGHT:
            return "Right";
        default:
            return "";
        }
    }
    /**
     * Get the opposite direction of dir.
     * @param dir the initial direction. 
     * @return the opposite direction of dir.
     */
    public static Direction getOppositeDirection(final Direction dir) {
        switch (dir) {
        case UP:
            return DOWN;
        case DOWN:
            return UP;
        case LEFT:
            return RIGHT;
        case RIGHT:
            return LEFT;
        default:
            return null;
        }
    }

}
