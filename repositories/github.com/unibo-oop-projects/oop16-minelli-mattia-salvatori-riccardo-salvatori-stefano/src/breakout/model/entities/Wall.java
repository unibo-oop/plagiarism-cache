package breakout.model.entities;

import breakout.model.physics.MyBoundingBox;
import breakout.model.physics.Vector2D;
import javafx.geometry.Point2D;

/**
 * A class that describes an uncrossable and undestroyable object with no speed.
 *
 */
public class Wall extends AbstractGameObject {

    /**
     * Positions for a wall.
     */
    public enum WallPos {
        /**
         * Upper wall.
         */
        UP,
        /**
         * Wall on left side.
         */
        LEFT,
        /**
         *  Wall on right side.
         */
        RIGHT;
    }

    private final WallPos pos;

    /**
     * Constructor to create a game object of a Wall. The velocity of this
     * object is always 0.
     * 
     * @param pos
     *            position
     * @param x
     *            the x coordinate of the upperleft corner of it's rectangular
     *            bounds
     * @param y
     *            the y coordinate of the upperleft corner of it's rectangular
     *            bounds
     * @param width
     *            the width of the rectangular box that contains the wall
     * @param height
     *            the height of the rectangular box that contains the wall
     */
    public Wall(final WallPos pos, final double x, final double y, final double width, final double height) {
        super(new Point2D(x, y), Vector2D.ZERO, new MyBoundingBox(x, y, width, height));
        this.pos = pos;
    }

    @Override
    public void setVelocity(final double x, final double y) {
    }

    @Override
    public void setSpeed(final double s) {
    }

    /**
     * @return the position on the screen of the wall.
     */
    public WallPos getWorldPosition() {
        return this.pos;
    }

    @Override
    public String toString() {
        return super.toString() + this.pos;
    }

}
