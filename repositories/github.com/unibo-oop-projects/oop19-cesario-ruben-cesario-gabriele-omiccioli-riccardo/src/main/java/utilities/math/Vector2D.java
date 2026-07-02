package utilities.math;

/**
 *  Models functions for 2-dimensional vectors.
 */
public interface Vector2D {

    /**
     * Returns a new Point2D containing the components of this vector.
     * @return the components of this vector
     */
    Point2D components();

    /**
     * Returns the module of the vector.
     * @return the module of the vector
     */
    double module();

    /**
     * Returns a new vector opposite of the vector.
     * @return a new vector opposite of the vector
     */
    Vector2D opposite();

    /**
     * Sums the two vectors.
     * @param vector Vector
     * @return a new vector, sum between the two vectors
     */
    Vector2D add(Vector2D vector);

    /**
     * Determines the difference between the first vector and the second vector.
     * @param vector Vector
     * @return a new vector, difference between the two vectors
     */
    default Vector2D sub(Vector2D vector) {
        return this.add(vector.opposite());
    }

    /**
     * Scalar multiplication of the vector.
     * @param scalar The scalar to be multiplied
     * @return a new vector containing the result of the scalar multiplication
     */
    Vector2D multiplyByScalar(double scalar);

    /**
     * Scalar multiplication between this vector and the vector passed as an argument.
     * @param vector The second vector
     * @return the result of the scalar multiplication
     */
    double multiply(Vector2D vector);

    /**
     * Sets the vector that goes from pointA to pointB (with sign).
     * @param pointA The first point
     * @param pointB The second point
     */
    void distance(Point2D pointA, Point2D pointB);

    /**
     * Returns the angle between this vector and the vector passed as an argument.
     * @param vector The second vector
     * @return The angle between this vector and the vector passed as an argument
     */
    double angle(Vector2D vector);

    /**
     * Returns the angle of this vector in degrees and starting from "top" 
     * (in a Cartesian plane an angle of 0 degrees corresponds to the y axis).
     * @return The angle of this vector
     */
    double angleOfThisVector();

}
