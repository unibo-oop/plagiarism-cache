package it.unibo.javacrush.common;

/**
 * Represents the possible gravity directions within the game.
 * Each direction defines its movement vector (dx, dy) and provides
 * logic to determine the entry point for new game elements.
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
     * Gets the x-component of the direction vector.
     *
     * @return the x-component of the direction vector
     */
    public int getDx() {
        return dx;
    }

    /**
     * Gets the y-component of the direction vector.
     *
     * @return the y-component of the direction vector
     */
    public int getDy() {
        return dy;
    }

    /**
     * Calculates the fixed coordinate (row or column) of the edge where 
     * new pieces must enter.
     * 
     * @param rows the total number of rows in the board
     * @param cols the total number of columns in the board
     * @return the index (0 or max-1) of the entry row/column
     */
    public int getEntryPoint(final int rows, final int cols) {
        return switch (this) {
            case DOWN -> 0;
            case UP -> rows - 1;
            case RIGHT -> 0;
            case LEFT -> cols - 1;
        };
    }

    /**
     * @return true if the direction is vertical (UP or DOWN), false if horizontal (LEFT or RIGHT)
     */
    public boolean isVertical() {
        return this == UP || this == DOWN;
    }

}
