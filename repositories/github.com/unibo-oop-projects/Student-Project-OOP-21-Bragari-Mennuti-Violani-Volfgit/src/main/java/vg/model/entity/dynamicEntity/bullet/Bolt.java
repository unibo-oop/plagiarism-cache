package vg.model.entity.dynamicEntity.bullet;

import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.utils.Shape;
import vg.utils.V2D;

import static vg.utils.MassTier.NOCOLLISION;

public abstract class Bolt extends DynamicEntity {
    public Bolt(V2D position, V2D speed, int radius, Shape shape) {
        super(position, speed, radius, shape, NOCOLLISION);
    }
}
