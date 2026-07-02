package utilities;

/**
 * This enumeration indicates where the entity is facing. It assumes all images are 
 * FACING_RIGHT by default.
 */
public enum Direction {
    /**
     * Used for an entity facing up.
     */
    FACING_UP(-90),
    /**
     * Used for an entity facing down.
     */
    FACING_DOWN(90),
    /**
     * Used for an entity facing to the left.
     */
    FACING_LEFT(180),
    /**
     * Used for an entity facing to the right.
     */
    FACING_RIGHT(0);

    private final double angle;

    /**
     * Constructor for the Direction enum.
     * @param angle The angle at which the image will be rotated.
     */
    Direction(final double angle) {
        this.angle = angle;
    }

    /**
     * Getter for the angle.
     * @return angle
     */
    public double getAngle() {
        return this.angle;
    }

}
