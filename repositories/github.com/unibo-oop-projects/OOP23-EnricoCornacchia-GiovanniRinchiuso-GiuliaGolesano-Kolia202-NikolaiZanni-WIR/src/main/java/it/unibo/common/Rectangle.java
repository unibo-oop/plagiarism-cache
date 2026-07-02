package it.unibo.common;

/**
 * Represents a rectangle in a 2D coordinate system.
 */
public class Rectangle {
    private double x;
    private double y;
    private final double width;
    private final double height;

    /**
     * Constructs a new Rectangle with the specified position and dimensions.
     *
     * @param x      the x-coordinate of the rectangle
     * @param y      the y-coordinate of the rectangle
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the x-coordinate of the rectangle.
     *
     * @return the x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the rectangle.
     *
     * @param x the new x-coordinate.
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the rectangle.
     *
     * @return the y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the rectangle.
     *
     * @param y the new y-coordinate.
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Checks if this rectangle intersects with another rectangle.
     *
     * @param other the other rectangle to check for intersection
     * @return true if the rectangles intersect, false otherwise
     */
    public boolean intersects(final Rectangle other) {
        return this.getX() <= other.getX() + other.getWidth() 
               && this.getX() + this.getWidth() >= other.getX() 
               && this.getY() <= other.getY() + other.getHeight() 
               && this.getY() + this.getHeight() >= other.getY();
    }
}
