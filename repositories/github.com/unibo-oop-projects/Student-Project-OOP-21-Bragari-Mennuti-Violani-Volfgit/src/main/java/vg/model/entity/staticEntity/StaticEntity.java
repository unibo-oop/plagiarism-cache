package vg.model.entity.staticEntity;

import vg.model.entity.ShapedEntity;
import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

/**
 * Abstract class that extends {@link ShapedEntity}.
 * This class represents entities that cannot move
 * after being created. The Shape of static entities
 * is usually squared and the massTier is the highest.
 * @see vg.model.entity.Entity
 * @see vg.model.entity.ShapedEntity
 */
public abstract class StaticEntity extends ShapedEntity {

    /**
     * Constructor of {@link StaticEntity}, takes only
     * 2 parameters as the shape and the mass tier are
     * fixed.
     * @param position the position of the entity
     * @param radius the half-width and half-height of the entity
     * @see Shape
     * @see MassTier
     */
    protected StaticEntity(final V2D position, final int radius) {
        super(position, radius, Shape.SQUARE, MassTier.HIGH);
    }
}
