package model.strategy;

import java.util.List;

import utility.Command;
import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;
import model.utility.ModelUtility;

/**
 * Movement for player.
 *
 */
public class PlayerMovement implements MovementStrategy {

    /**
     * Method that perform Player Movement.
     */
    @Override
    public HitBox move(final double dt, final double vel, final CircleHitBox h) {
        final List<Command> list = ModelUtility.getListMovementCommand();
        int deltaX = 0, deltaY = 0;
        if (list.contains(Command.UP)) {
            deltaY--;
        }
        if (list.contains(Command.DOWN)) {
            deltaY++;
        }
        if (list.contains(Command.RIGHT)) {
            deltaX++;
        }
        if (list.contains(Command.LEFT)) {
            deltaX--;
        }
        if (deltaX != 0 || deltaY != 0) {
            // This function give the result in degrees of the angle in direction we should
            // move.
            final double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
            // x-component and y-component of movements, using trigonometry.
            final double performedY = vel * dt * Math.sin(Math.toRadians(angle));
            final double performedX = vel * dt * Math.cos(Math.toRadians(angle));
            return new CircleHitBox(h.getX() + performedX, h.getY() + performedY, h.getRadius());
        }
        // If anything command was pressed in this frame, return the old HitBox,
        // in the last position.
        return h;
    }
}
