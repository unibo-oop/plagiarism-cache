package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.MovingObject;

/**
 * Class that extends AbstractLaneImpl to specify the behaviour for River type Lane, in particular to add Trunk type obstacles.
 */
public class River extends AbstractLaneImpl {

    /**
     * Initialize the field of the super class.
     * @param speed the speed of the lane
     * @param direction the direction of the lane
     */
    public River(final float speed, final Direction direction) {
        super(speed, direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMovingObject(final MovingObject obstacle) {
        if (obstacle instanceof Trunk) {
            super.addObstacle(obstacle);
        }
    }
}
