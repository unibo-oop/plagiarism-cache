package it.unibo.isaccoop.model.common;

import java.util.Random;

/**
 * Enum that models the concept of direction.
 */
public enum Direction {
    /**
     * The direction UP.
     */
    UP(0.0, -1.0),
    /**
     * The direction DOWN.
     */
    DOWN(0.0, 1.0),
    /**
     * The direction RIGHT.
     */
    RIGHT(1.0, 0.0),
    /**
     * The direction LEFT.
     */
    LEFT(-1.0, 0.0);

    private static final Random RND = new Random();
    private final double x;
    private final double y;

    /**
     * Direction constructor.
     *
     * @param x direction vector component
     * @param y direction vector component
     * */
    Direction(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get x of direction vector.
     * @return the x of this direction
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get y of direction vector.
     * @return the y of this direction
     */
    public double getY() {
        return this.y;
    }

    /**
     * Get random direction.
     * @return a random direction
     */
    public static Direction getRandomDir() {
        return Direction.values()[RND.nextInt(Direction.values().length)];
    }
}
