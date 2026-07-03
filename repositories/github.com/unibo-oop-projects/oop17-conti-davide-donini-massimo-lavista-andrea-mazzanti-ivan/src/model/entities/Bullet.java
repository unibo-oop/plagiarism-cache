package model.entities;

/**
 * 
 * Represent the bullet.
 *
 */
public interface Bullet extends Entity {

    /**
     * @return the damage of the bullet.
     */
    double getDamage();

    /**
     * @return the type of the bullet.
     */
    BulletType getBulletType();

}
