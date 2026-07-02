package controller.stagehandler.collisionhandler;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import controller.stagehandler.collisionhandler.collider.BasicSpaceShipCollider;
import controller.stagehandler.collisionhandler.collider.Collider;
import controller.stagehandler.collisionhandler.collider.StandardProjectileCollider;
import model.Stage;
import model.entity.CollidableEntity;
import model.entity.EntityID;
import model.entity.EntityID.EntityIDCategory;
import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.weapon.Weapon.Projectile;
import utilities.Pair;
import utilities.exception.IllegalInitializationException;
import utilities.exception.NotImplementedException;
import utilities.math.Vector2DImpl;

/**
 * A basic implementation of CollisionHandler.
 */
public class CollisionHandlerImpl implements CollisionHandler {

    private static final Map<EntityID, Collider> COLLIDER_TABLE = new EnumMap<>(EntityID.class);
    private static final double COLLISION_TOLLERANCE = 0.01;

    private final Set<Collision> detectedCollisions;

    static {
        initializeData();
    };

    public CollisionHandlerImpl() {
        this.detectedCollisions = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areColliding(final CollidableEntity e1, final CollidableEntity e2) {
        return !e1.equals(e2) && e1.getPosition().distance(e2.getPosition()) < minDistance(e1, e2) - COLLISION_TOLLERANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkCollisions(final Stage stage) {
        checkCollision(stage.player(), stage);
        for (final EnemyShip enemy : stage.enemies()) {
            checkCollision(enemy, stage);
        }
        for (final Projectile projectile : stage.projectiles()) {
            checkCollision(projectile, stage);
        }
        for (final Collision collision : this.detectedCollisions) {
            final CollidableEntity e1 = collision.collidingEntities.getFirst();
            final CollidableEntity e2 = collision.collidingEntities.getSecond();
            collide(e1, e2, stage);
            collide(e2, e1, stage);
            if (e1.getID().belongsTo(EntityIDCategory.SPACESHIPS) && e2.getID().belongsTo(EntityIDCategory.SPACESHIPS)) {
                reposition(e1, e2);
            }
        }
        this.detectedCollisions.clear();
    }

    /**
     * Check all the collisions that may have occurred with the specified entity within the specified stage.
     * @param entity : the specified entity.
     * @param stage : the specified stage.
     */
    private void checkCollision(final CollidableEntity entity, final Stage stage) {
        if (areColliding(entity, stage.player())) {
            this.detectedCollisions.add(new Collision(entity, stage.player()));
        }
        for (final EnemyShip enemy : stage.enemies()) {
            if (areColliding(entity, enemy)) {
                this.detectedCollisions.add(new Collision(entity, enemy));
            }
        }
        for (final Projectile projectile : stage.projectiles()) {
            if (areColliding(entity, projectile)) {
                this.detectedCollisions.add(new Collision(entity, projectile));
            }
        }
    }

    /**
     * Makes the first specified entity be affected by the collision with the second specified entity,
     * in the specified stage.
     * @param affectedEntity : the first specified entity. This entity will be affected by the collision.
     * @param entity : the second specified entity. This entity won't be affected by the collision.
     * @param stage : the specified stage.
     */
    private void collide(final CollidableEntity affectedEntity, final CollidableEntity entity, final Stage stage) {
        if (entity.getID().belongsTo(EntityIDCategory.SPACESHIPS)) {
            if (stage.isInPlayerFaction(entity)) {
                COLLIDER_TABLE.get(affectedEntity.getID()).collideWithPlayer(affectedEntity, (PlayerShip) entity);
            } else {
                COLLIDER_TABLE.get(affectedEntity.getID()).collideWithEnemy(affectedEntity, (EnemyShip) entity);
            }
        } else if (entity.getID().belongsTo(EntityIDCategory.PROJECTILES)) {
            COLLIDER_TABLE.get(affectedEntity.getID()).collideWithProjectile(affectedEntity, (Projectile) entity);
        } else {
            throw new NotImplementedException("CollisionHandlerImpl.collide: the ID of specified entity:[" + entity.getID() + "] isn't considered in this method.");
        }
    }

    /**
     * Repositions two entities that are colliding if they are too close.
     * @param e1 : the first entity.
     * @param e2 : the second entity.
     */
    private void reposition(final CollidableEntity e1, final CollidableEntity e2) {
        if (areColliding(e1, e2)) {
            final Vector2DImpl distanceVector = new Vector2DImpl(e1.getPosition(), e2.getPosition());
            final double collisionDistance = minDistance(e1, e2) - e1.getPosition().distance(e2.getPosition());
            e1.resetPosition(e1.getPosition().translate(distanceVector.multiplyByScalar(-collisionDistance / (2 * distanceVector.module())).components()));
            e2.resetPosition(e2.getPosition().translate(distanceVector.multiplyByScalar(collisionDistance / (2 * distanceVector.module())).components()));
        }
    }

    /**
     * Returns the minimum distance between two CollidableEntity.
     * @param e1 : the first CollidableEntity.
     * @param e2 : the second CollidableEntity.
     * @return the minimum distance between two CollidableEntity.
     */
    private double minDistance(final CollidableEntity e1, final CollidableEntity e2) {
        return e1.getRadialHitbox() + e2.getRadialHitbox();
    }

    /**
     * Initialises the table that maps each EntityID to their collider.
     */
    private static void initializeData() {
        COLLIDER_TABLE.putAll(Map.ofEntries(
                    Map.entry(EntityID.SPACESHIP_BASIC, new BasicSpaceShipCollider()),
                    Map.entry(EntityID.FIGHTER, new BasicSpaceShipCollider()),
                    Map.entry(EntityID.JUGGERNAUT, new BasicSpaceShipCollider()),
                    Map.entry(EntityID.CUTTER, new BasicSpaceShipCollider()),
                    Map.entry(EntityID.PROJECTILE_STANDARD, new StandardProjectileCollider())
        ));
        if (!COLLIDER_TABLE.keySet().containsAll(EntityIDCategory.getEntityIDs(EntityIDCategory.SPACESHIPS))) {
            throw new IllegalInitializationException("CollisionHandlerImpl.initializeData: the collider table doesn't have a key for each possible spaceship.");
        }
        if (!COLLIDER_TABLE.keySet().containsAll(EntityIDCategory.getEntityIDs(EntityIDCategory.PROJECTILES))) {
            throw new IllegalInitializationException("CollisionHandlerImpl.initializeData: the collider table doesn't have a key for each possible projectile.");
        }
    }

    /**
     * Models a collision between two CollidableEntity.
     */
    private static class Collision {

        private final Pair<CollidableEntity, CollidableEntity> collidingEntities;

        Collision(final CollidableEntity first, final CollidableEntity second) {
            this.collidingEntities = new Pair<>(first, second);
        }

        /**
         * Returns the entities that have collided in this collision.
         * @return the entities that have collided in this collision.
         */
        public Collision getEquivalentCollision() {
            return new Collision(collidingEntities.getSecond(), collidingEntities.getFirst());
        }

        /*EQUALS-----------------------------------------------------------*/
        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((collidingEntities == null) ? 0 : collidingEntities.hashCode() + collidingEntities.getInverted().hashCode());
            return result;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Collision other = (Collision) obj;
            if (collidingEntities == null) {
                if (other.collidingEntities != null) {
                    return false;
                }
            } else if (!(collidingEntities.equals(other.collidingEntities) || collidingEntities.equals(other.getEquivalentCollision().collidingEntities))) {
                return false;
            }
            return true;
        }
        /*------------------------------------------------------------------*/
    }

}
