package model.entities.properties;

/**
 * Enumeration used for the direction's entity on the axis Y.
 *
 */
public enum DirectionY {
    /**
     * Up.
     */
    UP(-1),
    /**
     * Down.
     */
    DOWN(1),
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
    DirectionY(final Integer unitVector) {
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
