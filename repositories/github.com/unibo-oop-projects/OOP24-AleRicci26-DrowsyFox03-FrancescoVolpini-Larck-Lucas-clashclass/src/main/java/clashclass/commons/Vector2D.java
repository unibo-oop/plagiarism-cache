package clashclass.commons;

/**
 * Represents a Vector in two dimensions (x,y).
 *
 * @param x the x component of the vector
 * @param y the y component of the vector
 */
public record Vector2D(double x, double y) {
    /**
     * Calculates the exact distance between two vectors.
     *
     * @param other the other vector
     *
     * @return the distance between this vector and the other given vector
     */
    public double distance(final Vector2D other) {
        final double deltaX = x - other.x;
        final double deltaY = y - other.y;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Adds two vectors.
     *
     * @param other the other vector
     *
     * @return the resulting vector
     */
    public Vector2D add(final Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    /**
     * Subtracts two vectors.
     *
     * @param other the other vector
     *
     * @return the resulting vector
     */
    public Vector2D subtract(final Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    /**
     * Multiplies a vectors with a scalar.
     *
     * @param scalar the scalar
     *
     * @return the resulting vector
     */
    public Vector2D multiply(final double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    /**
     * Normalizes the vector.
     *
     * @return the normalized vector
     */
    public Vector2D normalized() {
        final var magnitude = Math.sqrt(this.x * this.x + this.y * this.y);
        return new Vector2D(this.x / magnitude, this.y / magnitude);
    }

    /**
     * Creates a vector with zero in both x and y components.
     *
     * @return the vector with (0,0)
     */
    public static Vector2D zero() {
        return new Vector2D(0, 0);
    }

    /**
     * Creates a vector with one in both x and y components.
     *
     * @return the vector with (1,1)
     */
    public static Vector2D one() {
        return new Vector2D(1, 1);
    }
}
