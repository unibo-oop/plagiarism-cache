package it.dpg.minigames.jumpgame.controller.input;

/**
 * Implementation of Input that moves the player to the right
 * @author Davide Picchiotti
 * */

public class MoveRight extends AbstractHorizontalMovement {
    @Override
    protected int getHorizontalMovementSpeed() {
        return 12;
    }
}
