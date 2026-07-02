package model.minigames.perilouspath;

/**
 * Represents the possible directions.
 */
public enum Direction {

    /**
     * Left direction.
     */
    LEFT,

    /**
     * Right direction.
     */
    RIGHT,

    /**
     * Up direction.
     */
    UP,

    /**
     * Down direction.
     */
    DOWN;

    /**
     * Horizontal direction.
     * 
     * @return 1 to move rightward
     *        -1 to move leftward
     */
    public int horizontal() {
        return this == RIGHT ? 1 : -1;
    }

    /**
     * Vertical direction.
     * 
     * @return 1 to move downward
     *        -1 to move upward
     */
    public int vertical() {
        return this == DOWN ? 1 : -1;
    }
}
