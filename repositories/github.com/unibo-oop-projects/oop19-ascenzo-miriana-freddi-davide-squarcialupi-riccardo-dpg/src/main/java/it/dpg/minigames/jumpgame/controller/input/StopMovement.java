package it.dpg.minigames.jumpgame.controller.input;

/**
 * Implementation of Input that stops the player
 * @author Davide Picchiotti
 * */

public class StopMovement extends AbstractHorizontalMovement {
    @Override
    public int getHorizontalMovementSpeed() {
        return 0;
    }
}
