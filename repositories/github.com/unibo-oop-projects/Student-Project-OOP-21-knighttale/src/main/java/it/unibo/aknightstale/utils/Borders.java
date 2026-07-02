package it.unibo.aknightstale.utils;

public interface Borders {

    /**
     * The x coordinate of the upper-left corner of this Borders.
     * 
     * @return The x coordinate.
     */
    double getX();

    /**
     * The y coordinate of the upper-left corner of this Borders.
     * 
     * @return The y coordinate.
     */
    double getY();

    /**
     * The width of this Borders.
     * 
     * @return The width.
     */
    double getWidth();

    /**
     * The height of this Borders.
     * 
     * @return The height.
     */
    double getHeight();

    /**
     * Checks if this Borders intersects the area of a specified Borders.
     * 
     * @param b The specified Borders.
     * @return True if this Borders and the area of the specified Borders intersect,
     *         false otherwise.
     */
    boolean intersects(Borders b);

}
