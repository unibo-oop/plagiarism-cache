package model.aim;

import java.io.Serializable;

import model.player.PlayerInfo;

/**
 * 
 * The interface models the concept of a card "aim".
 *
 */
public interface Aim extends Serializable {

    /**
     * 
     * @return whether the player achieved his aim or not.
     * 
     */
    boolean aimAchieved();

    /**
     * 
     * @return the type of Aim to which the specific aim belong.
     * 
     */
    AimLabel getType();

    /**
     * 
     * @return player this aim has been assigned to.
     *
     */
    PlayerInfo getPlayer();

}
