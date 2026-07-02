package it.unibo.bmbman.model.utilities;
/**
 * Define the direction in which an entity can move.
 */
public enum Direction {
    /**
     * right direction.
     */
    RIGHT,
    /**
     * left direction.
     */
    LEFT,
    /**
     * up direction.
     */
    UP,
    /**
     * down direction.
     */
    DOWN,
    /**
     * no direction, the player is idle.
     */
    IDLE;
    /**
     * Used to know the opposite direction.
     * @param direction the direction of which you want to know the opposite
     * @return a {@link Direction}
     */
    public static Direction getOpposite(final Direction direction) {
         Direction opposite = direction;
        switch (direction) {
        case DOWN: opposite = UP;
            break;
        case LEFT: opposite = Direction.RIGHT;
            break;
        case RIGHT: opposite = Direction.LEFT;
            break;
        case UP: opposite = DOWN;
            break;
        default:
            break;
        }
        return opposite;
    }
}
