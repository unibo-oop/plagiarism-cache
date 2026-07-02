package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.MovingObject;

/**
 * Class that extends AbstractLaneImpl to specify the behaviour for Road type Lane, in particular to add Car type obstacles.
 */
public class Road extends AbstractLaneImpl {

    /**
     * Initialize the field of the super class.
     * @param speed the speed of the lane
     * @param direction the direction of the lane
     */
    public Road(final float speed, final Direction direction) {
        super(speed, direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMovingObject(final MovingObject obstacle) {
        if (obstacle instanceof Car) {
            super.addObstacle(obstacle);
        }
    }
}
