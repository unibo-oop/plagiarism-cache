package snakerunner.model;

import snakerunner.commons.Point2D;

/**
 * Doors can be open when the user collects the key.
 */
public class Door extends Obstacle {

    private boolean open; /* Checking if the door is open */

    /**
     * Constructor for the Door class, which initializes the door's position and sets it to closed by default.
     * 
     * @param x coordinate x of the door's position in the grid.
     * @param y coordinate y of the door's position in the grid.
     */
    public Door(final int x, final int y) {
        super(x, y, 1, 1); /* Default size is 1x1 */
        this.open = false; /* At the beginning the door is closed */

    }

    /**
     * checking if the door is open or not.
     * 
     * @return whether the door is open or not.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * setting the door to open or closed.
     * 
     * @param open the new state of the door, true for open and false for closed.
     */
    public void setOpen(final boolean open) {
        this.open = open;
    }

    /**
     * Getting door's positions.
     * 
     * @return a Point2D representing the (x, y) coordinates of the door.
     */
    public Point2D<Integer, Integer> getPosition() {
    return new Point2D<>(getX(), getY());
    }

}
