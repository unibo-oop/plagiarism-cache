package snakerunner.model.impl;

import snakerunner.commons.Point2D;
import snakerunner.model.Grid;

/**
 * Implementation of the Grid interface representing the grid of the game.
 */
public final class GridImpl implements Grid {
    private final int width;
    private final int height;

    /**
     * Constructs a GridImpl with the specified width and height.
     * 
     * @param width of the grid.
     * @param height of the grid.
     */
    public GridImpl(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if the given position is inside the grid boundaries.
     * 
     * @param position The position to check.
     * @return true if the position is inside the grid, false otherwise.
     */
    @Override
    public boolean isInsideGrid(final Point2D<Integer, Integer> position) {
        final int x = position.getX();
        final int y = position.getY();

        return x >= 0 && x < width && y >= 0 && y < height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
