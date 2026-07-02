package vg.model.entity.dynamicEntity.enemy;

import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

public abstract class MinorEnemy extends Enemy {

    MinorEnemy(final V2D v2D, final V2D speed, final int radius, final Shape shape, final MassTier massTier) {
        super(v2D, speed, radius, shape, massTier);

    }
}
