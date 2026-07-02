package ballblast.model.inputs;

/**
 * All possible input types.
 */
public enum InputType {
    /**
     * Moves left.
     */
    MOVE_LEFT,
    /**
     * Moves right.
     */
    MOVE_RIGHT,
    /**
     * Shoots.
     */
    SHOOT,
    /**
     * Doesn't move left.
     */
    STOP_MOVING_LEFT,
    /**
     * Doesn't move left.
     */
    STOP_MOVING_RIGHT,
    /**
     * Doesn't shot.
     */
    STOP_SHOOTING;
}
