package spacesurvival.model.gameobject.moveable;

import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.gameobject.moveable.movement.MovementLogic;
import spacesurvival.model.EngineImage;
import spacesurvival.model.collision.bounding.BoundingBox;
import spacesurvival.model.collision.eventgenerator.EventComponent;

/**
 * An object which can move, extends a basic game object and implements a logic for movement and an optional target.
 */
public abstract class MoveableObject extends GameObject {

    private V2d velocity;
    private double acceleration;
    private P2d targetPosition;
    private MovementLogic movementLogic;
    private boolean isMoving;

    public MoveableObject(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent eventComponent, final V2d velocity, final double acceleration, final MovementLogic movementLogic,
            final P2d targetPosition) {
        super(engineImage, position, bb, eventComponent);
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.targetPosition = targetPosition;
        this.movementLogic = movementLogic;
    }

    /**
     * Move the object in the next position based on velocity.
     */
    public void move() {
        this.movementLogic.move(this);
    }

    /**
     * @return the current vector 2d velocity.
     */
    public V2d getVelocity() {
        return velocity;
    }

    /**
     * Sets a new velocity to the object.
     * 
     * @param velocity the velocity to set
     */
    public void setVelocity(final V2d velocity) {
        this.velocity = velocity;
    }

    /**
     * @return the object acceleration
     */
    public double getAcceleration() {
        return this.acceleration;
    }

    /**
     * Sets a new acceleration to the object.
     *
     * @param acceleration the acceleration to set
     */
    public void setAcceleration(final double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * @return the object movement logic interface.
     */
    public MovementLogic getMovement() {
        return movementLogic;
    }

    /**
     * Sets a new movement logic for the object.
     * 
     * @param movementLogic the new movement logic to set
     */
    public void setMovement(final MovementLogic movementLogic) {
        this.movementLogic = movementLogic;
    }

    /**
     * Start the object moving, depends on the movement logic implemented.
     */
    public void startMoving() {
        this.movementLogic.startMoving(this);
    }

    /**
     * Stop the object moving, depends on the movement logic implemented.
     */
    public void stopMoving() {
        this.movementLogic.stopMoving(this);
    }

    /**
     * @return true if the object is moving.
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Start or stop the object move, depends on the movement logic implemented.
     * 
     * @param isMoving true if want to start, false otherwise
     */
    public void setMoving(final boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
     * @return the optional of target position
     */
    public P2d getTargetPosition() {
        return targetPosition;
    }

    /**
     * Sets the target position of Enemy.
     *
     * @param targetPosition the new target position
     */
    public void setTargetPosition(final P2d targetPosition) {
        this.targetPosition = targetPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "MovableObject [velocity=" + velocity + ", acceleration=" + acceleration + ", target=" + targetPosition
                + ", movementLogic=" + movementLogic + ", isMoving=" + isMoving + super.toString() + "]";
    }

}
