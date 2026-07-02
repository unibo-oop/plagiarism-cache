package model.physics;

/**
 * It represent a module for autonomy enemy movement.
 *
 */
public interface EnemyAI {

    /**
     * Move the specific {@link Enemy} based on {@link Hero}'s position and a timer.
     */
    void nextMove();
}
