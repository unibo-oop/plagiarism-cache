package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;

public class DoubleJump implements PowerUp {

    private final String name;
    private final String description;

    private static int ORIGINAL_MAX_JUMPS = 1;

    public DoubleJump(){
        this.name = "Double Jump";
        this.description = "Grants a second jump to the player.";
    }

    @Override
    public void applyEffect(PlayerImpl player){
        player.setMaxJumps(player.getMaxJumps() + 1);
    }

    public static void removeEffect(PlayerImpl player){
        player.setMaxJumps(ORIGINAL_MAX_JUMPS);
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
