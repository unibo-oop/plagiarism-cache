package it.unibo.oop.mge.c3d.geometry;

/**
 * 
 * An object representing a line in the cartesian coordinate system.
 *
 */
public interface Line {
    /**
     * 
     * @param a the first point to draw the line from
     * @param b the second point to draw the line from
     * @return a new Line, passing through the two points provided
     */
    static Line fromPoints(final Point2D a, final Point2D b) {
        return new LineImpl(a, b);
    }

    /**
     * 
     * @return the slope of the line
     */
    double getM();

    /**
     * 
     * @return the y-intercept of the line
     */
    double getQ();

    /**
     * 
     * @return the zero of the line
     */
    double getZero();

    /**
     * 
     * @param x the value to solve for
     * @return the value of the line at the given x
     */
    double solveFor(double x);

}
