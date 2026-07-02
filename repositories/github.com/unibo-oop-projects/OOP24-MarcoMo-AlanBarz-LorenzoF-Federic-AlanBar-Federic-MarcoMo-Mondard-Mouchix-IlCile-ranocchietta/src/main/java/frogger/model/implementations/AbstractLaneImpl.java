package frogger.model.implementations;

import java.util.HashSet;
import java.util.Set;

import frogger.common.Direction;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.MovingObject;

/**
 * {@inheritDoc}.
 */
public abstract class AbstractLaneImpl implements Lane {

    private final Set<MovingObject> obstacles = new HashSet<>();
    private final float speed;
    private final Direction direction;
    private boolean completed;

    /**
     * Initialize the private field speed and direction.
     * @param speed the speed of the lane
     * @param direction the direction of the lane
     */
    public AbstractLaneImpl(final float speed, final Direction direction) {
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * Adds the given obstacle to the lane's internal obstacle list.
     * <p>
     * This method is intended to be used by subclasses when implementing
     * their specific logic for adding obstacles to the lane. It ensures
     * that obstacle management remains encapsulated within the abstract class,
     * while allowing controlled modification of the internal state.
     * </p>
     * @param obstacle the obstacle to be added to the lane
     */
    protected void addObstacle(final MovingObject obstacle) {
        this.obstacles.add(obstacle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<MovingObject> getLaneObstacles() {
        return new HashSet<>(obstacles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCompleted() {
        this.completed = true;
    }

}
