package model.basic_component;

import java.io.Serializable;

/** This interface represents a 2D point.
 * The point can't change location.
 */

public interface StaticPoint2D extends Serializable {
    /**
     * @return the X coordinate of the point
     */
    int getX();
    /**
     * @return the Y coordinate of the point
     */
    int getY();

    /**
     * Checks if the other point is adjacent to this.
     * Two {@link StaticPoint2DImpl} are adjacent if they distance is <= 1
     * @param other the {@link StaticPoint2D} to test
     * @return if the two {@link StaticPoint2D} are adjacent to other
     */
    default boolean checkAdjacent(StaticPoint2D other) {
        return this.getDistance(other) <= 1;
    }

    /**
     * Computation of the distance between two point.
     * @param other the {@link StaticPoint2D} of which we want to compute the distance
     * @return an integer representing the distance
     */
    default int getDistance(StaticPoint2D other) {
        return new Double(Math.sqrt(Math.pow(getX() - other.getX(), 2) + (Math.pow(getY() - other.getY(), 2)))).intValue();
    }

    /**
     * Checks if this and the other {@link StaticPoint2D} are aligned.
     * @param other is the {@link StaticPoint2D} to test against
     * @return if the {@link StaticPoint2D} are aligned
     */
    default boolean alligned(StaticPoint2D other) {
        return getX() == other.getX() || getY() == other.getY();
    }
}
