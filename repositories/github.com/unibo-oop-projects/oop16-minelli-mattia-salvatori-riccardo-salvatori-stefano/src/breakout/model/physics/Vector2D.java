package breakout.model.physics;

import javafx.geometry.Point2D;

/**
 * A utlity class to define the concept of a vector in a standard Cartesian
 * space (x , y).
 *
 */
public final class Vector2D {

    /**
     * A constant to create a null vector.
     */
    public static final Vector2D ZERO = new Vector2D(0, 0);

    /**
     * The two coordinates in a 2d space.
     */
    private Point2D vector;

    /**
     * Private constructor
     * 
     * @param compX
     *            the x component of the vector
     * @param compY
     *            the y component of the vector
     */
    private Vector2D(final double compX, final double compY) {
        this.vector = new Point2D(compX, compY);
    }

    /**
     * Static method to instanciate a vector using magnitude and angle.
     * 
     * @param magnitude
     *            the magnitude of the vector
     * @param angle
     *            the inclination between the vector and the x-axis
     * @return a Vector2D
     */
    public static Vector2D valueOfPolar(final double magnitude, final double angle) {
        if (magnitude == 0.0 && angle == 0.0) {
            return Vector2D.ZERO;
        }
        return new Vector2D(magnitude * Math.cos(Math.toRadians(angle)), magnitude * Math.sin(Math.toRadians(angle)));
    }

    /**
     * Static method to instanciate a vector using his x and y component.
     * 
     * @param compX
     *            the x component.
     * @param compY
     *            the y component.
     * @return the vector
     */
    public static Vector2D valueOfCartesian(final double compX, final double compY) {
        if (compX == 0.0 && compY == 0.0) {
            return Vector2D.ZERO;
        }
        return new Vector2D(compX, compY);
    }

    /**
     * Getter for the x component.
     * 
     * @return the x component of this vector.
     */
    public double getX() {
        return this.vector.getX();
    }

    /**
     * Getter for the Y component.
     * 
     * @return the y component of this vector.
     */
    public double getY() {
        return this.vector.getY();
    }

    /**
     * Getter for the magnitude.
     * 
     * @return the magnitude of this vector
     */
    public double getMagnitude() {
        return this.vector.magnitude();
    }

    /**
     * Set the magnitude of this vector.
     * 
     * @param m
     *            the new magnitude
     */

    public void setMagnitude(final double m) {
        this.vector = new Point2D(m * Math.cos(this.getAngle()), m * Math.sin(this.getAngle()));
    }

    /**
     * Set the angle of the this vector.
     * 
     * @param angle
     *            the new angle (in deegres)
     */

    public void setAngle(final double angle) {
        this.vector = new Point2D(
                this.getMagnitude() * Math.cos(Math.toRadians(angle)),
                this.getMagnitude() * Math.sin(Math.toRadians(angle)));
    }

    /**
     * Getter for the angle.
     * 
     * @return the angle of the vector (in Radians)
     */
    public double getAngle() {
        return Math.atan2(this.getY(), this.getX());
    }

    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")" + " a:" + " " + Math.toDegrees(this.getAngle()) + " mod: "
                + this.getMagnitude();
    }

}
