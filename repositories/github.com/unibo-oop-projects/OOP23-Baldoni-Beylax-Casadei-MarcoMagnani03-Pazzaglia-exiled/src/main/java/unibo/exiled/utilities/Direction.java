package unibo.exiled.utilities;

/**
 * The direction used to move to a different position.
 */
public enum Direction {
    /**
     * Represents the north direction.
     */
    NORTH(0, -1),

    /**
     * Represents the south direction.
     */
    SOUTH(0, 1),

    /**
     * Represents the west direction.
     */
    WEST(-1, 0),

    /**
     * Represents the east direction.
     */
    EAST(1, 0);

    private final Position position;

    /**
     * Constructs a Direction with the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    Direction(final int x, final int y) {
        position = new Position(x, y);
    }

    /**
     * Gets the position associated with the direction.
     *
     * @return The position associated with the direction.
     */
    public Position getPosition() {
        return this.position;
    }
}
