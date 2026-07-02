package snakerunner.model;

/**
 * Enum representing the possible directions of movement for the snake.
 */
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Checks if the given direction is opposite to the current direction.
     * 
     * @param other the other direction to compare with
     * @return true if the other direction is opposite, false otherwise
     */
    public boolean isOpposite(final Direction other) {
        return this.dx == -other.dx && this.dy == -other.dy;
    }
}
