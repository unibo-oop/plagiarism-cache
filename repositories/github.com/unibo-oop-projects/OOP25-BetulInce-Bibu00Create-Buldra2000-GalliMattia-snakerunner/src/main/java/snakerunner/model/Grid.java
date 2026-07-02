package snakerunner.model;

import snakerunner.commons.Point2D;

/**
 * Interface representing the grid of the game.
 */
public interface Grid {
    /**
     * Checks if the given position is inside the grid boundaries.
     * 
     * @param position to check.
     * @return true if the position is inside the grid, false otherwise.
     */
    boolean isInsideGrid(Point2D<Integer, Integer> position);

    /**
     * Get thew width of the grid.
     * 
     * @return the width of the grid.
     */
    int getWidth();

    /**
     * Get thew height of the grid.
     * 
     * @return the height of the grid.
     */
    int getHeight();
}

