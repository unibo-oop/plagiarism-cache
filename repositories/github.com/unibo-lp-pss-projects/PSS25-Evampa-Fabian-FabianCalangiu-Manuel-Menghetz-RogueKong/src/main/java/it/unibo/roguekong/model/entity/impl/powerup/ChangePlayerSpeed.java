package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

/**
 * PowerUp that alters the player speed
 */
public class ChangePlayerSpeed implements PowerUp {
    private final double multiplier;
    private final String name;
    private final String description;

    private static double ORIGINAL_SPEED = 1;

    /**
     * Creates an instance of a power up
     * @param multiplier needed in order to alter player's velocity
     */
    public ChangePlayerSpeed(double multiplier){
        this.multiplier = multiplier;
        this.name = "Speed Power Up";
        this.description = "Changes the player's speed.";
    }

    @Override
    public void applyEffect(PlayerImpl player){
        VelocityImpl velocity = player.getVelocity();

        velocity.setVelocityX(player.getVelocity().getVelocityX() * this.multiplier);
        player.setVelocity(velocity);
    }

    public static void removeEffect(PlayerImpl player){
        VelocityImpl velocity = player.getVelocity();
        velocity.setVelocityX(ORIGINAL_SPEED);
        player.setVelocity(velocity);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
