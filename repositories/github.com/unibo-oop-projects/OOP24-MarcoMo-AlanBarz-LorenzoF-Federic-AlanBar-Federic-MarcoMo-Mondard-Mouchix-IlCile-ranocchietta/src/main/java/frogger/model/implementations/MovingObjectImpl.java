package frogger.model.implementations;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.MovingObject;

/**
 * Implementation of a moving game object.
 * Handles directional movement and boundary checking for objects that move on the game board.
 */
public class MovingObjectImpl extends GameObjectImpl implements MovingObject {
    private Direction direction;
    private float speed;

    /**
     * Constructs a new MovingObjectImpl.
     *
     * @param pos       the initial position of the object
     * @param dimension the dimension of the object (width and height in blocks)
     * @param speed     the movement speed
     * @param direction the initial direction of movement
     */
    public MovingObjectImpl(final Position pos, final Pair dimension, final float speed, final Direction direction) {
        super(pos, dimension);
        this.direction = direction; 
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final float speed) {
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getSpeed() {
        return speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        this.step();
        this.checkRestart();
    }

    /**
     * Moves the object in its current direction by its speed.
     * This method updates the position of the object based on its direction and speed.
     */
    protected void step() {
        this.setPos(new Position(this.getPos().x() + this.getDirectionValue().x() * this.getSpeed(), 
        this.getPos().y() + this.getDirectionValue().y() * this.getSpeed()));
    }

    /**
     * Checks whether the object has moved outside the valid game area.
     * If so, repositions it to the opposite side and handles special behavior (e.g., stopping an Eagle).
     */
    private void checkRestart() {
        if (!valid(this.getPos())) {
            switch (this.getDirection()) {
                case Direction.RIGHT -> this.setPos(new Position(Constants.MIN_X - 1, this.getPos().y()));
                case Direction.LEFT -> this.setPos(new Position(Constants.MAX_X + 1, this.getPos().y()));
                case Direction.UP -> this.setPos(new Position(this.getPos().x(), Constants.MIN_Y - 1));
                case Direction.DOWN -> this.setPos(new Position(this.getPos().x(), Constants.MAX_Y + 1));
            }

            //to stop the eagles once they are arrived at the end of the column
            if (this instanceof Eagle) {
                final Eagle e = (Eagle) this;
                e.stop();
            }
        }
    }

    /**
     * Checks whether the given position is within valid screen bounds.
     *
     * @param pos the position to validate
     * @return true if the position is within bounds, false otherwise
     */
    private boolean valid(final Position pos) {
        return pos.x() >= Constants.MIN_X - 1 && pos.x() <= Constants.MAX_X + 1 
        && pos.y() >= Constants.MIN_Y - 1 && pos.y() <= Constants.MAX_Y + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getDirectionValue() {
        int x = 0;
        int y = 0;

        switch (this.getDirection()) {
            case Direction.LEFT -> x = -1;
            case Direction.RIGHT -> x = 1;
            case Direction.UP -> y = 1;
            case Direction.DOWN -> y = -1;
        }
        return new Position(x, y);
    }
}
