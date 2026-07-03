package model.strategies;

import model.utils.ModelVariables;
import model.hitbox.HitboxImpl;
import model.hitbox.HitboxCircle;

/**
 * 
 */
public class ChaseTheTarget implements MovementStrategy {

    /**
     * 
     */
    private static final long serialVersionUID = 476223046086565958L;

    @Override
    public HitboxImpl movement(final HitboxCircle h, final double steps, final double delta) {
        final double angle = Math.toDegrees(Math.atan2(ModelVariables.getPlayerHitbox().getX() - h.getX(),
                -(ModelVariables.getPlayerHitbox().getY() - h.getY())));

        final double stepsx = steps * delta * Math.sin(angle * Math.PI / 180);
        final double stepsy = -steps * delta * Math.cos(angle * Math.PI / 180);
        return new HitboxImpl(h.getX() + stepsx, h.getY() + stepsy);
    }

}
