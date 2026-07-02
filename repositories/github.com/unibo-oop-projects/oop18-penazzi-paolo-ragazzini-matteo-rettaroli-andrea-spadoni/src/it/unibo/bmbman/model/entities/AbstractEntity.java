package it.unibo.bmbman.model.entities;

import it.unibo.bmbman.model.collision.Collision;
import it.unibo.bmbman.model.collision.CollisionComponent;
import it.unibo.bmbman.model.collision.CollisionComponentImpl;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/**
 * Models the general aspects of a entity.
 *
 */
public abstract class AbstractEntity implements Entity {

    private  Position position;
    private final EntityType entityType;
    private final Dimension dimension;
    private final CollisionComponent collisionComponent;
    /**
     * Create a static entity.
     * @param position the point in the game world
     * @param entityType which type of game entity is
     * @param dimension width and height  of the entity
     */
    public AbstractEntity(final Position position, final EntityType entityType, final Dimension dimension) {
        this.position = new Position(position.getX() * ScreenToolUtils.SCALE, position.getY() * ScreenToolUtils.SCALE);
        this.entityType = entityType;
        this.collisionComponent = new CollisionComponentImpl(this);
        this.dimension = new Dimension(dimension.getHeight() * ScreenToolUtils.SCALE, dimension.getWidth() * ScreenToolUtils.SCALE);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return position;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Position position) {
        this.position = position;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getDimension() {
        return this.dimension;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return this.entityType;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionComponent getCollisionComponent() {
        return this.collisionComponent;
    }
    @Override
    public abstract void onCollision(Collision c);
    @Override
    public abstract boolean remove();
}
