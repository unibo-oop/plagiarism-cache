package model.weapon;

import java.util.Collection;

import model.entity.ConditionalEntity;
import model.entity.MovingEntity;
import utilities.math.Point2D;

/**
 *  Models a weapon capable of shooting projectiles, in type and
 *  quantity depending on the weapon itself.
 */
public interface Weapon {

    /**
     * Creates one or more projectiles according to position of the 
     * user of this weapon and the target aimed.
     * @param origin : position of the user of this weapon
     * @param direction : direction of the shots in radians
     * @return the projectiles created by shooting this weapon
     */
    Collection<Projectile> shoot(Point2D origin, double direction);

    /**
     * Returns the rate of fire of this weapon.
     * @return the rate of fire of this weapon.
     */
    int getRateOfFire();

    /*CLASS SETTERS & GETTERS------------------------------------------------
    public Projectile getProjectile();
    public void setProjectile(Projectile newProjectile);
    -------------------------------------------------------------------------*/

    /**
     *  Models a shot projectile, capable of dealing damage to an
     *  entity colliding with it.
     */
    interface Projectile extends MovingEntity, ConditionalEntity {

        /**
         * Alerts this projectile that it has hit something.
         */
        void hit();

        /**
         * Returns the damage this projectile will inflict on impact.
         * @return the damage this projectile will inflict on impact.
         */
        int getDamage();

    }
}
