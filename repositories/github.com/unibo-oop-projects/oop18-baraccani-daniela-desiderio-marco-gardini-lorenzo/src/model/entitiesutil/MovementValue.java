
package model.entitiesutil;

/**
 * Movement with respective values.
 */
public enum MovementValue {
    /**
     * Bubble up movement.
     */
    BUBBLE_MOVE_UP(0, -2),
    /**
     * Bubble left movement.
     */
    BUBBLE_MOVE_LEFT(-6, 0),
    /**
     * Bubble right movement.
     */
    BUBBLE_MOVE_RIGHT(6, 0),
    /**
     * Bubble floating up movement.
     */
    BUBBLE_FLOAT_UP(0, 1),
    /**
     * Bubble floating down movement.
     */
    BUBBLE_FLOAT_DOWN(0, -1),
    /**
     * Character jump movement.
     */
    CHARACTER_JUMP(0, 2),
    /**
     * Character up movement.
     */
    CHARACTER_MOVE_UP(0, -2),
    /**
     * Character left movement.
     */
    CHARACTER_MOVE_LEFT(-5, 0),
    /**
     * Character right movement.
     */
    CHARACTER_MOVE_RIGHT(5, 0),
    /**
     * Character falling movement.
     */
    CHARACTER_FALL(0, 2),
    /**
     * Undo character falling movement.
     * Both coordinates must be -1 * CHARACTER_FALL.
     */
    CHARACTER_FALLUNDO(0, -2),
    /**
     * Character shoots.
     */
    SHOOT(0, 0);

    private final int velocityX;
    private final int velocityY;

    /**
     * Movement with respective values.
     * 
     * @param velocityX x-axis speed
     * @param velocityY y-axis speed
     */
    MovementValue(final int velocityX, final int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     * @return horizontal velocity
     */
    public int getVelocityX() {
        return velocityX;
    }

    /**
     * @return vertical velocity
     */
    public int getVelocityY() {
        return velocityY;
    }

}
