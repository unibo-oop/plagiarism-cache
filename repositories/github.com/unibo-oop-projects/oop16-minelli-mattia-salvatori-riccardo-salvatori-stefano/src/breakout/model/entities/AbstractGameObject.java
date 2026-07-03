package breakout.model.entities;

import breakout.model.physics.GameObject;
import breakout.model.physics.MyBoundingBox;
import breakout.model.physics.Vector2D;
import javafx.geometry.Point2D;

/**
 * An abstract class that defines generic game objects with position, velocity
 * and bounds in a 2D game space.
 * 
 */
abstract class AbstractGameObject implements GameObject {

    private Point2D position;
    private Vector2D velocity;
    private MyBoundingBox bounds;

    /**
     * Constructor for an object living in the 2D space The coordinates are
     * associated with a standard Cartesian system (x , y).
     * 
     * @param position
     *            a position specified by Cartesian coordinates (x , y)
     * @param velocity
     *            a vector specified by a vector (x , y)
     * @param bounds
     *            a rectangular bounds that describes the area occupied by the
     *            object
     */
    AbstractGameObject(final Point2D position, final Vector2D velocity, final MyBoundingBox bounds) {
        this.position = position;
        this.velocity = velocity;
        this.bounds = bounds;
    }

    /**
     * {@inheritDoc}.
     */
    public void setPosition(final double x, final double y) {
        this.position = new Point2D(x, y);

    }

    /**
     * {@inheritDoc}.
     */
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}.
     */
    public void setVelocity(final double x, final double y) {
        this.velocity = Vector2D.valueOfCartesian(x, y);
    }

    /**
     * {@inheritDoc}.
     */
    public Vector2D getVelocity() {
        return this.velocity;
    }

    /**
     * Getter for the speed. <br/>
     * <b>note</b>: The speed of a game object is the magnitude of his velocity
     * vector.
     * 
     * @return the magnitude of the velocity vector.
     */
    public double getSpeed() {
        return this.getVelocity().getMagnitude();
    }

    /**
     * Set the speed of this object.<br/>
     * <b>note</b>: The speed of a game object is the magnitude of his velocity
     * vector.
     * 
     * @param s
     *            the new speed.
     */
    public void setSpeed(final double s) {
        this.getVelocity().setMagnitude(s);
    }

    /**
     * {@inheritDoc} <br/>
     * <b>note</b>: The rectangular bounding box is always relative to the
     * object position
     */
    public void setBounds(final double width, final double height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        } else {
            this.bounds = new MyBoundingBox(this.position.getX(), this.position.getY(), width, height);
        }
    }

    /**
     * @return the bounding box of this object.
     */
    public MyBoundingBox getBounds() {
        return this.bounds;
    }

    /**
     * Getter for the width of this object.
     * 
     * @return a double rappresenting the width of this object's bounding box
     */
    public double getWidth() {
        return this.getBounds().getWidth();
    }

    /**
     * Getter for the height of this object.
     * 
     * @return a double rappresenting the height of this object's bounding box
     */
    public double getHeight() {
        return this.getBounds().getHeight();
    }

    /**
     * [{@inheritDoc}.
     */
    public void update(final double time) {
        this.setPosition(this.getPosition().getX() + this.getVelocity().getX() * time,
                this.getPosition().getY() + this.getVelocity().getY() * time);
        this.setBounds(this.getWidth(), this.getHeight());
    }

    /**
     * [{@inheritDoc}.
     */
    public boolean collidedWith(final GameObject object) {
        return this.getBounds().intersects(object.getBounds());
    }

    /**
     * [{@inheritDoc}.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName().toUpperCase() + "\nPos:" + "(" + this.getPosition().getX() + ","
                + this.getPosition().getY() + ")" + "\nVel:" + this.getVelocity() + "\n";

    }

}
