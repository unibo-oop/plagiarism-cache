package it.unibo.model.entity.obstacles;

import it.unibo.model.entity.EntityImpl;
import it.unibo.model.hitbox.RectangleHitbox;
/**
 * The PlatformImpl class represents the platform in the level.
 * It extends EntityImpl<RectangleHitbox> and has as paramether the position in the level and the size for the HitBox.
 */
public class PlatformImpl extends EntityImpl<RectangleHitbox> implements Platform {

    /**
     * Constructs a new instance of Platform.
     * 
     * @param x coordinate of the platform.
     * @param y coordinate of the platform.
     * @param width size of the platform.
     * @param height size of the platform.
     */
    public PlatformImpl(final double x, final double y, final int width, final int height) {
        super(x, y, new RectangleHitbox(x, y, width, height));
    }
}
