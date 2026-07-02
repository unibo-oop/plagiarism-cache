package controller.stagehandler.collisionhandler.collider;

import model.entity.CollidableEntity;
import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.weapon.Weapon.Projectile;

/**
 * Controls the behaviour of a CollidableEntity on collision.
 */
public interface Collider {

    /**
     * Makes the specified entity collide with the specified player, updating
     * the status of the specified entity only.
     * @param entity : the specified entity.
     * @param player : the specified player.
     */
    void collideWithPlayer(CollidableEntity entity, PlayerShip player);

    /**
     * Makes the specified entity collide with the specified enemy, updating
     * the status of the specified entity only.
     * @param entity : the specified entity.
     * @param enemy : the specified enemy.
     */
    void collideWithEnemy(CollidableEntity entity, EnemyShip enemy);

    /**
     * Makes the specified entity collide with the specified projectile, updating
     * the status of the specified entity only.
     * @param entity : the specified entity.
     * @param projectile : the specified projectile.
     */
    void collideWithProjectile(CollidableEntity entity, Projectile projectile);

}
