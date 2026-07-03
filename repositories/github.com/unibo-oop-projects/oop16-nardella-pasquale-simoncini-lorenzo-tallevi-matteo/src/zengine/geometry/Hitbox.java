package zengine.geometry;

import zengine.constants.ZengineColor;

/**
 * Hitbox is a class that models a generic shape that is used for collision
 * checking. Each various subclass describes a different shape.
 */
public interface Hitbox {

    /**
     * returns the x coordinate of this hitbox.
     * 
     * @return x coordinate of the hitbox
     */
    double getX();

    /**
     * returns the y coordinate of this hitbox.
     * 
     * @return y coordinate of the hitbox
     */
    double getY();

    /**
     * sets the x coordinate of the hitbox.
     * 
     * @param x
     *            value to assign
     */
    void setX(double x);

    /**
     * sets the y coordinate of the hitbox.
     * 
     * @param y
     *            value to assign
     */
    void setY(double y);

    /**
     * sets the rotation of the hitbox. Keep in mind that not all hitboxes
     * support rotations and this method may do nothing.
     * 
     * @param angle
     *            value to assign
     */
    void setRotation(double angle);

    /**
     * returns the rotation of this hitbox.
     * 
     * @return rotation, in degrees
     */
    double getRotation();

    /**
     * returns true if this hitbox is touching the other hitbox.
     * 
     * @param other
     *            other hitbox to check
     * @return true if the two hitboxes are touching
     */
    default boolean isTouching(Hitbox other) {
        if (other == null) {
            return false;
        } else {
            if (other instanceof HitboxPoint) {
                // if other is a point
                HitboxPoint ot = (HitboxPoint) other;
                return isTouching(ot);
            } else {
                // if other is a circle
                if (other instanceof HitboxCircle) {
                    HitboxCircle ot = (HitboxCircle) other;
                    return isTouching(ot);
                } else {
                    // if other is a rectangle
                    if (other instanceof HitboxRectangle) {
                        HitboxRectangle ot = (HitboxRectangle) other;
                        return isTouching(ot);
                    } else {
                        // if other is a multishape
                        if (other instanceof HitboxMultishape) {
                            HitboxMultishape ot = (HitboxMultishape) other;
                            return isTouching(ot);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
    }

    /**
     * returns true if this hitbox is touching the other hitbox.
     * 
     * @param other
     *            other hitbox to check
     * @return true if the two hitboxes are touching
     */
    boolean isTouching(HitboxPoint other);

    /**
     * returns true if this hitbox is touching the other hitbox.
     * 
     * @param other
     *            other hitbox to check
     * @return true if the two hitboxes are touching
     */
    boolean isTouching(HitboxCircle other);

    /**
     * returns true if this hitbox is touching the other hitbox.
     * 
     * @param other
     *            other hitbox to check
     * @return true if the two hitboxes are touching
     */
    boolean isTouching(HitboxRectangle other);

    /**
     * returns true if this hitbox is touching the other hitbox.
     * 
     * @param other
     *            other hitbox to check
     * @return true if the two hitboxes are touching
     */
    boolean isTouching(HitboxMultishape other);

    /**
     * draws a visual representation of this hitbox.
     * 
     * @param color
     *            color of the hitbox
     * @param filled
     *            wether to draw the whole shape or just the contour
     */
    void drawSelf(ZengineColor color, boolean filled);

}
