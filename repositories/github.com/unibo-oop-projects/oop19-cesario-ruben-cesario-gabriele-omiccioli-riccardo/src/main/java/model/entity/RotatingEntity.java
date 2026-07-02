package model.entity;

/**
 *  Models a collidable entity capable of rotating itself.
 */
public interface RotatingEntity extends CollidableEntity {

    /**
     * Returns the angle of rotation of this entity.
     * @return the angle of rotation of this entity.
     */
    double getRotation();

    /**
     * Changes the angle of rotation of this entity instantaneously.
     * @param newAngle : the new rotation of this entity.
     */
    void resetRotation(double newAngle);

    /**
     * Rotates this entity of a certain angle, anti-clockwise.
     * @param angle : angle of rotation.
     */
    void rotateAnticlockwise(double angle);
    /**
     * Rotates this entity of a certain angle, clockwise.
     * @param angle : angle of rotation.
     */
    default void rotateClockwise(double angle) {
        rotateAnticlockwise(-angle);
    }

}
