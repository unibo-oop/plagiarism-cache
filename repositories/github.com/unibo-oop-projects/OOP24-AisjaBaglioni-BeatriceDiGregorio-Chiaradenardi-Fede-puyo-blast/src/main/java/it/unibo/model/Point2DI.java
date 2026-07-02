
package it.unibo.model;

/**
 * A utility record representing a 2D point with x and y integer coordinates.
 * It provides the utility methods for basic vector operations,
 * an hash generator, a compare method and a Point2DI to Point2D converter.
 */
public record Point2DI(int x, int y) {

    /**
     * Sums two Point2DIs
     * 
     * @param a The first Point2DI addend
     * @param b The second Point2DI addend
     * 
     * @return A new Point2DI representing the sum of the two given points.
     */
    public static Point2DI add(Point2DI a, Point2DI b) {
        return new Point2DI(a.x + b.x, a.y + b.y);
    }

    /**
     * Subtracts two Point2DIs
     * 
     * @param a The Point2DI minuend
     * @param b The Point2DI subtrahend
     * 
     * @return A new Point2DI representing the difference between the two given
     *         points.
     */
    public static Point2DI sub(Point2DI a, Point2DI b) {
        return new Point2DI(a.x - b.x, a.y - b.y);
    }

    /**
     * Negates a Point2DI, flipping its coordinates
     * 
     * @param a The Point2DI to flip
     * 
     * @return A new Point2DI with the negated coordinates of the given point.
     */
    public static Point2DI neg(Point2DI a) {
        return new Point2DI(-a.x, -a.y);
    }

    /**
     * Multiplies a Point2DI by a scalar
     * 
     * @param a The Point2DI multiplicand
     * @param b The scalar multiplier
     * 
     * @return A new Point2DI being the product of the two parameters
     */
    public static Point2DI mul(Point2DI a, int k) {
        return new Point2DI(a.x * k, a.y * k);
    }

    /**
     * Divides a Point2DI by a scalar
     * 
     * @param a The Point2DI dividend
     * @param b The scalar divisor
     * 
     * @return A new Point2DI being the quotient of the two parameters
     */
    public static Point2DI div(Point2DI a, int k) {
        return new Point2DI(a.x / k, a.y / k);
    }

    /**
     * Converts an integer Point2DI into a floating-point Point2D.
     * 
     * @param a The Point2DI to convert
     * 
     * @return A new Point2D
     */
    public static Point2D toPoint2D(Point2DI a) {
        return new Point2D(a.x, a.y);
    }

    /**
     * Checks if the parameter object is equal to the Point2DI
     * 
     * @param obj The object to compare
     * 
     * @return {@code true} if the two object have the same type and coordinates,
     *         {@code false} otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point2DI other = (Point2DI) obj;
        return x == other.x && y == other.y;
    }

    /**
     * Creates a unique integer hash code for a Point2DI, using a prime number
     */
    public int hashCode() {
        return x * 10007 + y;
    }
}
