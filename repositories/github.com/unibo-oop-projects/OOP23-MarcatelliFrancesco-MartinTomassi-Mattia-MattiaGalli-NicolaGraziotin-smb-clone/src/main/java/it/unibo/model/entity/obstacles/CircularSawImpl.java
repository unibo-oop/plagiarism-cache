package it.unibo.model.entity.obstacles;

import it.unibo.model.hitbox.CircularHitbox;
import it.unibo.model.entity.EntityImpl;
/**
 * The CircularSawImpl class represents the circular saw in the level.
 * It extends EntityImpl<CircularHitbox> and has as paramether the position in the level and the radius of action for the HitBox.
 */
public class CircularSawImpl extends EntityImpl<CircularHitbox> implements CircularSaw {

    /**
     * Constructs a new instance of CircularSaw.
     * 
     * @param x coordinate of the circular saw.
     * @param y coordinate of the circular saw.
     * @param radius of action of the circular saw.
     */
    public CircularSawImpl(final double x, final double y, final int radius) {
        super(x, y, new CircularHitbox(x, y, radius));
    }
}
