package model.entities.properties;

/**
 * Enumeration used for the direction's entity on the axis X.
 *
 */
public enum DirectionX {

    /**
     * Right.
     */
    RIGHT(1),
    /**
     * Left.
     */
    LEFT(-1),
    /**
     * Stop.
     */
    STOP(0);

    private final Integer unitVector;

    /**
     * 
     * @param unitVector
     *          indicate if the direction is positive or negative.
     */
    DirectionX(final Integer unitVector) {
        this.unitVector = unitVector;
    }

    /**
     * 
     * @return the direction is positive or negative
     */
    public Integer getUnitVector() {
        return this.unitVector;
    }
}
