package it.unibo.michelito.util;

/**
 * Represents cardinal {@link Direction} along with a null {@link Direction}.
 */
public enum Direction {
    /**
     * Up {@link Direction}.
     */
    UP(new Position(0, -1)),

    /**
     * Down {@link Direction}.
     */
    DOWN(new Position(0, 1)),

    /**
     * Left {@link Direction}.
     */
    LEFT(new Position(-1, 0)),

    /**
     * Right {@link Direction}.
     */
    RIGHT(new Position(1, 0)),

    /**
     * UP-Left {@link Direction}.
     */
    UP_LEFT(new Position(-Math.sqrt(0.5), -Math.sqrt(0.5))),

    /**
     * Up-Right {@link Direction}.
     */
    UP_RIGHT(new Position(Math.sqrt(0.5), -Math.sqrt(0.5))),

    /**
     * Down-Left {@link Direction}.
     */
    DOWN_LEFT(new Position(-Math.sqrt(0.5), Math.sqrt(0.5))),

    /**
     * Down-Right {@link Direction}.
     */
    DOWN_RIGHT(new Position(Math.sqrt(0.5), Math.sqrt(0.5))),

    /**
     * A {@link Direction} to represent standing still.
     */
    NONE(new Position(0, 0));

    private final Position position;

    Direction(final Position position) {
        this.position = position;
    }

    /**
     * Checks if the {@link Direction} is diagonal.
     * @return true if the {@link Direction} is diagonal, false otherwise
     */
    public boolean isDiagonal() {
        return this.equals(DOWN_LEFT) || this.equals(DOWN_RIGHT) || this.equals(UP_LEFT) || this.equals(UP_RIGHT);
    }

    /**
     * Transforms the {@link Direction} into a {@link Position}.
     * @return the {@link Position} representing the {@link Direction}
     */
    public Position toPosition() {
        return this.position;
    }
}
