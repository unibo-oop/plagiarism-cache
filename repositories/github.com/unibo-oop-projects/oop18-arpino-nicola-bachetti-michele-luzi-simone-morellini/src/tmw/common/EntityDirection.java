package tmw.common;

/**
 * Enumeration that portray the direction the entities can take.
 *
 */
public enum EntityDirection {

    /**
     * upward direction.
     */
    GOING_UP,

    /**
     * downward direction.
     */
    GOING_DOWN,

    /**
     * rightward direction.
     */
    GOING_RIGHT,

    /**
     * leftward direction.
     */
    GOING_LEFT;

    /**
     * Getter for the direction at the specific index.
     * @param index the index
     * @return the corresponding direction.
     */
    public static EntityDirection getDirection(final int index) {
        switch (index) {
        case 0:
            return EntityDirection.GOING_DOWN;

        case 1:
            return EntityDirection.GOING_LEFT;

        case 2:
            return EntityDirection.GOING_UP;

        case 3:
            return EntityDirection.GOING_RIGHT;

        default:
            throw new IllegalArgumentException();
        }
    }

}
