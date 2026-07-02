package it.unibo.model.entity.target;

import it.unibo.commons.Constants;
import it.unibo.model.entity.EntityImpl;
import it.unibo.model.hitbox.RectangleHitbox;

public class BandageGirlImpl extends EntityImpl<RectangleHitbox> implements BandageGirl {

    public BandageGirlImpl(final double x, final double y) {
        super(x, y, 
            new RectangleHitbox(
                x, 
                y, 
                (int) (Constants.TILE_SIZE * Constants.SCALE_PROPORTION), 
                (int) (Constants.TILE_SIZE * Constants.SCALE_PROPORTION)));
    }
}
