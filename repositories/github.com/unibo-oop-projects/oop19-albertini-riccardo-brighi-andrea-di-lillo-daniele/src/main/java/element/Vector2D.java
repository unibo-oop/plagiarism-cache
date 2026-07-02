package element;

public interface Vector2D {

    /**
     * @return the value of the x component of the vector
     */

    double getXComponent();

    /**
     * @return the value of the y component of the vector
     */

    double getYComponent();

    /**
     * @return the value in radians of the angle of the vector
     * @throws IllegalStateException if the vector is arithmetic null
     */

    double getRadiansAngle() throws IllegalStateException;

    /**
     * @return the value in degrees of the angle of the vector
     * @throws IllegalStateException if the vector is arithmetic null
     */

    double getDegreesAngle() throws IllegalStateException;

    /**
     * @return true if the vector is arithmetic null, else return false
     */

    boolean isNullVector();

    /**
     * @return the module of the vector
     */

    double getModulus();

    /**
     * @return a new vector with the same angle but with module 1
     * @throws ArithmeticException if the vector is arithmetic null
     */

    Vector2D getNormalizedVector() throws ArithmeticException;

    /**
     * @param scalar the scalar value of the multiplication
     * @return a new vector with module of the actual vector multiplied by scalar
     */

    Vector2D scalarMultiplication(double scalar);

    /**
     * @param vector2D the vector to compare
     * @return true if the normalized vectors are equals with precision 4
     * @throws IllegalStateException if either of them is arithmetic null
     */

    boolean hasSameNormalizedVector(Vector2D vector2D) throws IllegalStateException;

    /**
     * @param vector2D  the vector to compare
     * @param precision the precision of the comparison
     * @return true if the normalized vectors are equals with precision @param precision
     * @throws IllegalStateException if either of them is arithmetic null
     */
    boolean hasSameNormalizedVector(Vector2D vector2D, int precision) throws IllegalStateException;
}
