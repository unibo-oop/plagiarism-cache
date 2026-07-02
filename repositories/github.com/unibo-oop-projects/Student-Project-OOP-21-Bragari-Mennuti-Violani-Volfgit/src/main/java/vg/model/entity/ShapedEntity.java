package vg.model.entity;

import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

import java.io.Serializable;

/**
 * ShapedEntity implements {@link Entity} and add the {@link Shape}
 * to it (Circle or Square). It has {@link #radius} that defines
 * show large the entity is.
 * @see Entity
 * @see Shape
 * @see MassTier
 * @see V2D
 */
public abstract class ShapedEntity implements Entity, Serializable {
    /**
     * 2D coordinates to keep current entity position in map.
     */
    private V2D position;
    /**
     * shape defines the shape of the shaped entity.
     * @see Shape
     */
    private Shape shape;
    /**
     * radius defines the radius of the shaped entity,
     * for now only squares and circles are available
     * as Shapes, so radius is actually the radius if
     * Shape is circle, and radius is the half-width
     * and half-height if shape is square.
     * @see Shape
     */
    private int radius;
    /**
     * @see MassTier
     */
    private MassTier massTier;

    /**
     * Constructor of ShapedEntity class.
     * @param position the value of starting position
     * @param radius the value of the radius
     * @param shape the value of the shape
     * @param massTier the value of the mass tier
     * @see V2D
     * @see Shape
     * @see Entity
     * @see MassTier
     */
    protected ShapedEntity(final V2D position, final int radius, final Shape shape, final MassTier massTier) {
        this.position = position;
        this.radius = radius;
        this.shape = shape;
        this.massTier = massTier;
    }
    public final V2D getPosition() {
      return this.position;
    }

    /**
     * Update entity position with a new one.
     * @param position new position of entity
     */
    public final void setPosition(final V2D position) {
        this.position = position;
    }

    /**
     * Getter of the radius of this entity. If the entity is a circle then it is
     * an actual radius from the center. If the entity is a square then it is the
     * half-width and half-height of the entity.
     * @return the radius or half-width/half-height
     * @see Shape
     */
    public final int getRadius() {
        return this.radius;
    }

    /**
     * Returns the shape of this entity.
     * @return the shape of this entity
     * @see Shape
     */
    public Shape getShape() {
        return this.shape;
    }
    /**
     * Checks if the given position collides with this ShapedEntity.
     * @param position The position to check if it is in shape of this entity
     */
    @Override
    public boolean isInShape(final V2D position) {
        return this.shape.isInShape(this.getPosition(), position, this.getRadius());
    }

    /**
     * Checks if the two shaped entities are colliding.
     * @param other the ShapedEntity to check if this is colling with.
     * @return true if the two entities are colliding, false otherwise
     */
    @Override
    public boolean isInShape(final ShapedEntity other) {
        return this.shape.isInShape(this.getPosition(), other.getPosition(), this.getRadius(), other.getRadius(), other.getShape());
    }

    /**
     * {@inheritDoc}
     * @return the mass tier
     */
    @Override
    public MassTier getMassTier() {
        return this.massTier;
    }

    /**
     * {@inheritDoc}
     * @param toSet the mass tier to set to
     */
    @Override
    public void setMassTier(final MassTier toSet) {
        this.massTier = toSet;
    }
}
