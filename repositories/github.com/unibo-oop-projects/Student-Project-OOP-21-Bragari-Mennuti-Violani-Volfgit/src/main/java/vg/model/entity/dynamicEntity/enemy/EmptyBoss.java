package vg.model.entity.dynamicEntity.enemy;

import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

/**
 * Used to sync data.
 */
public class EmptyBoss extends Boss {
    public EmptyBoss(V2D position, V2D speed, int radius, Shape shape, MassTier massTier) {
        super(position, speed, radius, shape, massTier);
    }
}
