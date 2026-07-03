package model.hitbox;

/**
 * 
 * Represents a common interface for all the basic shapes used to check
 * collisions between entities.
 *
 */
public interface Hitbox {

    /**
     * Change the coordinates of this Hitbox.
     * 
     * @param x
     *            The new coordinate x.
     * @param y
     *            The new coordinate y.
     */
    void changePosition(double x, double y);

    /**
     * Get the value of the coordinate x.
     * 
     * @return The coordinate x.
     */
    double getX();

    /**
     * Get the value of the coordinate y.
     * 
     * @return The coordinate y.
     */
    double getY();

}