package model.entities;

import model.entities.properties.Velocity;

/**
 * 
 * Interface for enemies that can shoot.
 *
 */
public interface ActiveEnemy extends Shooter, Character, Enemy {

    /**
     * 
     * @return the bullet's velocity.
     */
    Velocity getBulletVelocity();

    /**
     * 
     * @return the bullet's type.
     */
    BulletType getBulletType();
}
