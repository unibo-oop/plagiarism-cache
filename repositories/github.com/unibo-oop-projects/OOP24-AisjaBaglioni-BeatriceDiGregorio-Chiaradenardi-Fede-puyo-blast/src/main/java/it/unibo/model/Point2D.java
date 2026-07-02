package it.unibo.model;

/**
 * A utility record representing a 2D point with x and y coordinates as doubles.
 * It provides the utility methods for basic vector operations.
 */
public record Point2D(double x, double y) {

    /**
     * Sums two Point2Ds
     * 
     * @param a The first Point2D addend
     * @param b The second Point2D addend
     * 
     * @return A new Point2D representing the sum of the two given points.
     */
    public static Point2D add(Point2D a, Point2D b) {
        return new Point2D(a.x + b.x, a.y + b.y);
    }

    /**
     * Subtracts two Point2Ds
     * 
     * @param a The Point2D minuend
     * @param b The Point2D subtrahend
     * 
     * @return A new Point2D representing the difference between the two given points.
     */
    public static Point2D sub(Point2D a, Point2D b) {
        return new Point2D(a.x - b.x, a.y - b.y);
    }

    /**
     * Negates a Point2D, flipping its coordinates
     * 
     * @param a The Point2D to flip
     * 
     * @return A new Point2D with the negated coordinates of the given point.
     */
    public static Point2D neg(Point2D a) {
        return new Point2D(-a.x, -a.y);
    }

    /**
     * Multiplies a Point2D by a scalar
     * 
     * @param a The Point2D multiplicand
     * @param b The scalar multiplier
     * 
     * @return a Point2D being the product of the two parameters
     */
    public static Point2D mul(Point2D a, double k) {
        return new Point2D(a.x * k, a.y * k);
    }

    /**
     * Divides a Point2D by a scalar
     * 
     * @param a The Point2D dividend
     * @param b The scalar divisor
     * 
     * @return a Point2D being the quotient of the two parameters
     */
    public static Point2D div(Point2D a, double k) {
        return new Point2D(a.x / k, a.y / k);
    }

}