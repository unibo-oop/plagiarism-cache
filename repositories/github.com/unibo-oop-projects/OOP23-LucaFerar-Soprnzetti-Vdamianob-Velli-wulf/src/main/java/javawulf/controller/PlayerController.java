package javawulf.controller;

import java.util.Optional;

import javawulf.model.Direction;

/**
 * Class that updates the status of Player by using the commands
 * coming from the user.
 */
public interface PlayerController {

    /**
     * Update the status of Player.
     * 
     * @param up if the Player should go up it is true
     * @param down if the Player should go down it is true
     * @param left if the Player should go left it is true
     * @param right if the Player should go right it is true
     */
    void updatePlayerStatus(boolean up, boolean down, boolean left, boolean right);

    /**
     * @param attack if the Player should attack it is true
     */
    void updateSwordStatus(boolean attack);

    /**
     * @return The direction of Player
     */
    Optional<Direction> getDirection();

    /**
     * @return True if the Player character is attacking
     */
    boolean isAttack();
}
