package model.strategies;

import model.utils.Direction;
import model.utils.ModelVariables;
import model.hitbox.HitboxImpl;
import model.hitbox.HitboxCircle;

/**
 * 
 * Defines the movement strategy followed by the player.
 *
 */
public class InputMovement implements MovementStrategy {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = -4037578062094505090L;

    @Override
    public HitboxImpl movement(final HitboxCircle h, final double steps, final double delta) {
        double x = 0;
        double y = 0;

        if (ModelVariables.getMoveDirection().contains(Direction.RIGHT)) {
            x += 1;
        }
        if (ModelVariables.getMoveDirection().contains(Direction.LEFT)) {
            x -= 1;
        }
        if (ModelVariables.getMoveDirection().contains(Direction.UP)) {
            y -= 1;
        }
        if (ModelVariables.getMoveDirection().contains(Direction.DOWN)) {
            y += 1;
        }

        if (x != 0 || y != 0) {
            final double angle = Math.toDegrees(Math.atan2(x, -y));

            final double stepsx = steps * delta * Math.sin(angle * Math.PI / 180);
            final double stepsy = -steps * delta * Math.cos(angle * Math.PI / 180);

            return new HitboxImpl(h.getX() + stepsx, h.getY() + stepsy);
        }
        return h;
    }

}
