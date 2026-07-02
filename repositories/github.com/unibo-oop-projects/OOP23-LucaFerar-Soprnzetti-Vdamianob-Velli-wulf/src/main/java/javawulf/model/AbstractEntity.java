package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.map.TileType;

/**
 * Abstract implementation of the Entity interface.
 */
public abstract class AbstractEntity extends GameObject implements Entity {

    /**
     * MOVEMENT_DELTA defines the scalar constant that will be multiplied
     * by the direction vector and the speed of the Entity in order
     * to define its movement.
     */
    public static final int MOVEMENT_DELTA = OBJECT_SIZE / 8;
    private int speed;
    private Direction direction;
    private int stun;

    /**
     * Creates an Entity.
     * 
     * @param position the position of the entity
     * @param type     the collision type of the entity
     * @param speed    the speed of the entity
     */
    protected AbstractEntity(final Coordinate position, final CollisionType type, final int speed) {
        super(position, type);
        this.speed = speed;
        this.direction = Direction.DOWN;
    }

    /**
     * @return the speed of the entity.
     */
    public final int getSpeed() {
        return this.speed;
    }

    @Override
    public final Direction getDirection() {
        return this.direction;
    }

    @Override
    public final void setSpeed(final int speed) {
        this.speed = speed;
    }

    /**
     * Changes the direction of the entity.
     * 
     * @param direction the new direction the entity will have
     */
    public final void setDirection(final Direction direction) {
        this.direction = direction;
    }

    /**
     * Checks if the entity is colliding with a wall.
     * 
     * @param m the map which the entity is in
     * @return true if the entity is colliding with a wall, false otherwise
     */
    protected final boolean isCollidingWithWall(final Map m) {
        final var tile = m.getTileTypes(this.getBounds());
        return tile.contains(TileType.WALL);
    }

    /**
     * Must be extended using the implementation in this class.
     */
    @Override
    public boolean isHit(final BoundingBox box) {
        return this.getBounds().isCollidingWith(box.getCollisionArea())
                && control(box);
    }

    /**
     * @param box The boundingBox that must be checked
     * @return true if the type of the box is the one that damages the Entity,
     *         otherwise it will return false
     */
    protected abstract boolean control(BoundingBox box);

    @Override
    public final void reduceStun() {
        if (this.getBounds().getCollisionType().equals(CollisionType.STUNNED)) {
            if (stun == 0) {
                this.getBounds().changeCollisionType(originalCollisionType());
            } else {
                this.stun--;
            }
        }
    }

    /**
     * @return The collision type of the Entity
     */
    protected abstract CollisionType originalCollisionType();

    @Override
    public final void setStun(final int stun) {
        this.stun = stun;
        this.getBounds().changeCollisionType(CollisionType.STUNNED);
    }
}
