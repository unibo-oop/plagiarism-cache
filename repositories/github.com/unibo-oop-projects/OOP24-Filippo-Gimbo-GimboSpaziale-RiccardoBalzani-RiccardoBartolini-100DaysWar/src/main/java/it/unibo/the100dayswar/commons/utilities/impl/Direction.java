package it.unibo.the100dayswar.commons.utilities.impl;

/**
 * Enum that models all the possible directions.
 * Each direction includes the change in coordinates required to move
 * in that direction on a grid.
 */
public enum Direction {
    /**
     * Up direction.
     * Represents a decrease in the Y-coordinate by 1.
     */
    UP(0, -1),

    /**
     * Down direction.
     * Represents an increase in the Y-coordinate by 1.
     */
    DOWN(0, 1),

    /**
     * Left direction.
     * Represents a decrease in the X-coordinate by 1.
     */
    LEFT(-1, 0),

    /**
     * Right direction.
     * Represents an increase in the X-coordinate by 1.
     */
    RIGHT(1, 0);

    private final int deltaX;
    private final int deltaY;

    /**
     * Constructor for the Direction enum.
     * 
     * @param deltaX the change in the X-coordinate.
     * @param deltaY the change in the Y-coordinate.
     */
    Direction(final int deltaX, final int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Gets the change in the X-coordinate for this direction.
     * 
     * @return the change in the X-coordinate.
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * Gets the change in the Y-coordinate for this direction.
     * 
     * @return the change in the Y-coordinate.
     */
    public int getDeltaY() {
        return deltaY;
    }
}
