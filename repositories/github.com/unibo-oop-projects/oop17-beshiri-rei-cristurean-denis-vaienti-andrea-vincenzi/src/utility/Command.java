package utility;

/**
 * Enumeration that represent the possibility of command.
 */
public enum Command {

    /**
     * Command Up is represented by 90° angle.
     */
    UP(-90),

    /**
     * Command Right is represented by 0° angle.
     */
    RIGHT(0),

    /**
     * Command Left is represented by 180° angle.
     */
    LEFT(180),

    /**
     * Command Down is represented by 90° angle.
     */
    DOWN(90);

    private final double angle;

    /**
     * Constructor for enum.
     * 
     * @param angle
     *            Angle of command.
     */
    Command(final double angle) {
        this.angle = angle;
    }

    /**
     * Return angle of command.
     * 
     * @return angle.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * 
     * @return Opposite command.
     */
    public Command getOppositeCommand() {
        if (this == DOWN) {
            return UP;
        } else if (this == UP) {
            return DOWN;
        } else if (this == LEFT) {
            return RIGHT;
        } else {
            return LEFT;
        }
    }
}
