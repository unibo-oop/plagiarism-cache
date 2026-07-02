package model.direction;

/**
 * 
 * Simple interface for all possible directions.
 *
 */
public interface Direction {
    /**
     * @return movement along the X axis.
     */
    int movementAlongX();
 
    /**
     * @return movement along the Y axis.
     */
    int movementAlongY();

    /**
     * @return index of direction.
     */
    int getIndex();

}
