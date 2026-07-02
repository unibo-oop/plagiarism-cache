package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.MovingObject;

/**
 * Implements the interface Lane, model the start, mid and last lane of the level.
 */
public final class Ground extends AbstractLaneImpl {

    /**
     * Constructs a Ground lane with the specified speed and direction.
     * @param speed the speed of the lane
     * @param direction the direction of movement
     */
    private Ground(final float speed, final Direction direction) {
        super(speed, direction);
    }

    /**
     * Constructs a Ground lane with default values (speed 0, no direction).
     */
    public Ground() {
        this(0, null);
    }

    @Override
    public void addMovingObject(final MovingObject obstacle) { }

    @Override
    public boolean isCompleted() {
        return true;
    }

}
