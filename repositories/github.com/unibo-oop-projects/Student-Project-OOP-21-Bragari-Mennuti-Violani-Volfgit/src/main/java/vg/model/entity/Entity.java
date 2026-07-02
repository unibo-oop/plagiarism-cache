package vg.model.entity;

import vg.utils.MassTier;
import vg.utils.V2D;

/**
 * Entity is the upper interface that describes an object on the map,
 * that can be static/dynamic, with a shape or not (a V2D position basically).
 * Every entity has a MassTier, even the ones without a shape. If this interfaces
 * is implemented and is not a {@link ShapedEntity}, {@link #isInShape(ShapedEntity)}
 * can use the ShapedEntity {@link ShapedEntity#isInShape(V2D)} internally.
 * @see #getPosition()
 * @see #isInShape(V2D)
 * @see #isInShape(ShapedEntity)
 * @see #getMassTier()
 * @see V2D
 * @see MassTier
 */
public interface Entity {
    /**
     * Returns the position of the entity.
     * @return the position of the entity
     * @see V2D
     */
    V2D getPosition();

    /**
     * Check if this entity (that is at least a V2D position)
     * is in the same position as another.
     * @param position the position to check if is on the same position as this.
     * @return true if the position is the same, false otherwise.
     * @see V2D
     */
    boolean isInShape(V2D position);
    /**
     * Check if this entity (that is at least a V2D position)
     * is contained or colliding with a ShapedEntity.
     * @param entity the ShapedEntity to check if this is colling with.
     * @return true if the position of this is in shape of the entity, false otherwise
     * @see V2D
     * @see vg.utils.Shape
     * @see ShapedEntity
     */
    boolean isInShape(ShapedEntity entity);

    /**
     * Returns the mass tier of the entity.
     * @return the mass tier of the entity
     * @see MassTier
     */
    MassTier getMassTier();

    /**
     * Sets the mass tier of the entity.
     * @param toSet the mass tier to set to
     * @see MassTier
     */
    void setMassTier(MassTier toSet);

}
