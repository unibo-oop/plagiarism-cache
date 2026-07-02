package controller.stagehandler.collisionhandler.collider;

import model.entity.CollidableEntity;
import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.ship.SpaceShip;
import model.ship.basic.BasicSpaceShip;
import model.weapon.Weapon.Projectile;

/**
 * Controls the behaviour of a BasicSpaceShip on collision.
 */
public class BasicSpaceShipCollider implements Collider {

    private static final double COLLISION_DAMAGE = 0.16;
    private static final double COLLISION_DECELERATION_FACTOR = 0.1;

    /**
     * {@inheritDoc}
     * A BasicSpaceShipCollider will decelerate the specified entity by a constant
     * factor, also dealing collision damage based on its speed in relation to its maximum speed.
     * The specified entity is implicitly considered to be a BasicSpaceShip.
     */
    @Override
    public void collideWithPlayer(final CollidableEntity entity, final PlayerShip player) {
        collideWithSpaceShip((BasicSpaceShip) entity, player);
    }

    /**
     * {@inheritDoc}
     * A BasicSpaceShipCollider will decelerate the specified entity by a constant
     * factor, also dealing collision damage based on its speed in relation to its maximum speed.
     * The specified entity is implicitly considered to be a BasicSpaceShip.
     */
    @Override
    public void collideWithEnemy(final CollidableEntity entity, final EnemyShip enemy) {
        collideWithSpaceShip((BasicSpaceShip) entity, enemy);
    }

    /**
     * {@inheritDoc}
     * A BasicSpaceShipCollider will deal damage to the specified entity according to
     * the damage of the specified projectile.
     * The specified entity is implicitly considered to be a BasicSpaceShip.
     */
    @Override
    public void collideWithProjectile(final CollidableEntity entity, final Projectile projectile) {
        ((BasicSpaceShip) entity).receiveDamage(projectile.getDamage());
    }

    /**
     * Changes the properties of the specified spaceship, being considered a collision with another
     * spaceship happened.
     * @param spaceship : the specified spaceship.
     */
    private void collideWithSpaceShip(final BasicSpaceShip collidingSpaceship, final SpaceShip spaceship) {
        spaceship.receiveDamage(COLLISION_DAMAGE * getCollisionDamageFactor(collidingSpaceship, spaceship));
        spaceship.resetSpeed(spaceship.getSpeed().multiplyByScalar(1 - COLLISION_DECELERATION_FACTOR));
    }

    /**
     * Returns how much of the base collision damage will be taken by the affected spaceship during the collision with the
     * second specified ship. This factor considers the sum of their speed divided by the maximum speed of the affected spaceship.
     * @param affectedSpaceship : the affected spaceship.
     * @param spaceship : the second specified spaceship.
     * @return the collision damage factor of the collision between the two spaceships.
     */
    private double getCollisionDamageFactor(final BasicSpaceShip affectedSpaceship, final SpaceShip spaceship) {
        return (affectedSpaceship.getSpeed().module() + Math.cos(affectedSpaceship.getSpeed().angle(spaceship.getSpeed())) * spaceship.getSpeed().module()) / affectedSpaceship.getMaxSpeed();
    }

}
