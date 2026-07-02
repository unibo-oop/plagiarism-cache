package javawulf.model;

/**
 * Direction represents the possibile vectors the elements of the
 * game can move towards.
 */
public enum Direction {
    /**
     * The vector representing moving up.
     */
    UP(0.0, -1.0),
    /**
     * The vector representing moving down.
     */
    DOWN(0.0, 1.0),
    /**
     * The vector representing moving left.
     */
    LEFT(-1.0, 0.0),
    /**
     * The vector representing moving right.
     */
    RIGHT(1.0, 0.0),
    /**
     * The vector representing moving up-left.
     */
    UP_LEFT(-Math.cos(Math.PI / 4), -Math.sin(Math.PI / 4)),
    /**
     * The vector representing moving up-right.
     */
    UP_RIGHT(Math.cos(Math.PI / 4), -Math.sin(Math.PI / 4)),
    /**
     * The vector representing moving down-left.
     */
    DOWN_LEFT(-Math.cos(Math.PI / 4), Math.sin(Math.PI / 4)),
    /**
     * The vector representing moving down-right.
     */
    DOWN_RIGHT(Math.cos(Math.PI / 4), Math.sin(Math.PI / 4));

    private final double x;
    private final double y;

    Direction(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The coordinate on the x-axis of the vector
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return The coordinate on the y-axis of the vector
     */
    public double getY() {
        return this.y;
    }
}
