package controller.stagehandler.collisionhandler.collider;

import model.entity.CollidableEntity;
import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.weapon.Weapon.Projectile;
import model.weapon.basic.BasicWeapon.StandardProjectile;

/**
 * Controls the behaviour of a StandardProjectile on collision.
 */
public class StandardProjectileCollider implements Collider {

    /**
     * {@inheritDoc}
     * A StandardProjectileCollider will alert the specified entity that it has collided.
     * The specified entity is implicitly considered to be a StandardProjectile.
     */
    @Override
    public void collideWithPlayer(final CollidableEntity entity, final PlayerShip player) {
        ((StandardProjectile) entity).hit();
    }

    /**
     * {@inheritDoc}
     * A StandardProjectileCollider will alert the specified entity that it has collided.
     * The specified entity is implicitly considered to be a StandardProjectile.
     */
    @Override
    public void collideWithEnemy(final CollidableEntity entity, final EnemyShip enemy) {
        ((StandardProjectile) entity).hit();
    }

    /**
     * {@inheritDoc}
     * A StandardProjectileCollider won't do anything, ignoring the collision.
     */
    @Override
    public void collideWithProjectile(final CollidableEntity entity, final Projectile projectile) { }

}
