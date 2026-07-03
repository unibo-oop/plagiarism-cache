package model.strategies;

import model.hitbox.HitboxImpl;
import model.hitbox.HitboxCircle;

/**
 * 
 * Defines the Stactionary strategy. This class return the Hitbox provided as
 * parameter in the movement method.
 *
 */
public class Stactionary implements MovementStrategy {

    /**
     * 
     */
    private static final long serialVersionUID = -6192760818860887347L;

    @Override
    public HitboxImpl movement(final HitboxCircle h, final double steps, final double delta) {
        return h;
    }

}
