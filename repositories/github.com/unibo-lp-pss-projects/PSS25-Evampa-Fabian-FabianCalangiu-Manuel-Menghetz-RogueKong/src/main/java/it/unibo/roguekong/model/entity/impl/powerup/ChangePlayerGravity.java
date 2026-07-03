package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;


/**
 * Power up that alters the player's gravity
 */
public class ChangePlayerGravity implements PowerUp {
    private final String name;
    private final String description;
    private final double multiplier;

    private static double ORIGINAL_GRAVITY = 0.05;

    /**
     * Creates an instance of the powerup
     * @param multiplier needed in order to alter the player's gravity
     */
    public ChangePlayerGravity(double multiplier){
        this.multiplier = multiplier;
        this.name = "Alter gravity";
        this.description = "Changes player's gravity.";
    }


    @Override
    public void applyEffect(PlayerImpl player) {
        player.setGravity(player.getGravity().gravity() * this.multiplier, player.getGravity().max_fall_speed());
    }

    public static void removeEffect(PlayerImpl player) {
            player.setGravity(ORIGINAL_GRAVITY, player.getGravity().max_fall_speed());
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
