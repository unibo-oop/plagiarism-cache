package javawulf.model.powerup;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;
import javawulf.model.player.Score;

/**
 * PowerUpDoublePoints is a Type of PowerUp.
 */
public final class PowerUpDoublePoints extends PowerUpImpl {

    /**
     * Creates a PowerUpDoublePoints.
     * @param position The starting position
     * @param duration The duration of the PowerUp
     * @param points Points given once collected 
     * @param type The type name of the PowerUp
     */
    public PowerUpDoublePoints(final Coordinate position, final int duration, final int points, final String type) {
        super(position, points, type, duration);
    }

    @Override
    public void applyEffect(final Player p) {
        super.applyEffect(p);
        if (this.stillActive()) {
            p.getScore().setMultiplier(Score.Multiplier.DOUBLE);
        } else {
            resetEffect(p);
        }
    }

    @Override
    public void resetEffect(final Player p) {
        p.getScore().setMultiplier(Score.Multiplier.DEFAULT);
    }

}
