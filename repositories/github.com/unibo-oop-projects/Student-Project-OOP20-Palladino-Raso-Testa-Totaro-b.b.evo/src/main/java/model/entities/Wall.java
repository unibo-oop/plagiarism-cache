package model.entities;

import model.utilities.Position;

public class Wall {

    private final double width;
    private final double height;

    public Wall(final double worldWidth, final double worldHeight) {
        this.width = worldWidth;
        this.height = worldHeight;
    }

    /**
     * getter for the wall width.
     * @return width of the wall
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter for the wall height.
     * @return height of the wall
     */
    public double getHeight() {
        return height;
    }


    /**
     * getter for upper left corner of the game board.
     * @return position of the upper left corner
     */
    public Position getUpperLeftCorner() {
        return new Position(0, 0);
    }
    /**
     * getter for bottom right corner of the game board.
     * @return position of the bottom right corner
     */
    public Position getRightBottomCorner() {
        return new Position(this.width, this.height);
    }
}
