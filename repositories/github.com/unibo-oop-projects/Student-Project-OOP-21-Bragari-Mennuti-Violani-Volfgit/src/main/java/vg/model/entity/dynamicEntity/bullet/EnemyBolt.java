package vg.model.entity.dynamicEntity.bullet;

import vg.utils.Shape;
import vg.utils.V2D;

public abstract class EnemyBolt extends Bolt {

    public EnemyBolt(final V2D position, final V2D speed, final int radius, final Shape shape) {
        super(position, speed, radius, shape);
    }
}
