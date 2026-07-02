package vg.model.entity.dynamicEntity.enemy;

import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

public abstract class Enemy extends DynamicEntity {

    Enemy(final V2D v2D, final V2D speed, final int radius, final Shape shape, final MassTier massTier) {
        super(v2D, speed, radius, shape, massTier);

    }
}
