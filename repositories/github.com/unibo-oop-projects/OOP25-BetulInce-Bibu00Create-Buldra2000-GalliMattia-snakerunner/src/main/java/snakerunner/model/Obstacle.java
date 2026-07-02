package snakerunner.model;

import java.util.HashSet;
import java.util.Set;

import snakerunner.commons.Point2D;

/**
 * The Obstacle class represents an obstacle in the Snake Runner game.
 */
public class Obstacle extends Entity {
    /* Dimensions in grid */
    private final int width;
    private final int height;

    /**
     * Obstacle's inital positions and dimensions.
     * 
     * @param x the x coordinate of the obstacle.
     * @param y the y coordinate of the obstacle.
     * @param width the width of the obstacle in grid units.
     * @param height the height of the obstacle in grid units.
     */
    public Obstacle(final int x, final int y, final int width, final int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }
 
    /**
     * Getter Width.
     * 
     * @return the width of the obstacle in grid units.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter Height.
     * 
     * @return the height of the obstacle in grid units.
     */
    public int getHeight() {
        return height;
    }

    /**
     * We check if targetX is between x and x + width and targetY is between y and y + height (the area).
     * 
     * @param targetX the x coordinate of the target point.
     * @param targetY the y coordinate of the target point.
     * @return true if the target point is within the obstacle's boundaries, false otherwise.
     */
    public boolean isHit(final int targetX, final int targetY) {
     return targetX >= getX()
            && targetX < getX() + this.width 
            /* Checking if x is between the beginning and the end of the obstacle */
            && targetY >= getY()
            && targetY < getY() + this.height; /* Checking if the y is between the beginning and the end of the obstacle */
    }

    /**
     * We use this method to get the occupied positions by transforming them into a set.
     * 
     * @return a set of occupied positions.
     */
    public Set<Point2D<Integer, Integer>> getOccupiedPositions() {
        final Set<Point2D<Integer, Integer>> positions = new HashSet<>();
        for (int i = 0; i < width; i++) { /* iterates through width and then height*/
            for (int j = 0; j < height; j++) {
                /* adds a new "point" to the set for every cell that's occupied */
                positions.add(new Point2D<>(getX() + i, getY() + j));
            }
        }
        return positions;
    }
}
