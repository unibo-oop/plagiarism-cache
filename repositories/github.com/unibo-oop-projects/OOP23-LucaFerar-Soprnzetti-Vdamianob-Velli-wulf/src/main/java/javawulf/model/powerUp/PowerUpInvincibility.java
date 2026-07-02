package javawulf.model.powerup;

import javawulf.model.Coordinate;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;

/**
 * PowerUpInvincibility is a Type of PowerUp.
 */
public final class PowerUpInvincibility extends PowerUpImpl { 

    private static final int STUN_FINISHED = 0; 

    /**
     * Creates a PowerUpInvincibility.
     * @param position The starting position
     * @param duration The duration of the PowerUp
     * @param points Points given once collected 
     * @param type The type name of the PowerUp
     */
    public PowerUpInvincibility(final Coordinate position, final int duration, final int points, final String type) {
        super(position, points, type, duration);
    }

    @Override
    public void applyEffect(final Player p) {
        super.applyEffect(p);
        if (this.stillActive()) {
            //make the player invincible
            p.getBounds().changeCollisionType(CollisionType.STUNNED);
            p.setStun(this.getDuration());
        } else {
            //return the player to his normal form
            resetEffect(p);
        }
    }

    @Override
    public void resetEffect(final Player p) {
        p.getBounds().changeCollisionType(CollisionType.PLAYER);
        p.setStun(STUN_FINISHED);
    }

}
