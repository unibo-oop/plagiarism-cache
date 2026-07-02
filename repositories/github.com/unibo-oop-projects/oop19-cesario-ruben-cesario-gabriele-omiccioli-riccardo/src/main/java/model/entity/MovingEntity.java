package model.entity;

import utilities.math.Vector2D;

/**
 *  Models a rotating entity capable of moving.
 */
public interface MovingEntity extends RotatingEntity {

    /**
     * Returns the speed of this entity.
     * @return the speed of this entity.
     */
    Vector2D getSpeed();

    /**
     * Changes the position of this entity according to its speed and
     * possibly other factors (es.: behaviour of special ships...).
     */
    void move();

    /**
     * Changes the speed of this entity instantaneously.
     * @param newSpeed : the new speed of this entity.
     */
    void resetSpeed(Vector2D newSpeed);

    /**
     * Increases the speed of this entity by a certain amount towards the
     * direction of its acceleration.
     * @param acceleration : amount and direction of acceleration
     */
    void accelerate(Vector2D acceleration);
    /**
     * Decreases the speed of this entity by a certain amount towards the
     * direction of its acceleration.
     * @param acceleration : amount and direction of acceleration
     */
    default void decelerate(Vector2D acceleration) {
        this.accelerate(acceleration.opposite());
    }

}
