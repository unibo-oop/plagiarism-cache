package clashclass.commons;

/**
 * Represents an integer Vector in two dimensions (x,y).
 *
 * @param x the x component of the vector
 * @param y the y component of the vector
 */
public record VectorInt2D(int x, int y) {
    /**
     * Calculates the exact distance between two vectors.
     *
     * @param other the other vector
     *
     * @return the distance between this vector and the other given vector
     */
    public double distance(final VectorInt2D other) {
        final int deltaX = x - other.x;
        final int deltaY = y - other.y;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Creates a vector with zero in both x and y components.
     *
     * @return the vector with (0,0)
     */
    public static VectorInt2D zero() {
        return new VectorInt2D(0, 0);
    }

    /**
     * Creates a vector with one in both x and y components.
     *
     * @return the vector with (1,1)
     */
    public static VectorInt2D one() {
        return new VectorInt2D(1, 1);
    }
}
