package model.aim;

import model.player.PlayerInfo;

/**
 * 
 * Interface for a Factory of Aims.
 *
 */
public interface AimFactory {

    /**
     * 
     * Method used to initialize the two following lists: availableEnemies,
     * aimList. All other method shouldn't work if this method has never been
     * called before.
     *
     */
    void initializeFactory();

    /**
     * 
     * @param player
     *            player who will get the aim
     * 
     * @return a new random aim for the player specified
     *
     */
    Aim assignAim(PlayerInfo player);
}
