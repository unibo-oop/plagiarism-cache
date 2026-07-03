package breakout.model.physics;

import javafx.geometry.Point2D;

/**
 * An interface that defines the main proprties for all the objects in the game.
 */

public interface GameObject {

    /**
     * Set the position of the object with his X and Y component.
     * 
     * @param x
     *            the x coordinate.
     * @param y
     *            the y coordinate.
     */

    void setPosition(final double x, final double y);

    /**
     * Getter for the position.
     * 
     * @return Point2D.
     */
    Point2D getPosition();

    /**
     * Set the velocity of the object.
     * 
     * @param x
     *            the x component of the vector.
     * @param y
     *            the y component of the vector.
     */
    void setVelocity(final double x, final double y);

    /**
     * Getter for the velocity.
     * 
     * @return A Vector2D rappresenting the velocity vector.
     */
    Vector2D getVelocity();

    /**
     * Getter for the bounding box.
     * 
     * @return BoundingBox.
     */
    MyBoundingBox getBounds();

    /**
     * Set a bounding box for this object.
     * 
     * @param width
     *            the width of the rectangula bounding box.
     * @param height
     *            the height of the rectangula bounding box.
     * @throws IllegalArgumentException
     *             if width or height are less then 0.
     */
    void setBounds(final double width, final double height) throws IllegalArgumentException;

    /**
     * Update the object as a function of time.
     * 
     * @param time
     *            time elapsed from a previous update.
     */
    void update(final double time);

    /**
     * Check if an object collides with the given object.
     * 
     * @param object
     *            the collied to check
     * @return true if the two objects collide.<br/>
     *         false otherwise.
     */
    boolean collidedWith(final GameObject object);

}
