package it.unibo.oop.model;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;

public abstract class MovableEntity extends AbstractEntity implements Movable {

    private Vector2 movementVector;
    private final Velocity speedValues;
    /**
     * Standard movable entity constructor
     * @param startingX starting X
     * @param startingY starting Y
     * @param movementVector initial movement vector
     * @param speedValue default speed values
     */
    public MovableEntity(final double startingX, final double startingY, final Vector2 movementVector,
            final Velocity speedValue) {
        super(startingX, startingY);
        this.movementVector = movementVector;
        this.speedValues = speedValue;
    }
    /**
     * Adds to his position the movement {@link Vector2}
     */
    public void move() {
        this.setPosition(this.getPosition().sumVector(movementVector));
    }
    /**
     * Set the current movement vector to another one
     * @param newMovement new movement vector
     */
    public void setMovement(final Vector2 newMovement) {
        this.movementVector = newMovement;
    }

    /**
     * Getter for the movement vector
     * @return the current movement vector
     */
    public Vector2 getMovement() {
        return this.movementVector;
    }

    /**
     * Abstrac collision handling
     * @param newPosition new {@link Position} where the Entity has to move to
     * @throws CollisionHandlingException The error that represents if the collision happened
     */
    public abstract void checkCollision(Position newPosition) throws CollisionHandlingException;

    /**
     * Abstract update function
     */
    public abstract void update();

    /**
     * Getter for the movable entity speed
     * @return the current entity {@link Velocity}
     */
    public Velocity getVelocity() {
        return this.speedValues;
    }

    /**
     * Getter for the current {@link Direction} where the face is turned
     * @return a {@link Direction} indicating the information
     */
    public Direction getFaceDirection() {

        if (Math.abs(this.movementVector.getX()) > Math.abs(this.movementVector.getY())) {
            if (this.movementVector.getX() > 0) {
                return Direction.RIGHT;
            } else {
                return Direction.LEFT;
            }
        } else {
            if (this.movementVector.getY() > 0) {
                return Direction.DOWN;
            } else {
                return Direction.UP;
            }
        }
    }
}
