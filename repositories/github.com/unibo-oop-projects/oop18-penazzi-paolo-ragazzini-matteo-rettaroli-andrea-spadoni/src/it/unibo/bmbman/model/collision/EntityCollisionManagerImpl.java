package it.unibo.bmbman.model.collision;

import java.awt.Rectangle;
import java.util.Set;

import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.utilities.Position;
/**
 * An implementation of {@link CollisionController}.
 */
public class EntityCollisionManagerImpl implements EntityCollisionManager {
    private final CollisionComponent followedEntity;
    /**
     * Constructor for {@link CollisionControllerImpl}.
     * @param followedEntity {@link Entity} associated to this {@link CollisionController}
     */
    public EntityCollisionManagerImpl(final CollisionComponent followedEntity) {
        this.followedEntity = followedEntity;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void detectCollision(final Set<Entity> entities) {
        entities.stream().filter(e -> !e.equals(followedEntity.getFollowedEntity()))
        .forEach(e -> {
            if (checkCollision(e, followedEntity.getTopHitbox())) {
                notifyCollision(e, new Position(this.followedEntity.getFollowedEntity().getPosition().getX(), e.getPosition().getY() + e.getDimension().getHeight()));
            } else if (checkCollision(e, followedEntity.getBottomHitbox())) {
                notifyCollision(e, new Position(this.followedEntity.getFollowedEntity().getPosition().getX(), e.getPosition().getY() - e.getDimension().getHeight()));
            } else if (checkCollision(e, followedEntity.getLeftHitbox())) {
                notifyCollision(e, new Position(e.getPosition().getX() + e.getDimension().getWidth(), this.followedEntity.getFollowedEntity().getPosition().getY()));
            } else if (checkCollision(e, followedEntity.getRightHitbox())) {
                notifyCollision(e, new Position(e.getPosition().getX() - e.getDimension().getWidth(), this.followedEntity.getFollowedEntity().getPosition().getY()));
            }
        });
    }
    /**
     * {@inheritDoc}
     */
    public boolean checkCollision(final Entity receiver, final Rectangle collider) {
        return receiver.getCollisionComponent().getHitbox().intersects(collider);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyCollision(final Entity receiver, final Position position) {
        this.followedEntity.notifyCollision(new CollisionImpl(receiver, position));
        receiver.onCollision(new CollisionImpl(followedEntity.getFollowedEntity(), receiver.getPosition()));

    }

}
