package model.entities;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

/**
 * An implementation of a basic Entity of the game, contains all the methods
 * shared by all the game's entities.
 *
 */
public class EntityImpl implements Entity {
    private Double x;
    private Double y;
    private final Dimension hitboxDim;
    private final Point2D position;

    /**
     * Constructor for a generic Entity inside the game.
     * 
     * @param x
     *            Starting X coordinate of the entity.
     * @param y
     *            Starting Y coordinate of the entity.
     * @param dim
     *            Dimension of the entity's hitbox: it is represented by a
     *            rectangle.
     */
    public EntityImpl(final Double x, final Double y, final Dimension dim) {
        this.x = x;
        this.y = y;
        this.hitboxDim = dim;
        this.position = new Point2D.Double(x, y);
    }

    @Override
    public final Double getX() {
        return x;
    }

    @Override
    public final Double getY() {
        return y;
    }

    @Override
    public final void setX(final Double x) {
        this.x = x;
    }

    @Override
    public final void setY(final Double y) {
        this.y = y;
    }

    @Override
    public final Rectangle getHitbox() {
        return new Rectangle(this.x.intValue(), this.y.intValue(), hitboxDim.width, hitboxDim.height);
    }

    @Override
    public final Point2D getPosition() {
        return position;
    }

    @Override
    public final boolean isColliding(final Entity entity) {
        return this.getHitbox().intersects(entity.getHitbox());
    }

}
