package it.unibo.bmbman.model.collision;

import java.awt.Rectangle;

import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/**
 * Implementation of {@link CollisionComponent}.
 */
public class CollisionComponentImpl implements CollisionComponent {
    private static final int POSITION_ADJUSTMENT = 10 * ScreenToolUtils.SCALE;
    private static final int HEIGHT_ADJUSTMENT = 5 * ScreenToolUtils.SCALE;
    private static final int WIDTH_ADJUSTMENT = 20 * ScreenToolUtils.SCALE;
    private final Entity entity;
    /**
     * Construct a {@code CollisionComponentImpl}.
     * @param entity the followed entity
     */
    public CollisionComponentImpl(final Entity  entity) {
        this.entity = entity;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getFollowedEntity() {
        return this.entity;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public Rectangle getHitbox() {
        return new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(),
                entity.getDimension().getWidth(), entity.getDimension().getHeight());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getTopHitbox() {
        return new Rectangle(entity.getPosition().getX() + POSITION_ADJUSTMENT, entity.getPosition().getY(),
                entity.getDimension().getWidth() - WIDTH_ADJUSTMENT, HEIGHT_ADJUSTMENT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getBottomHitbox() {
        return new Rectangle(entity.getPosition().getX() + POSITION_ADJUSTMENT, entity.getPosition().getY() + entity.getDimension().getHeight() - HEIGHT_ADJUSTMENT,
                entity.getDimension().getWidth() - WIDTH_ADJUSTMENT, HEIGHT_ADJUSTMENT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getLeftHitbox() {
        return new Rectangle(entity.getPosition().getX(), entity.getPosition().getY() + POSITION_ADJUSTMENT,
                HEIGHT_ADJUSTMENT, entity.getDimension().getHeight() - WIDTH_ADJUSTMENT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getRightHitbox() {
        return new Rectangle(entity.getPosition().getX() + entity.getDimension().getWidth() - HEIGHT_ADJUSTMENT, entity.getPosition().getY() + POSITION_ADJUSTMENT,
                HEIGHT_ADJUSTMENT, entity.getDimension().getHeight() - WIDTH_ADJUSTMENT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyCollision(final Collision c) {
        this.entity.onCollision(c);
    }

}
