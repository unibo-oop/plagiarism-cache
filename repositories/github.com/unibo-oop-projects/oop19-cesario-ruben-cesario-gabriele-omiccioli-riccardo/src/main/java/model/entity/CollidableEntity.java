package model.entity;

/**
 * Models an entity capable of colliding with other CollidableEntity.
 * A CollidableEntity is considered to have a radial hitbox.
 */
public interface CollidableEntity extends Entity {

    /**
     * Returns the radius of the circle occupied by this entity.
     * @return the radius of the circle occupied by this entity.
     */
    double getRadialHitbox();

}
