package javawulf.model;

import java.awt.Rectangle;
import java.util.Optional;

/**
 * Implementation of BoudingBox that considers the center point
 * in order to form the collision area around it.
 */
public final class BoundingBoxImpl implements BoundingBox {

    private Optional<Rectangle> area;
    private CollisionType type;

    /**
     * Creates a new BoundingBox.
     * 
     * @param x The x axis position of the GameElement
     * @param y The y axis position of the GameElement
     * @param width The width of the collision area
     * @param height The height of the collision area
     * @param type The type of collision area
     */
    public BoundingBoxImpl(final int x, final int y, final int width, final int height, final CollisionType type) {
        this.area = Optional.ofNullable(new Rectangle(x - width / 2, y - height / 2, width, height));
        this.type = type;
    }

    @Override
    public boolean isCollidingWith(final Rectangle box) {
        return this.area.orElse(new Rectangle()).intersects(box);
    }

    @Override
    public Rectangle getCollisionArea() {
        return this.area.orElse(new Rectangle());
    }

    @Override
    public void setCollisionArea(final int x, final int y, final int width, final int height) {
        this.area = Optional.ofNullable(new Rectangle(x - width / 2, y - height / 2, width, height));
    }

    @Override
    public CollisionType getCollisionType() {
        return this.type;
    }

    @Override
    public void changeCollisionType(final CollisionType type) {
        this.type = type;
    }

    @Override
    public void setCollisionArea(final Rectangle area) {
        this.area = Optional.of(area);
    }

}
