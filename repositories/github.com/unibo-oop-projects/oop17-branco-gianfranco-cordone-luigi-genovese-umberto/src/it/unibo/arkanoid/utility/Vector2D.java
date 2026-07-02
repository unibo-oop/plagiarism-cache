package it.unibo.arkanoid.utility;

/**
 * Representation of vector in the x and y coordinate.
 *
 */
public final class Vector2D {

    /**
     * constant used to do equals method.
     */
    public static final double DELTA = 0.001;
    /**
     * vector with x = 0 and y = 1.
     */
    public static final Vector2D UP = new Vector2D(0, 1);
    /**
     * vector with x = 0 and y = -1.
     */
    public static final Vector2D DOWN = new Vector2D(0, -1);
    /**
     * vector with x = 1 and y = 0.
     */
    public static final Vector2D RIGHT = new Vector2D(1, 0);
    /**
     * vector with x = -1 and y = 0.
     */
    public static final Vector2D LEFT = new Vector2D(-1, 0);

    private final double x;
    private final double y;

    /**
     * Constructor for Vector with x and y coordinate.
     * 
     * @param x
     *            coordinate.
     * @param y
     *            coordinate.
     */

    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return x component
     */
    public double getX() {
        return x;
    }

    /**
     * 
     * @return y component
     */
    public double getY() {
        return y;
    }

    /**
     * Sums the given Vector to this Vector.
     * 
     * @param vector
     *            the Vector to be summed.
     * @return new Vector with its new value.
     */
    public Vector2D sumVector(final Vector2D vector) {
        return new Vector2D(this.x + vector.getX(), this.y + vector.getY());
    }

    /**
     * Multiplies this Vector by the given value.
     * 
     * @param value
     *            value to multiply the Vector with.
     * @return new Vector with its new values.
     */
    public Vector2D multiply(final double value) {
        return new Vector2D(this.x * value, this.y * value);
    }

    /**
     * Rotates this vector by the angle theta.
     * 
     * @param theta
     *            the rotation angle
     * @return a new rotated vector.
     */
    public Vector2D rotate(final double theta) {
        final double sin = Math.sin(theta);
        final double cos = Math.cos(theta);
        return new Vector2D(this.x * cos - this.y * sin, this.x * sin + this.y * cos);
    }

    /**
     * it modifies this vector.
     * 
     * @return The inverse of the vector passed.
     */
    public Vector2D inverse() {
        return new Vector2D(-this.x, -this.y);
    }

    /**
     * returns the length of vector.
     * 
     * @return length value.
     */
    public double getLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Vector2D other = (Vector2D) obj;

        if (Math.abs(this.x - other.getX()) > DELTA) {
            return false;
        } else if (Math.abs(this.y - other.getY()) > DELTA) {
            return false;
        }
        return true;
    }

}
