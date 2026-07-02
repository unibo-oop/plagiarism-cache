package javawulf.model.powerup;

import javawulf.model.Entity;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;

/**
 * PowerUpSpeed is a Type of PowerUp.
 */
public final class PowerUpSpeed extends PowerUpImpl {

    /**
     * Creates a PowerUpSpeed.
     * @param position The starting position
     * @param duration The duration of the PowerUp
     * @param points Points given once collected 
     * @param type The type name of the PowerUp
     */
    public PowerUpSpeed(final Coordinate position, final int duration, final int points, final String type) {
        super(position, points, type, duration);
    }

    @Override
    public void applyEffect(final Player p) {
        super.applyEffect(p);
        if (this.stillActive()) {
            p.setSpeed(Entity.DOUBLE_SPEED);
        } else {
            resetEffect(p);
        }
    }

    @Override
    public void resetEffect(final Player p) {
        p.setSpeed(Entity.DEFAULT_SPEED);
    }

}
