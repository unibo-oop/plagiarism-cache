package zombieversity.model.entities;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

/**
 * 
 * Models general aspects of entities.
 *
 */
public abstract class AbstractEntity implements Entity {

    private Point2D position;
    private final EntityType type;
    private double width;
    private double height;

    /**
     * Construct a static entity.
     * @param p2d the position in the game world.
     * @param type which type of entity is.
     */
    public AbstractEntity(final Point2D p2d, final EntityType type) {
        this.position = p2d;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setPosition(final Point2D position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final BoundingBox getBBox() {
        return new BoundingBox(this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setBBox(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EntityType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }

}
