package javawulf.model.powerup;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

/**
 * PowerUpImpl is the implementation of PowerUp, base of all PowerUps.
 */
public abstract class PowerUpImpl extends AbstractCollectable implements PowerUp {

    private int duration;
    private final String type;

    /**
     * Creates a new PowerUp.
     * @param position The starting position
     * @param points Points given once collected
     * @param type The type name of the PowerUp
     * @param duration The duration of the PowerUp
     */
    protected PowerUpImpl(final Coordinate position, final Integer points, final String type, final int duration) {
        super(position, points);
        this.type = type;
        this.duration = duration;
    }

    @Override
    public final int getDuration() {
        return this.duration;
    }

    @Override
    public final String getType() {
        return this.type;
    }

    @Override
    public final boolean stillActive() {
        return duration > 0;
    }

    @Override
    public final void updateDuration() {
        if (duration > 0) {
            duration--;
        }
    }

    @Override
    public final String toString() {
        return "PowerUp: [duration=" + this.getDuration() 
        + ", type=" + this.getType() + ", pointsGiven=" + this.getPoints() + "]";
    }

    /**
     * Extend To customize effect on new powerUps.
     */
    @Override
    public void applyEffect(final Player p) {
        p.getPowerUpHandler().collectPowerUp(this, p);
    }


    @Override
    public final void finishEffect(final Player p) {
        this.duration = 0;
        this.resetEffect(p);
    }

    /**
     * Clear Player effect.
     * @param p The player where the effect gets reset
     */
    public abstract void resetEffect(Player p);

}
